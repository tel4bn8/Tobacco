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
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.entity.Sec_WenshiduEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__wen_shidu_gunali_actvity)
public class Sec_msg_WenShiduGunaliActvity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.back_item)
    private ImageView ivBack;

    @ViewInject(R.id.textView)
    private TextView tvTitle;

    @ViewInject(R.id.second_ym_wsdgl_msg_gridView)
    private GridView mGridView;
    @ViewInject(R.id.second_ym_wsdgl_msg_farmer)
    private TextView tvFarmer;
    @ViewInject(R.id.second_ym_wsdgl_msg_wendu)
    private TextView tvWendu;
    @ViewInject(R.id.second_ym_wsdgl_msg_shidu)
    private TextView tvShidu;
    @ViewInject(R.id.second_ym_wsdgl_msg_mioaqing)
    private TextView tvMiaoqi;

    @ViewInject(R.id.second_ym_wsdgl_msg_createTime)
    private TextView tvCreateTime;
    @ViewInject(R.id.second_ym_wsdgl_msg_detail)
    private TextView tvDetail;
    @ViewInject(R.id.second_ym_wsdgl_msg_btnBack)
    private Button btnBack;

    private Sec_WenshiduEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"WenShiDuGuanLi";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        entityInfo= (Sec_WenshiduEntity) getIntent().getSerializableExtra("info");
        btnBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        //初始化控件数据
        initData();
    }
    private void initData(){
        tvTitle.setText("温湿度管理");
        photosPath=new ArrayList<>();
        tvFarmer.setText(entityInfo.getFarmer());
        tvWendu.setText(entityInfo.getWendu());
        tvShidu.setText(entityInfo.getShidu());
        tvCreateTime.setText(entityInfo.getCreatetime());
        tvDetail.setText(entityInfo.getDetail());
        tvMiaoqi.setText(entityInfo.getMiaoqing());
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
            mGridView.setAdapter(adapter);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_item:
                this.finish();
                break;
            case R.id.second_ym_wsdgl_msg_btnBack:
                this.finish();
                break;
        }
    }
}
