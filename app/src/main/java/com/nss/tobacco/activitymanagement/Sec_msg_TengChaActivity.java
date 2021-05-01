package com.nss.tobacco.activitymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.Sec_TengChaEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__teng_cha)
public class Sec_msg_TengChaActivity extends AppCompatActivity implements View.OnClickListener{
    private GridView photoGridView;
    private TextView tvFarmer;
    private TextView tvArea;
    private TextView tvCreateTime;
    private TextView tvBeizhu;
    private Button btnBack;
    private ImageView ivBack;
    private TextView tvTitle;

    private Sec_TengChaEntity entityInfo;
    private String path;
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__teng_cha);
        //初始化控件
        initView();
        //初始化数据
        initData();
        //控件监听
        initListener();

    }



    //初始化控件
    private void initView() {
        photoGridView=(GridView) findViewById(R.id.second_zdql_tengcha_msg_gridView);
        tvFarmer=(TextView) findViewById(R.id.second_zdql_tengcha_msg_farmer);
        tvArea=(TextView) findViewById(R.id.second_zdql_tengcha_msg_area);
        tvCreateTime=(TextView) findViewById(R.id.second_zdql_tengcha_msg_createtime);
        tvBeizhu=(TextView) findViewById(R.id.second_zdql_tengcha_msg_beizhu);
        btnBack=(Button) findViewById(R.id.second_zdql_tengcha_msg_btnBack);
        ivBack=(ImageView) findViewById(R.id.msg_back_item);
        tvTitle = (TextView) findViewById(R.id.msg_textView);
    }
    private void initData() {
        tvTitle.setText("腾茬");
        path=CommonUtil.getSDPath()+ File.separator+"TengCha";
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        entityInfo = (Sec_TengChaEntity) getIntent().getSerializableExtra("info");
        tvFarmer.setText(entityInfo.getFarmer());
        tvArea.setText(entityInfo.getArea());
        tvCreateTime.setText(entityInfo.getCreateTime());
        tvBeizhu.setText(entityInfo.getDetail());
        String photoPath = entityInfo.getPhotoPath();
        String[] split = photoPath.split(" ");
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            photoGridView.setAdapter(adapter);
        }
    }
    private void initListener() {
        btnBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_zdql_tengcha_msg_btnBack:
                this.finish();
                break;
        }
    }
}
