package com.nss.tobacco.activitymanagement;

import android.content.Intent;
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
import com.nss.tobacco.entity.Sec_GuangaiEntity;
import com.nss.tobacco.entity.Sec_XiaoduInfoEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__guan_gai)
public class Sec_msg_GuanGaiActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_GuangaiEntity entityInfo;
    private String path;
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @ViewInject(R.id.msg_back_item)
    ImageView imageView;

    @ViewInject(R.id.second_tjgl_guangai_msg_gridView)
    GridView gvGridView;

    @ViewInject(R.id.second_tjgl_guangai_msg_farmer)
    TextView textFarmer;

    @ViewInject(R.id.second_tjgl_guangai_msg_area)
    TextView textArea;

    @ViewInject(R.id.second_tjgl_guangai_msg_time)
    TextView textTime;

    @ViewInject(R.id.second_tjgl_guangai_msg_way)
    TextView textWay;
    @ViewInject(R.id.second_tjgl_guangai_msg_detail)
    TextView textDetail;
    @ViewInject(R.id.second_tjgl_guangai_msg_btnBack)
    Button btnBack;
    @ViewInject(R.id.msg_textView)
    TextView tvTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        entityInfo = (Sec_GuangaiEntity) getIntent().getSerializableExtra("info");

        //初始化数据
        initData();
        setListener();
    }
    private void setListener() {
        btnBack.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }
    private void initData() {
        tvTitle.setText("灌溉");
        path= CommonUtil.getSDPath()+ File.separator+"GuanGai";
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        textFarmer.setText(entityInfo.getFarmer());
        textTime.setText(entityInfo.getWorktime());
        textArea.setText(entityInfo.getArea());
        textWay.setText(entityInfo.getWay());

        textDetail.setText(entityInfo.getDetail());
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
            case R.id.second_tjgl_guangai_msg_btnBack:
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
