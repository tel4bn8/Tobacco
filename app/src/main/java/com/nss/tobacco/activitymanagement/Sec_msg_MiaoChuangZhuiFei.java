package com.nss.tobacco.activitymanagement;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_MiaoChuangZhuiFei extends AppCompatActivity implements View.OnClickListener{

    private GridView  gridViewPhoto;//苗床追肥详情页面存放图片的GridView
    private TextView tvFarmer;
    private TextView tvType;
    private TextView tvNum;
    private TextView tvWay;
    private TextView tvArea;
    private TextView tvTime;
    private TextView tvDetail;
    private Button btnBack;
    private TextView tvTitle;
    private ImageView ivBackTilte;
    private Sec_MiaoChuangZhuiFeiEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"miaochuangzhuifei";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__miao_chuang_zhui_fei);
        entityInfo= (Sec_MiaoChuangZhuiFeiEntity) getIntent().getSerializableExtra("info");
        initView();
        //初始化控件数据
        initData();
    }
    //初始化控件
    private void initView(){
        ivBackTilte = (ImageView) findViewById(R.id.msg_back_item);
        tvTitle = (TextView) findViewById(R.id.msg_textView);
        gridViewPhoto = (GridView) findViewById(R.id.second_ym_mczf_msg_gridView);
        tvFarmer = (TextView) findViewById(R.id.second_ym_mczf_msg_farmer);
        tvType = (TextView) findViewById(R.id.second_ym_mczf_msg_type);
        tvNum = (TextView) findViewById(R.id.second_ym_mczf_msg_num);
        tvWay = (TextView) findViewById(R.id.second_ym_mczf_msg_way);
        tvArea = (TextView) findViewById(R.id.second_ym_mczf_msg_area);
        tvTime = (TextView) findViewById(R.id.second_ym_mczf_msg_time);
        tvDetail = (TextView) findViewById(R.id.second_ym_mczf_msg_detail);
        btnBack = (Button) findViewById(R.id.second_ym_mczf_msg_btnBack);
        btnBack.setOnClickListener(this);
        ivBackTilte.setOnClickListener(this);
    }
    private void initData(){
        tvTitle.setText("苗床追肥");
        photosPath=new ArrayList<>();
        tvFarmer.setText(entityInfo.getFarmer());
        tvType.setText(entityInfo.getType());
        tvNum.setText(entityInfo.getNum());
        tvWay.setText(entityInfo.getWay());
        tvArea.setText(entityInfo.getArea());
        tvTime.setText(entityInfo.getCreatetime());
        tvDetail.setText(entityInfo.getDetail());
        String photoPath=entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gridViewPhoto.setAdapter(adapter);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                 this.finish();
                break;
            case R.id.second_ym_mczf_msg_btnBack:
                this.finish();
                break;
        }
    }
}
