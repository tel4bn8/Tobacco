package com.nss.tobacco.activitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.daos.WirelessQA;
import com.nss.tobacco.entity.Sec_TengChaEntity;
import com.nss.tobacco.entity.Sec_XiaoduInfoEntity;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_XiaoduActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvTitle,name,time,yaopin,sheshi,beizhu;
    private Button back;
    private ImageView back_img;
    private GridView gvPhoto;

    private Sec_XiaoduInfoEntity entityInfo;
    private String path;
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__xiaodu);
        //初始化控件
        initView();
        //初始化数据
        initData();
        setListener();
    }
    private void setListener() {
        back.setOnClickListener(this);
        back_img.setOnClickListener(this);
    }
    private void initView() {
        tvTitle = (TextView) findViewById(R.id.msg_textView);
        back_img = (ImageView) findViewById(R.id.msg_back_item);
        tvTitle.setText("消毒");

        name = (TextView) findViewById(R.id.zhongzhihu);
        time = (TextView) findViewById(R.id.shijian);
        yaopin = (TextView) findViewById(R.id.yaoming);
        sheshi = (TextView) findViewById(R.id.sheshi);
        beizhu = (TextView) findViewById(R.id.beizhu);
        back = (Button) findViewById(R.id.btn_back);

        gvPhoto= (GridView) findViewById(R.id.second_ym_xd_msg_gridView);

    }
    private void initData() {
        tvTitle.setText("消毒");
        path= CommonUtil.getSDPath()+ File.separator+"XiaoDu";
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        entityInfo = (Sec_XiaoduInfoEntity) getIntent().getSerializableExtra("info");
        name.setText(entityInfo.getFarmer());
        time.setText(entityInfo.getCreatetime());
        beizhu.setText(entityInfo.getDetail());
        yaopin.setText(entityInfo.getYaopin());
        sheshi.setText(entityInfo.getSheshi());

        String photoPath = entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gvPhoto.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_back:
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
