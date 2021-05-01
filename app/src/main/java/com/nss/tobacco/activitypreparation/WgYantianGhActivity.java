package com.nss.tobacco.activitypreparation;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
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
import com.nss.tobacco.daos.YantianGuihuaDao;
import com.nss.tobacco.entity.YantianGuihua;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.DividerItemDecoration;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.PermissionHelper;
import com.nss.tobacco.utils.XUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WgYantianGhActivity extends AppCompatActivity implements View.OnClickListener{
    private PermissionHelper mPermissionHelper;

    private YantianGuihuaDao yantianGuihuaDao = null;

    private List<YantianGuihua> allentity; //所有数据
    private List<YantianGuihua> searchentity;//查询结果数据

    //判断是否查询的数据
    private boolean isSearch=false;

    //发送延迟更新数据库
    private Handler mHandler;
    private String photoFoloder= CommonUtil.getSDPath() + File.separator + "yantianguihua";

    private ImageView imageViewback;
    private TextView tvadd;
    private TextView tvupdata;
    private Spinner spinneryear;
    private EditText editTextsearch;
    private Button searchButton;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout wgYantianswipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wg_yantian_gh);

        yantianGuihuaDao = new YantianGuihuaDao(this);
        mHandler=new Handler();
        allentity = yantianGuihuaDao.searchAllData();

        mPermissionHelper = new PermissionHelper(this);
        mPermissionHelper.checkPermisson(new PermissionHelper.OnPermissionListener() {
            @Override
            public void onAgreePermission() {
                Toast.makeText(WgYantianGhActivity.this, "同意权限了", Toast.LENGTH_SHORT).show();
                // do something
            }

            @Override
            public void onDeniedPermission() {
                Toast.makeText(WgYantianGhActivity.this, "拒绝权限了", Toast.LENGTH_SHORT).show();
                finish(); // 当界面一定通过权限才能继续，就要加上这行
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);

        initView();
        initData(allentity);
        initListener();
    }

    //初始化数据
    private void initData(List<YantianGuihua> entites) {
        recyclerView.setAdapter(new CommonAdapter<YantianGuihua>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, YantianGuihua mYantianGuihua) {
                holder.setText(R.id.id_num,mYantianGuihua.getModitime());
                holder.setText(R.id.id_farmer,mYantianGuihua.getVillage());

                if("0".equals(mYantianGuihua.getState())){
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
                        intent.setClass(WgYantianGhActivity.this, Msg_WgYantianGhActivity.class);

                        if(!isSearch){
                            intent.putExtra("yantianguihuaInfo", allentity.get(position));
                        }else{
                            intent.putExtra("yantianguihuaInfo", searchentity.get(position));
                        }
                        startActivity(intent);
                    }
                });
            }

        });
        //RecyclerView分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
    }

    //初始化监听
    private void initListener() {
        imageViewback.setOnClickListener(this);
        tvadd.setOnClickListener(this);
        tvupdata.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        wgYantianswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch=false;
                        editTextsearch.setText("");
                        allentity = yantianGuihuaDao.searchAllData();
                        initData(allentity);
                        wgYantianswipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    //初始化view
    private void initView() {
        //标题栏
        imageViewback = (ImageView) findViewById(R.id.back_item);
        TextView textViewtitle = (TextView) findViewById(R.id.textView_item);
        textViewtitle.setText("烟田规划");
        tvadd = (TextView) findViewById(R.id.add_item);
        tvupdata = (TextView) findViewById(R.id.up_item);
        //搜索栏
        spinneryear = (Spinner) findViewById(R.id.frm_first_time_spinner);
        mySpinner();
        editTextsearch = (EditText) findViewById(R.id.frm_first_edittext);
        editTextsearch.setHint("输入查询的村名");
        searchButton = (Button) findViewById(R.id.search_button_item);
        recyclerView = (RecyclerView) findViewById(R.id.frm_first_wg_yantian_gh_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        wgYantianswipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.frm_first_wg_yantian_gh_swipe);
        wgYantianswipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    //自定义spinner
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.year_item, R.layout.item_myspinner);
        spinneryear.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_item:
                finish();
                break;
            case R.id.add_item:
                Intent intent = new Intent(WgYantianGhActivity.this, WgYantianGhAddActivity.class);
                startActivity(intent);
                break;
            case R.id.up_item:
                Toast.makeText(this,"正在上传",Toast.LENGTH_SHORT).show();
                upLoad();
                break;
            case R.id.frm_first_time_spinner:
                break;
            case R.id.search_button_item:
                //搜索监听
                searchMyRecy();
                break;
            default:
                break;
        }
    }

    //搜索功能
    private void searchMyRecy() {
        String name = editTextsearch.getText().toString();
        //RecyclerView列表里的数据 标志位
        isSearch=true;
        searchentity = yantianGuihuaDao.searchData(name);
        initData(searchentity);
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
            upData(allentity);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isSearch=false;
                    editTextsearch.setText("");
                    allentity=yantianGuihuaDao.searchAllData();
                    initData(allentity);
                    Toast.makeText(WgYantianGhActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        //搜索界面的数据
        else{
            upData(searchentity);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    searchMyRecy();
                    Toast.makeText(WgYantianGhActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        return true;
    }

    //上传"未上传的数据"
    private void upData(final List<YantianGuihua> entites) {

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
                map.put("village",entites.get(i).getVillage());
                map.put("year",entites.get(i).getYear());
                map.put("area",entites.get(i).getArea());
                map.put("lng",entites.get(i).getLng());
                map.put("lat",entites.get(i).getLat());
                map.put("dixing",entites.get(i).getDixing());
                map.put("soil",entites.get(i).getSoil());
                map.put("feili",entites.get(i).getFeili());
                map.put("moditime",entites.get(i).getModitime());
                map.put("technician",entites.get(i).getTechnician());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                 XUtil.Post(API_Data.YantianguihuaUP,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if(!TextUtils.isEmpty(result)){
                            //修改该条目数据库中的状态
                            int len = yantianGuihuaDao.updateStateByVillage("1", entites.get(finalI).getId());
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

    @Override
    public void onResume() {
        super.onResume();
        mPermissionHelper.onResume(); // 当界面一定通过权限才能继续，就要加上这行
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}