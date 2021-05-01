package com.nss.tobacco.activitymanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.CommonAdapter;
import com.nss.tobacco.adapter.ViewHolder;
import com.nss.tobacco.daos.Sec_HongkaoshiDao;
import com.nss.tobacco.entity.Sec_HongkaoshiEntity;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.DividerItemDecoration;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.XUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HongkaoshiActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_HongkaoshiDao sec_hongkaoshiDao = null;

    private List<Sec_HongkaoshiEntity> allentity; //所有数据
    private List<Sec_HongkaoshiEntity> searchentity;//查询结果数据

    private ImageView back_img;
    private TextView add_text;
    private TextView upload;
    private Spinner year_spinner;
    private EditText search_edit;
    private Button search_btn;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;

    //判断是否查询的数据
    private boolean isSearch = false;

    //发送延迟更新数据库
    private Handler mHandler;
    private String photoFoloder = CommonUtil.getSDPath() + File.separator + "hongkaoshi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hongkaoshi);

        sec_hongkaoshiDao = new Sec_HongkaoshiDao(this);
        allentity = sec_hongkaoshiDao.searchAllData();
        mHandler = new Handler();

        initView();
        initData(allentity);
        setLitsener();//监听事件
    }

    private void setLitsener() {
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        upload.setOnClickListener(this);
        setMySpinnerLitsener();
        search_btn.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch=false;
                        search_edit.setText("");
                        allentity=sec_hongkaoshiDao.searchAllData();
                        initData(allentity);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    private void setMySpinnerLitsener() {
    }

    private void initData(List<Sec_HongkaoshiEntity> entites) {
        recyclerView.setAdapter(new CommonAdapter<Sec_HongkaoshiEntity>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, Sec_HongkaoshiEntity mSec_HongkaoshiEntity) {
                holder.setText(R.id.id_num,mSec_HongkaoshiEntity.getCreatetime());
                holder.setText(R.id.id_farmer,mSec_HongkaoshiEntity.getName());

                if("0".equals(mSec_HongkaoshiEntity.getState())){
                    holder.setText(R.id.id_state,"未上传");
                }else{
                    holder.setText(R.id.id_state,"已上传");
                }
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.setOnClickListener(R.id.id_ll_item,new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent();
                        intent.setClass(HongkaoshiActivity.this, Sec_msg_HongkaoshiActivity.class);

                        if(!isSearch){
                            intent.putExtra("hongkaoshiInfo", allentity.get(position));
                        }else{
                            intent.putExtra("hongkaoshiInfo", searchentity.get(position));
                        }
                        startActivity(intent);
                    }
                });
            }
        });
        //RecyclerView分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    private void initView() {
        back_img = (ImageView) findViewById(R.id.back_item);
        TextView tvtitle = (TextView) findViewById(R.id.textView_item);
        tvtitle.setText("烘烤师");
        add_text = (TextView) findViewById(R.id.add_item);
        upload = (TextView) findViewById(R.id.up_item);
        year_spinner = (Spinner) findViewById(R.id.year_item);
        search_edit = (EditText) findViewById(R.id.searchbox);
        search_btn = (Button) findViewById(R.id.search_item);

        recyclerView = (RecyclerView) findViewById(R.id.second_hks_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.second_hks_swipe);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mySpinner();
    }

    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        Spinner spinner = (Spinner) findViewById(R.id.year_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            case R.id.add_item:
                intent.setClass(this, Add_HongkaoshiActivity.class);
                startActivity(intent);
                break;
            case R.id.up_item:
                Toast.makeText(this, "正在上传", Toast.LENGTH_SHORT).show();
                upLoad();
                break;
            case R.id.year_item:
                break;
            case R.id.search_item:
                searchInfoByName();
                break;
            default:
                break;
        }
    }

    private boolean upLoad() {
        //非搜索的界面数据
        if(!isSearch){
            upData(allentity);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isSearch=false;
                    search_edit.setText("");
                    allentity=sec_hongkaoshiDao.searchAllData();
                    initData(allentity);
                    Toast.makeText(HongkaoshiActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        //搜索界面的数据
        else{
            upData(searchentity);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchInfoByName();
                    Toast.makeText(HongkaoshiActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        return true;
    }

    //根据村名查询信息
    private void searchInfoByName() {
        String village = search_edit.getText().toString();
        isSearch=true;
        searchentity = sec_hongkaoshiDao.searchData(village);
        initData(searchentity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isSearch=false;
    }

    //上传未上传的数据
    private void upData(final List<Sec_HongkaoshiEntity> entites) {
        for(int i=0;i<entites.size();i++){
            if("0".equals(entites.get(i).getState())){
                Map<String,Object> map=new HashMap<>();

                String photopath = entites.get(i).getPhotopath();
                String[] imageNames = photopath.split(" ");
                Log.i("TAG", "upData: "+imageNames.length);
                File[] files=new File[imageNames.length];
                for(int j=0;j<imageNames.length;j++){
                    File file=new File(photoFoloder+File.separator+imageNames[j]+".jpg");
                    files[j]=file;
                }
                map.put("pics",files);
                map.put("id",entites.get(i).getId());
                map.put("name",entites.get(i).getName());
                map.put("tel",entites.get(i).getTel());
                map.put("xiaoguo",entites.get(i).getXiaoguo());
                map.put("createtime",entites.get(i).getCreatetime());
                map.put("detail",entites.get(i).getDetail());
                map.put("technician",entites.get(i).getTechnician());
                final int finalI = i;
                XUtil.Post(API_Data.HongkaoshiUP,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if(!TextUtils.isEmpty(result)){
                            //修改该条目数据库中的状态
                            int len = sec_hongkaoshiDao.updateStateByFarmer("1", entites.get(finalI).getId());
                            if(len>0){
                                Log.i("TAG", "len 上传成功: "+len);
                            }else{
                                Log.i("TAG", "len 上传失败: "+len);
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                        Log.i("TAG", "onError: ");
                    }
                });
            }
        }
    }
}