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
import com.nss.tobacco.daos.Sec_JiFeiDao;
import com.nss.tobacco.daos.Sec_XueFeiDao;
import com.nss.tobacco.entity.Sec_JiFeiEntity;
import com.nss.tobacco.entity.Sec_JiemoEntity;
import com.nss.tobacco.entity.Sec_XueFeiEntity;
import com.nss.tobacco.entity.Sec_YizaiEntity;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.DividerItemDecoration;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.NetworkHelper;
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

@ContentView(R.layout.activity_xuefei)
public class XuefeiActivity extends AppCompatActivity implements View.OnClickListener {

    private CommonAdapter<String> adapter;
    @ViewInject(R.id.textView_item)
    TextView textView;
    @ViewInject(R.id.back_item)
    ImageView back_img;
    @ViewInject(R.id.add_item)
    TextView add_text;
    @ViewInject(R.id.up_item)
    TextView up_text;

    @ViewInject(R.id.year_item)
    Spinner year_spinner;
    @ViewInject(R.id.frm_first_state_spinner)
    Spinner state_spinner;

    @ViewInject(R.id.searchbox)
    EditText search_edit;
    @ViewInject(R.id.search_item)
    Button search_btn;

    @ViewInject(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshWidget;
    @ViewInject(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    private Sec_XueFeiDao mDao = null;

    //????????????????????????
    private List<Sec_XueFeiEntity> allEntites;
    private List<Sec_XueFeiEntity> searchEntities;
    private List<Sec_XueFeiEntity> stateEntities;

    //????????????????????? ?????????
    private boolean isSearch = false;
    //???????????? ?????????
    private boolean isSelectState =false;

    //???????????????????????????
    private Handler mHandler;
    private String photoFoloder= CommonUtil.getSDPath() + File.separator + "XueFei";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mDao = new Sec_XueFeiDao(this);
        mHandler=new Handler();
        //???????????????????????????
        allEntites = mDao.searchAll();
        initView();
        initData(allEntites);
        setLitsener();//????????????

    }
    private void initView() {
        textView.setText("??????");
        //??????????????????????????????????????????
        mSwipeRefreshWidget.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView?????????
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mySpinner();
    }
    //???????????????  ?????????????????????????????????????????????
    private void initData(List<Sec_XueFeiEntity> entites) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CommonAdapter<Sec_XueFeiEntity>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, Sec_XueFeiEntity entity) {
                holder.setText(R.id.id_num,entity.getCreatetime());
                holder.setText(R.id.id_farmer,entity.getFarmer());

                if("0".equals(entity.getState())){
                    holder.setText(R.id.id_state,"?????????");
                }else{
                    holder.setText(R.id.id_state,"?????????");
                }
            }
            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.setOnClickListener(R.id.id_ll_item,new View.OnClickListener() {
                    @Override
                    public void onClick(View v){
                        Intent intent = new Intent();
                        intent.setClass(XuefeiActivity.this, Sec_msg_XueFeiActivity.class);
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
        //RecyclerView?????????
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }
    //?????????spinner
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        year_spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.caijizhuangtai, R.layout.item_myspinner);
        state_spinner.setAdapter(stateAdapter);
    }
    private void setLitsener() {
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        up_text.setOnClickListener(this);
        mSwipeRefreshWidget.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch=false;
                        search_edit.setText("");
                        allEntites=mDao.searchAll();
                        initData(allEntites);
                        mSwipeRefreshWidget.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        //????????????Spinner??????
        state_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //??????????????????
                search_edit.setText("");
                isSearch=false;
                isSelectState=true;
                String selectContent = parent.getItemAtPosition(position).toString();
                switch (selectContent) {
                    case "?????????":
                        stateEntities = mDao.searchByState("1");
                        break;
                    case "?????????":
                        stateEntities = mDao.searchByState("0");
                        break;
                    case "??????":
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


    //???????????????????????????????????????
    @Override
    public void onClick(View v) {
        //??????????????????
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                isSearch = false;
                isSelectState=false;
                finish();
                break;
            case R.id.add_item:
                intent.setClass(this, Add_XuefeiActivity.class);
                startActivity(intent);
                break;
            //??????????????????
            case R.id.search_item:
                searchByName();
                break;
            case R.id.up_item:
                if(NetworkHelper.isNetworkAvailable(this)){
                    Toast.makeText(this, "????????????", Toast.LENGTH_LONG).show();
                    upLoad();
                }else{
                    Toast.makeText(this, "????????????,?????????????????????WIFI", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    //????????????????????????????????????
    private void searchByName() {
        String name = search_edit.getText().toString();
        //RecyclerView?????????????????? ?????????
        isSearch = true;
        isSelectState=false;
        state_spinner.setSelection(0);
        searchEntities = mDao.searchByName(name);
        initData(searchEntities);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //???????????????????????????
        isSearch = false;
        isSelectState=false;
    }

    //??????????????????
    private boolean upLoad(){
        //????????????????????????
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
                    for(Sec_XueFeiEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            allCount++;
                        }
                    }
                    if(allCount==searchEntities.size()){
                        Toast.makeText(XuefeiActivity.this, "????????????", Toast.LENGTH_LONG).show();
                    }
                }
            }, 5000);

        }
        //?????????????????????
        else if(isSearch&&!isSelectState){
            selectState(searchEntities);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchByName();
                    int searchCount=0;
                    for(Sec_XueFeiEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            searchCount++;
                        }
                    }
                    if(searchCount==searchEntities.size()){
                        Toast.makeText(XuefeiActivity.this, "????????????", Toast.LENGTH_LONG).show();
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
                        case "?????????":
                            stateEntities = mDao.searchByState("1");
                            break;
                        case "?????????":
                            stateEntities = mDao.searchByState("0");
                            break;
                        case "??????":
                            stateEntities = mDao.searchAll();
                            break;
                    }
                    initData(stateEntities);
                    int stateCount=0;
                    for(Sec_XueFeiEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            stateCount++;
                        }
                    }
                    if(stateCount==searchEntities.size()){
                        Toast.makeText(XuefeiActivity.this, "????????????", Toast.LENGTH_LONG).show();
                    }
                }
            }, 5000);
        }
        return true;
    }


    //????????????????????????
    private void selectState(final List<Sec_XueFeiEntity> entites) {

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
                map.put("type",entites.get(i).getType());
                map.put("num",entites.get(i).getNum());
                map.put("area",entites.get(i).getArea());
                map.put("createtime",entites.get(i).getCreatetime());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                XUtil.Post(API_Data.XUEFEI,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (!TextUtils.isEmpty(result)&&"1".equals(result)) {
                            Log.i("TAG", "onSuccess: "+result);
                            int len = mDao.updateStateById("1", entites.get(finalI).getId());
                            if(len>0) {
                                Log.i("TAG", "onSuccess: "+len);
                            }else{
                                Toast.makeText(XuefeiActivity.this, "????????????,???????????????", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                        Log.i("TAG", "onError: ");
                        Toast.makeText(XuefeiActivity.this, "????????????,???????????????", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }


}