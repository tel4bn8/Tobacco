package com.nss.tobacco.activitymanagement;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.nss.tobacco.daos.Sec_TianjianpingguDao;
import com.nss.tobacco.entity.Sec_TianjianpingguEntity;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.DividerItemDecoration;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@ContentView(R.layout.activity_tianjianpinggu)
public class TianjianpingguActivity extends AppCompatActivity implements View.OnClickListener {
    private Sec_TianjianpingguDao sec_tianjianpingguDao = null;

    private List<Sec_TianjianpingguEntity> allentity; //所有数据
    private List<Sec_TianjianpingguEntity> searchentity;//查询结果数据

    @ViewInject(R.id.textView_item)
    private TextView textView;
    @ViewInject(R.id.back_item)
    private ImageView back_img;

    @ViewInject(R.id.add_item)
    private TextView add_text;
    @ViewInject(R.id.up_item)
    private TextView upload;
    @ViewInject(R.id.year_item)
    private Spinner year_spinner;
    @ViewInject(R.id.searchbox)
    private EditText search_edit;
    @ViewInject(R.id.search_item)
    private Button search_btn;
    @ViewInject(R.id.second_tjpg_swipe)
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @ViewInject(R.id.second_tjpg_recyclerview)
    private RecyclerView recyclerView;

    //判断是否查询的数据
    private boolean isSearch = false;

    //发送延迟更新数据库
    private Handler mHandler;
    private String photoFoloder = CommonUtil.getSDPath() + File.separator + "tianjianpinggu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        sec_tianjianpingguDao = new Sec_TianjianpingguDao(this);
        allentity = sec_tianjianpingguDao.searchAllData();
        mHandler = new Handler();

        initView();
        initData(allentity);
        setLitsener();
    }

    private void initData(List<Sec_TianjianpingguEntity> entites) {
        recyclerView.setAdapter(new CommonAdapter<Sec_TianjianpingguEntity>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, Sec_TianjianpingguEntity mSec_TianjianpingguEntity) {
                holder.setText(R.id.id_num,mSec_TianjianpingguEntity.getCreatetime());
                holder.setText(R.id.id_farmer,mSec_TianjianpingguEntity.getFarmer());

                if("0".equals(mSec_TianjianpingguEntity.getState())){
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
                        intent.setClass(TianjianpingguActivity.this, Sec_msg_TianjianpingguActivity.class);

                        if(!isSearch){
                            intent.putExtra("tianjianpingguInfo", allentity.get(position));
                        }else{
                            intent.putExtra("tianjianpingguInfo", searchentity.get(position));
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
        textView.setText("田间评估");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mySpinner();
    }

    private void setLitsener() {
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        upload.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch=false;
                        search_edit.setText("");
                        allentity=sec_tianjianpingguDao.searchAllData();
                        initData(allentity);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    //自定义spinner
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
                intent.setClass(this, Add_TianjianpingguActivity.class);
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
                    allentity=sec_tianjianpingguDao.searchAllData();
                    initData(allentity);
                    Toast.makeText(TianjianpingguActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(TianjianpingguActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        return true;
    }

    //根据村名查询信息
    private void searchInfoByName() {
        String village = search_edit.getText().toString();
        isSearch=true;
        searchentity = sec_tianjianpingguDao.searchData(village);
        initData(searchentity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isSearch=false;
    }

    //上传未上传的数据
    private void upData(final List<Sec_TianjianpingguEntity> entites) {
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
                map.put("farmer",entites.get(i).getFarmer());
                map.put("area",entites.get(i).getArea());
                map.put("zhunum",entites.get(i).getZhunum());
                map.put("leafnum",entites.get(i).getLeafnum());
                map.put("one",entites.get(i).getOne());
                map.put("sum",entites.get(i).getSum());
                map.put("createtime",entites.get(i).getCreatetime());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                XUtil.Post(API_Data.TianjianpingguUP,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if(!TextUtils.isEmpty(result)){
                            //修改该条目数据库中的状态
                            int len = sec_tianjianpingguDao.updateStateByFarmer("1", entites.get(finalI).getId());
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
