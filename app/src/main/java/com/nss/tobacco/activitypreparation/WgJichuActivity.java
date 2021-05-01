package com.nss.tobacco.activitypreparation;

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
import com.nss.tobacco.daos.JichuSheshiDao;
import com.nss.tobacco.entity.JichuSheshi;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.DividerItemDecoration;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.XUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WgJichuActivity extends AppCompatActivity implements View.OnClickListener {
    private JichuSheshiDao jichuSheshiDao = null;

    private List<JichuSheshi> allentity; //所有数据
    private List<JichuSheshi> searchentity;//查询结果数据

    private ImageView imageViewback;
    private TextView tvadd;
    private TextView tvupdata;
    private Spinner spinneryear;
    private EditText editTextsearch;
    private Button searchButton;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;

    //判断是否查询的数据
    private boolean isSearch = false;

    //发送延迟更新数据库
    private Handler mHandler;
    private String photoFoloder = CommonUtil.getSDPath() + File.separator + "jichusheshi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wg_jichu);
        jichuSheshiDao = new JichuSheshiDao(this);
        allentity = jichuSheshiDao.searchAllData();
        mHandler = new Handler();

        initView();
        initData(allentity);
        initListener();
    }

    //初始化view
    private void initView() {
        //标题栏
        imageViewback = (ImageView) findViewById(R.id.back_item);
        TextView textViewtitle = (TextView) findViewById(R.id.textView_item);
        textViewtitle.setText("基础设施");
        tvadd = (TextView) findViewById(R.id.add_item);
        tvupdata = (TextView) findViewById(R.id.up_item);
        //搜索栏
        spinneryear = (Spinner) findViewById(R.id.frm_first_time_spinner);
        mySpinner();

        editTextsearch = (EditText) findViewById(R.id.frm_first_edittext);
        editTextsearch.setHint("请输入您要查询的村名");
        searchButton = (Button) findViewById(R.id.search_button_item);

        recyclerView = (RecyclerView) findViewById(R.id.frm_first_wg_jichu_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.frm_first_wg_jichu_swipe);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
    }

    private void initData(List<JichuSheshi> entites) {
        recyclerView.setAdapter(new CommonAdapter<JichuSheshi>(this, R.layout.item_detailmsg_recyclerview,entites) {
            @Override
            public void convert(ViewHolder holder, JichuSheshi mJichuSheshi) {
                holder.setText(R.id.id_num,mJichuSheshi.getCreatetime());
                holder.setText(R.id.id_farmer,mJichuSheshi.getVillage());

                if("0".equals(mJichuSheshi.getState())){
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
                        intent.setClass(WgJichuActivity.this, Msg_WgJichuActivity.class);

                        if(!isSearch){
                            intent.putExtra("jichusheshiInfo", allentity.get(position));
                        }else{
                            intent.putExtra("jichusheshiInfo", searchentity.get(position));
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
        //搜索栏
        setMyEditLitsener();
        searchButton.setOnClickListener(this);
        //设置下拉刷新监听
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSearch=false;
                        editTextsearch.setText("");
                        allentity=jichuSheshiDao.searchAllData();
                        initData(allentity);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });
    }

    //设置输入监听
    private void setMyEditLitsener() {
        editTextsearch.addTextChangedListener(new TextWatcher() {
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

    //自定义spinner
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        Spinner spinner = (Spinner) findViewById(R.id.frm_first_time_spinner);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            case R.id.add_item:
                Intent intent = new Intent(WgJichuActivity.this, WgJichuAddActivity.class);
                startActivity(intent);
                break;
            case R.id.up_item:
                Toast.makeText(this, "正在上传", Toast.LENGTH_SHORT).show();
                upLoad();
                break;
            case R.id.search_button_item:
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
                    editTextsearch.setText("");
                    allentity=jichuSheshiDao.searchAllData();
                    initData(allentity);
                    Toast.makeText(WgJichuActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(WgJichuActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
                }
            }, 1000);
        }
        return true;
    }

    //根据村名查询信息
    private void searchInfoByName() {
        String village = editTextsearch.getText().toString();
        isSearch=true;
        searchentity = jichuSheshiDao.searchData(village);
        initData(searchentity);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        isSearch=false;
    }

    //上传未上传的数据
    private void upData(final List<JichuSheshi> entites) {
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
                map.put("year",entites.get(i).getYear());
                map.put("village",entites.get(i).getVillage());
                map.put("kaofangnum",entites.get(i).getKaofangnum());
                map.put("jijingnum",entites.get(i).getJijingnum());
                map.put("createtime",entites.get(i).getCreatetime());
                map.put("technician",entites.get(i).getTechnician());
                map.put("detail",entites.get(i).getDetail());
                final int finalI = i;
                XUtil.Post(API_Data.JichushesheUP,map, new MyCallBack<String>() {
                    @Override
                    public void onSuccess(String result) {
                        super.onSuccess(result);
                        if(!TextUtils.isEmpty(result)){
                            //修改该条目数据库中的状态
                            int len = jichuSheshiDao.updateStateByVillage("1", entites.get(finalI).getId());
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
