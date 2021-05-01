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
import android.widget.AdapterView;
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
import com.nss.tobacco.daos.Sec_ZaihaitongjiDao;
import com.nss.tobacco.entity.Sec_Zaihaitongji;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.DividerItemDecoration;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.XUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZaihaiTongjiActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_ZaihaitongjiDao sec_zaihaitongjiDao = null;

    private TextView textView;
    private ImageView back_img;
    private TextView add_text,upload;
    private Spinner year_spinner;
    private EditText search_edit;
    private Button search_btn;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //查询到的所有数据
    private List<Sec_Zaihaitongji> allEntites;
    private List<Sec_Zaihaitongji> searchEntities;

    //判断是否查询的数据
    private boolean isSearch=false;

    //发送延迟更新数据库
    private Handler mHandler;
    private String photoFoloder= CommonUtil.getSDPath() + File.separator + "zaihaitongji";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zaihai_tongji);

        sec_zaihaitongjiDao = new Sec_ZaihaitongjiDao(this);
        allEntites = sec_zaihaitongjiDao.searchAllData();
        mHandler = new Handler();

        initView();
        initData(allEntites);
        setLitsener();//监听事件
    }
    protected void initData(List<Sec_Zaihaitongji> entites) {
        recyclerView.setAdapter(new CommonAdapter<Sec_Zaihaitongji>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, Sec_Zaihaitongji mSec_Zaihaitongji) {
                holder.setText(R.id.id_num,mSec_Zaihaitongji.getCreatetime());
                holder.setText(R.id.id_farmer,mSec_Zaihaitongji.getFarmer());

                if("0".equals(mSec_Zaihaitongji.getState())){
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
                        intent.setClass(ZaihaiTongjiActivity.this, Sec_msg_ZaihaiTongjiActivity.class);

                        if(!isSearch){
                            intent.putExtra("info", allEntites.get(position));
                        }else{
                            intent.putExtra("info", searchEntities.get(position));
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
        textView = (TextView)findViewById(R.id.textView_item);
        textView.setText("自然灾害统计");
        back_img = (ImageView) findViewById(R.id.back_item);
        add_text = (TextView) findViewById(R.id.add_item);
        upload = (TextView) findViewById(R.id.up_item);
        year_spinner = (Spinner) findViewById(R.id.year_item);
        search_edit = (EditText) findViewById(R.id.searchbox);
        search_btn = (Button) findViewById(R.id.search_item);
        recyclerView = (RecyclerView) findViewById(R.id.second_zhtj_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.second_zhtj_swipe);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mySpinner();
    }

    private void setLitsener() {
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        upload.setOnClickListener(this);
        setMySpinnerLitsener();
        setMyEditLitsener();
        search_btn.setOnClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch=false;
                        search_edit.setText("");
                        allEntites=sec_zaihaitongjiDao.searchAllData();
                        initData(allEntites);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
    }
    //搜索框editText监听
    private void setMyEditLitsener() {
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //年度spinner监听
    private void setMySpinnerLitsener() {
        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //自定义spinner
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.year_item, R.layout.item_myspinner);
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
                intent.setClass(this, Add_ZaihaiTongjiActivity.class);
                startActivity(intent);
                break;
            case R.id.up_item:
                Toast.makeText(this,"正在上传",Toast.LENGTH_LONG).show();
                upLoad();
                break;
            case R.id.year_item:

                break;
            case R.id.search_item:
                searchByName();
                break;
            default:
                break;
        }

    }
    //通过名字查询苗床追肥信息
    private void searchByName() {
        String name = search_edit.getText().toString();
        //RecyclerView列表里的数据 标志位
        isSearch=true;
        searchEntities = sec_zaihaitongjiDao.searchData(name);
        initData(searchEntities);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isSearch=false;
    }

    //上传列表数据
    private boolean upLoad(){
        //非搜索的界面数据
        if(!isSearch){
            upData(allEntites);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isSearch=false;
                    search_edit.setText("");
                    allEntites=sec_zaihaitongjiDao.searchAllData();
                    initData(allEntites);
                    Toast.makeText(ZaihaiTongjiActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        //搜索界面的数据
        else{
            upData(searchEntities);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchByName();
                    Toast.makeText(ZaihaiTongjiActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        return true;
    }

    //上传未上传的数据
    private void upData(final List<Sec_Zaihaitongji> entites) {

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
                map.put("type",entites.get(i).getType());
                map.put("area1",entites.get(i).getArea1());
                map.put("area2",entites.get(i).getArea2());
                map.put("lose",entites.get(i).getLose());
                map.put("createtime",entites.get(i).getCreatetime());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                XUtil.Post(API_Data.ZaihaitongjiUP,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if(!TextUtils.isEmpty(result)){
                            //修改该条目数据库中的状态
                            int len = sec_zaihaitongjiDao.updateStateByFarmer("1", entites.get(finalI).getId());
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

