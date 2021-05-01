package com.nss.tobacco.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.Sec_GuangaiEntity;
import com.nss.tobacco.entity.Sec_MiaoqiguanliEntity;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.SolarTerms;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@ContentView(R.layout.activity_sec_msg__miao_qi_guan_li)
public class Sec_msg_MiaoQiGuanLiActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.msg_back_item)
    ImageView imageView;
    @ViewInject(R.id.msg_textView)
    TextView tvTitle;

    @ViewInject(R.id.second_ym_mq_msg_gridView)
    GridView gvGridView;
    @ViewInject(R.id.second_ym_mq_msg_farmer)
    TextView tvFarmer;
    @ViewInject(R.id.second_ym_mq_msg_miaoqi)
    TextView tvMiaoqi;
    @ViewInject(R.id.second_ym_mq_msg_date)
    TextView tvData;
    @ViewInject(R.id.second_ym_mq_msg_miaoqing)
    TextView tvMiaoQing;
    @ViewInject(R.id.second_ym_mq_msg_zhuangmiaolv)
    TextView tvZhungmiaolv;
    @ViewInject(R.id.second_ym_mq_msg_detail)
    TextView tvDetail;
    @ViewInject(R.id.second_ym_mq_msg_btnBack)
    TextView btnBack;


    private Sec_MiaoqiguanliEntity entityInfo;
    private String path;
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
        entityInfo = (Sec_MiaoqiguanliEntity) getIntent().getSerializableExtra("info");

        //初始化数据
        initData();
        setListener();
    }
    private void setListener() {
        btnBack.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }
    private void initData() {
        tvTitle.setText("苗期管理");
        path= CommonUtil.getSDPath()+ File.separator+"MiaoQiGuanLi";
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        tvFarmer.setText(entityInfo.getFarmer());
        tvMiaoqi.setText(entityInfo.getMiaoqi());
        tvData.setText(entityInfo.getCreatetime());
        tvMiaoQing.setText(entityInfo.getMiaoqing());
        tvZhungmiaolv.setText(entityInfo.getZhuangmiaolv());
        tvDetail.setText(entityInfo.getDetail());
        String photoPath = entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gvGridView.setAdapter(adapter);
        }
    }
    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.second_ym_mq_msg_btnBack:
                finish();
                break;
            case R.id.msg_back_item:
                finish();
                break;
            default:
                break;
        }
    }
}
