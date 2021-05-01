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
import com.nss.tobacco.daos.Sec_DadingDao;
import com.nss.tobacco.daos.Sec_GuangaiDao;
import com.nss.tobacco.daos.Sec_JiemoDao;
import com.nss.tobacco.entity.Sec_DadingEntity;
import com.nss.tobacco.entity.Sec_GuangaiEntity;
import com.nss.tobacco.entity.Sec_JiemoEntity;
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

public class DadingYiyaActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTitle;
    private ImageView back_img;

    private TextView add_text;
    private TextView up_text;

    private Spinner year_spinner,state_spinner;
    private EditText search_edit;
    private Button search_btn;


    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipRefresh;

    private Sec_DadingDao mDao = null;
    //查询到的所有数据
    private List<Sec_DadingEntity> allEntites;
    private List<Sec_DadingEntity> searchEntities;
    private List<Sec_DadingEntity> stateEntities;

    //全部数据或搜索 标志位
    private boolean isSearch = false;
    //采集状态 标志位
    private boolean isSelectState =false;

    //发送延迟更新数据库
    private Handler mHandler;
    private String photoFoloder= CommonUtil.getSDPath() + File.separator + "DaDing";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dading_yiya);
        mDao = new Sec_DadingDao(this);
        mHandler=new Handler();
        //查询所有实体类信息
        allEntites = mDao.searchAll();
        initView();
        initData(allEntites);
        setLitsener();//监听事件
    }
    private void initView() {
        //返回按钮
        back_img = (ImageView) findViewById(R.id.back_item);
        //标题
        tvTitle = (TextView)findViewById(R.id.textView_item);
        tvTitle.setText("打顶抑芽");
        //添加
        add_text = (TextView) findViewById(R.id.add_item);
        //添加
        up_text = (TextView) findViewById(R.id.up_item);
        //年度选择
        year_spinner = (Spinner) findViewById(R.id.year_item);
        state_spinner = (Spinner) findViewById(R.id.frm_first_state_spinner);

        //搜索输入框
        search_edit = (EditText) findViewById(R.id.searchbox);
        //搜索按钮
        search_btn = (Button) findViewById(R.id.search_item);

        mRecyclerView= (RecyclerView) findViewById(R.id.second_tjgl_dading_recyclerView);
        mSwipRefresh= (SwipeRefreshLayout) findViewById(R.id.second_tjgl_dading_swipRefresh);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        //初始化下拉刷新控件的颜色改变
        mSwipRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mySpinner();
    }
    //初始化数据  跳到主界面进行查询数据库并展示
    private void initData(List<Sec_DadingEntity> entites) {
        mRecyclerView.setAdapter(new CommonAdapter<Sec_DadingEntity>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, Sec_DadingEntity entity) {
                holder.setText(R.id.id_num,entity.getDadingtime());
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
                        intent.setClass(DadingYiyaActivity.this, Sec_msg_DaDingActivity.class);
                        if (!isSearch&&!isSelectState){
                            intent.putExtra("info", allEntites.get(position));
                        }else if(isSearch&&!isSelectState){
                            intent.putExtra("info", searchEntities.get(position));
                        }else if(!isSearch&&isSelectState){
                            intent.putExtra("info", stateEntities.get(position));
                        }
                        startActivity(intent);
                    }
                });


            }

        });
    }
    private void setLitsener(){
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        up_text.setOnClickListener(this);
        mSwipRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch = false;
                        isSelectState=false;
                        //采集状态设置默认值
                        state_spinner.setSelection(0);
                        //搜索框清空
                        search_edit.setText("");
                        allEntites=mDao.searchAll();
                        initData(allEntites);
                        mSwipRefresh.setRefreshing(false);
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
                intent.setClass(this, Add_DadingActivity.class);
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
    //自定义spinner,并填充数据
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        year_spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.caijizhuangtai, R.layout.item_myspinner);
        state_spinner.setAdapter(stateAdapter);
    }
    @Override
    public void onBackPressed() {
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
                    for(Sec_DadingEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            allCount++;
                        }
                    }
                    if(allCount==searchEntities.size()){
                        Toast.makeText(DadingYiyaActivity.this, "上传成功", Toast.LENGTH_LONG).show();
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
                    for(Sec_DadingEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            searchCount++;
                        }
                    }
                    if(searchCount==searchEntities.size()){
                        Toast.makeText(DadingYiyaActivity.this, "上传成功", Toast.LENGTH_LONG).show();
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
                    for(Sec_DadingEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            stateCount++;
                        }
                    }
                    if(stateCount==searchEntities.size()){
                        Toast.makeText(DadingYiyaActivity.this, "上传成功", Toast.LENGTH_LONG).show();
                    }
                }
            }, 5000);
        }
        return true;
    }
    //上传未上传的数据
    private void selectState(final List<Sec_DadingEntity> entites) {

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
                map.put("xianleitime",entites.get(i).getXianleitime());
                map.put("dadingtime",entites.get(i).getDadingtime());
                map.put("dijiaoyetime",entites.get(i).getYouhuatime());
                map.put("leaf",entites.get(i).getYouhuashu());
                map.put("yiyaji",entites.get(i).getYiyaji());
                map.put("zhangshi",entites.get(i).getZhangshi());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                XUtil.Post(API_Data.DADINGYIYA,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (!TextUtils.isEmpty(result)&&"1".equals(result)) {
                            Log.i("TAG", "onSuccess: "+result);
                            int len = mDao.updateStateById("1", entites.get(finalI).getId());
                            if(len>0) {
                                Log.i("TAG", "onSuccess: "+len);
                            }else{
                                Toast.makeText(DadingYiyaActivity.this, "上传失败,请检查网络", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                        Log.i("TAG", "onError: ");
                        Toast.makeText(DadingYiyaActivity.this, "上传失败,请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}
