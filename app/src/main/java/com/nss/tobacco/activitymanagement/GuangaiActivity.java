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
import com.nss.tobacco.daos.Sec_GuangaiDao;
import com.nss.tobacco.daos.Sec_ZhibaoDao;
import com.nss.tobacco.entity.Sec_GuangaiEntity;
import com.nss.tobacco.entity.Sec_XueFeiEntity;
import com.nss.tobacco.entity.Sec_ZhibaoEntity;
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

@ContentView(R.layout.activity_guangai)
public class GuangaiActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.textView_item)
    private TextView textView;

    @ViewInject(R.id.back_item)
    private ImageView back_img;

    @ViewInject(R.id.add_item)
    private TextView add_text;
    @ViewInject(R.id.up_item)
    private TextView up_text;

    @ViewInject(R.id.year_item)
    private Spinner year_spinner;
    @ViewInject(R.id.frm_first_state_spinner)
    private Spinner state_spinner;
    @ViewInject(R.id.searchbox)
    private EditText search_edit;
    @ViewInject(R.id.search_item)
    private Button search_btn;

    @ViewInject(R.id.second_tjgl_guangai_recylerview)
    private RecyclerView mRecyclerView;

    @ViewInject(R.id.second_tjgl_guangai_swipfreshLayouts)
    SwipeRefreshLayout swipeRefreshLayout;

    //????????????????????????
    private CommonAdapter<String> adapter;

    private List<Sec_GuangaiEntity> allEntites;
    private List<Sec_GuangaiEntity> searchEntities;
    private List<Sec_GuangaiEntity> stateEntities;
    private Sec_GuangaiDao mDao = null;
    //????????????????????? ?????????
    private boolean isSearch = false;
    //???????????? ?????????
    private boolean isSelectState =false;

    //???????????????????????????
    private Handler mHandler;
    private String photoFoloder= CommonUtil.getSDPath() + File.separator + "GuanGai";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mDao = new Sec_GuangaiDao(this);
        mHandler=new Handler();
        //???????????????????????????
        allEntites = mDao.searchAll();
        initView();
        initData(allEntites);
        setLitsener();//????????????
    }

    private void initView() {
        textView.setText("??????");
        //?????????????????????
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //RecyclerView?????????
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mySpinner();
    }
    //???????????????  ?????????????????????????????????????????????
    private void initData(List<Sec_GuangaiEntity> entites) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new CommonAdapter<Sec_GuangaiEntity>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, Sec_GuangaiEntity entity) {
                holder.setText(R.id.id_num,entity.getWorktime());
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
                        intent.setClass(GuangaiActivity.this, Sec_msg_GuanGaiActivity.class);
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
        //RecyclerView?????????
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }
    private void setLitsener(){
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        search_btn.setOnClickListener(this);
        up_text.setOnClickListener(this);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch = false;
                        isSelectState=false;
                        //???????????????????????????
                        state_spinner.setSelection(0);
                        //???????????????
                        search_edit.setText("");
                        allEntites=mDao.searchAll();
                        initData(allEntites);
                        swipeRefreshLayout.setRefreshing(false);
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
                intent.setClass(this, Add_GuangaiActivity.class);
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
    //?????????spinner
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        year_spinner.setAdapter(adapter);

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this, R.array.caijizhuangtai, R.layout.item_myspinner);
        state_spinner.setAdapter(stateAdapter);

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
                }
            }, 5000);
            int allCount=0;
            for(Sec_GuangaiEntity info:searchEntities){
                if("1".equals(info.getState())){
                    allCount++;
                }
            }
            if(allCount==searchEntities.size()){
                Toast.makeText(GuangaiActivity.this, "????????????", Toast.LENGTH_LONG).show();
            }

        }
        //?????????????????????
        else if(isSearch&&!isSelectState){
            selectState(searchEntities);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchByName();
                    int searchCount=0;
                    for(Sec_GuangaiEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            searchCount++;
                        }
                    }
                    if(searchCount==searchEntities.size()){
                        Toast.makeText(GuangaiActivity.this, "????????????", Toast.LENGTH_LONG).show();
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
                    for(Sec_GuangaiEntity info:searchEntities){
                        if("1".equals(info.getState())){
                            stateCount++;
                        }
                    }
                    if(stateCount==searchEntities.size()){
                        Toast.makeText(GuangaiActivity.this, "????????????", Toast.LENGTH_LONG).show();
                    }
                }
            }, 5000);
        }
        return true;
    }
    //????????????????????????
    private void selectState(final List<Sec_GuangaiEntity> entites) {

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
                map.put("way",entites.get(i).getWay());
                map.put("area",entites.get(i).getArea());
                map.put("createtime",entites.get(i).getWorktime());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                XUtil.Post(API_Data.GUANGAI,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if (!TextUtils.isEmpty(result)&&"1".equals(result)) {
                            Log.i("TAG", "onSuccess: "+result);
                            int len = mDao.updateStateById("1", entites.get(finalI).getId());
                            if(len>0) {
                                Log.i("TAG", "onSuccess: "+len);
                            }else{
                                Toast.makeText(GuangaiActivity.this, "????????????,???????????????", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        super.onError(ex, isOnCallback);
                        Log.i("TAG", "onError: ");
                        Toast.makeText(GuangaiActivity.this, "????????????,???????????????", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


}
