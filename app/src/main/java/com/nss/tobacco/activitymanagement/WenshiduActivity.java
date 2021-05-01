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
import com.nss.tobacco.daos.Sec_MiaoChuangZhuiFeiDao;
import com.nss.tobacco.daos.Sec_WenshiduDao;
import com.nss.tobacco.daos.Sec_XiaoduInfoDao;
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.entity.Sec_WenshiduEntity;
import com.nss.tobacco.entity.Sec_WenshiduEntity;
import com.nss.tobacco.entity.Sec_XiaoduInfoEntity;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.DividerItemDecoration;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.NetworkHelper;
import com.nss.tobacco.utils.XUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WenshiduActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private ImageView back_img;
    private TextView add_text, up_text;
    private Spinner year_spinner,state_spinner;
    private EditText search_edit;
    private Button search_btn;
    private RecyclerView mRecyclerView;

    private Sec_WenshiduDao mDao = null;

    //查询到的所有数据
    private List<Sec_WenshiduEntity> allEntites;
    //搜索条件的数据
    private List<Sec_WenshiduEntity> searchEntities;
    //采集状态里的数据
    private List<Sec_WenshiduEntity> stateEntities;

    //全部数据或搜索 标志位
    private boolean isSearch = false;
    //采集状态 标志位
    private boolean isSelectState =false;

    private SwipeRefreshLayout mSwipRefreshLayout;

    //发送延迟更新数据库
    private Handler mHandler;
    private String photoFoloder = CommonUtil.getSDPath() + File.separator + "WenShiDuGuanLi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wenshidu);
        initView();
        mDao = new Sec_WenshiduDao(this);
        mHandler = new Handler();
        //查询所有实体类信息
        allEntites = mDao.searchAll();
        initView();
        initData(allEntites);
        setLitsener();//监听事件
    }


    private void initView() {
        textView = (TextView) findViewById(R.id.textView_item);
        textView.setText("温湿度管理");
        back_img = (ImageView) findViewById(R.id.back_item);
        add_text = (TextView) findViewById(R.id.add_item);
        up_text = (TextView) findViewById(R.id.up_item);

        year_spinner = (Spinner) findViewById(R.id.year_item);
        state_spinner= (Spinner) findViewById(R.id.frm_first_state_spinner);
        search_edit = (EditText) findViewById(R.id.searchbox);
        search_btn = (Button) findViewById(R.id.search_item);
        mSwipRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.second_ym_wsdgl_swipeRefresh);
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSwipRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mySpinner();
    }



    //初始化数据  跳到主界面进行查询数据库并展示
    private void initData(List<Sec_WenshiduEntity> entites) {
        mRecyclerView.setAdapter(new CommonAdapter<Sec_WenshiduEntity>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, Sec_WenshiduEntity entity) {
                holder.setText(R.id.id_num,entity.getCreatetime());
                holder.setText(R.id.id_farmer,entity.getFarmer());

                if("0".equals(entity.getState())){
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
                        intent.setClass(WenshiduActivity.this, Sec_msg_WenShiduGunaliActvity.class);
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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    private void setLitsener(){
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        up_text.setOnClickListener(this);
        mSwipRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch = false;
                        isSelectState=false;
                        search_edit.setText("");
                        allEntites=mDao.searchAll();
                        initData(allEntites);
                        mSwipRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        //采集状态Spinner监听
        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //初始化搜索框
                search_edit.setText("");
                isSearch=false;
                isSelectState=true;
                String selectContent = parent.getItemAtPosition(position).toString();
                switch (selectContent) {
                    case "已上传":
                        stateEntities = mDao.searchByState("1");
                        break;
                    case "未上传":
                        stateEntities = mDao.searchByState("0");
                        break;
                    case "全部":
                        stateEntities = mDao.searchAll();
                        break;
                }
                initData(stateEntities);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //自定义spinner
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        year_spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.caijizhuangtai, R.layout.item_myspinner);
        state_spinner.setAdapter(stateAdapter);
    }
    //列表界面的所有控件点击事件
    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                isSearch = false;
                isSelectState=false;
                finish();
                break;
            case R.id.add_item:
                intent.setClass(this, Add_WenshiduActivity.class);
                startActivity(intent);
                break;
            //根据名字搜索
            case R.id.search_item:
                searchByName();
                break;
            case R.id.up_item:
                if(NetworkHelper.isNetworkAvailable(this)){
                    Toast.makeText(this, "正在上传", Toast.LENGTH_LONG).show();
                    upLoad();
                }else{
                    Toast.makeText(this, "上传失败,请检查是否连接WIFI", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }
    //通过名字查询苗床追肥信息
    private void searchByName() {
        String name = search_edit.getText().toString();
        //RecyclerView列表里的数据 标志位
        isSearch = true;
        isSelectState=false;
        state_spinner.setSelection(0);
        searchEntities = mDao.searchByName(name);
        initData(searchEntities);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //标志位设置为初始值
        isSearch = false;
        isSelectState=false;
    }
    //上传列表数据
    private boolean upLoad(){
        //非搜索的界面数据
        if (!isSearch&&!isSelectState){
            selectState(allEntites);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isSearch = false;
                    isSelectState=false;
                    search_edit.setText("");
                    allEntites = mDao.searchAll();
                    initData(allEntites);
                    int allCount=0;
                    for(Sec_WenshiduEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            allCount++;
                        }
                    }
                    if(allCount==searchEntities.size()){
                        Toast.makeText(WenshiduActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    }
                }
            }, 5000);

        }
        //搜索界面的数据
        else if(isSearch&&!isSelectState){
            selectState(searchEntities);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchByName();
                    int searchCount=0;
                    for(Sec_WenshiduEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            searchCount++;
                        }
                    }
                    if(searchCount==searchEntities.size()){
                        Toast.makeText(WenshiduActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    }
                }
            }, 6000);
        }
        else if (!isSearch && isSelectState) {
            selectState(stateEntities);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run(){
                    isSearch = false;
                    isSelectState = true;
                    search_edit.setText("");

                    String selectContent = state_spinner.getSelectedItem().toString();
                    switch (selectContent) {
                        case "已上传":
                            stateEntities = mDao.searchByState("1");
                            break;
                        case "未上传":
                            stateEntities = mDao.searchByState("0");
                            break;
                        case "全部":
                            stateEntities = mDao.searchAll();
                            break;
                    }
                    initData(stateEntities);
                    int stateCount=0;
                    for(Sec_WenshiduEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            stateCount++;
                        }
                    }
                    if(stateCount==searchEntities.size()){
                        Toast.makeText(WenshiduActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    }
                }
            }, 5000);
        }
        return true;
    }
    //上传未上传的数据
    private void selectState(final List<Sec_WenshiduEntity> entites) {

        for(int i=0;i<entites.size();i++){
            if("0".equals(entites.get(i).getState())){
                Map<String,Object> map=new HashMap<>();

                String photopath = entites.get(i).getPhotopath();
                String[] imageNames = photopath.split(" ");
                File[] files=new File[imageNames.length];
                for(int j=0;j<imageNames.length;j++){
                    File file=new File(photoFoloder+File.separator+imageNames[j]+".jpg");
                    files[j]=file;
                }
                map.put("pics",files);
                map.put("id",entites.get(i).getId());
                map.put("farmer",entites.get(i).getFarmer());
                map.put("createtime",entites.get(i).getCreatetime());
                map.put("miaoqing",entites.get(i).getMiaoqing());
                map.put("wendu",entites.get(i).getWendu());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                XUtil.Post(API_Data.WENSHIDUGUANLI,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (!TextUtils.isEmpty(result)&&"1".equals(result)) {
                            //修改该条目数据库中的状态
                            int len = mDao.updateStateByFarmer("1", entites.get(finalI).getId());
                            if(len>0) {
                                Log.i("TAG", "onSuccess: "+len);
                            }else{
                                Toast.makeText(WenshiduActivity.this, "上传失败,请检查网络", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                        Log.i("TAG", "onError: ");
                        Toast.makeText(WenshiduActivity.this, "上传失败,请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }

}
