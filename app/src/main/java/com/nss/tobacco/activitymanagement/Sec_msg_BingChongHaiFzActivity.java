package com.nss.tobacco.activitymanagement;

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

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.daos.Sec_BingchongFzDao;
import com.nss.tobacco.entity.Sec_BingchongFzEntity;
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ViewInject;

import java.io.File;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;


public class Sec_msg_BingChongHaiFzActivity extends AppCompatActivity implements View.OnClickListener {


    private GridView gvPhoto;
    private TextView tvFramer;
    private TextView tvYongYaoMingCheng;
    private TextView tvNongDu;
    private TextView tvFangZhiFangShi;
    private TextView tvBingChongHaiLeiXing;
    private TextView tvFaBangLv;

    private TextView tvArea;
    private TextView tvYuGuLiang;

    private TextView tvFangzhiTime;
    private TextView tvDetail;
    private Button tvBtnBack;

    private TextView tvTitle;
    private Sec_BingchongFzEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"BingChongHaiFangZhi";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__bing_chong_hai_fz);
        entityInfo= (Sec_BingchongFzEntity) getIntent().getSerializableExtra("info");
        initView();
        initData();

    }

    private void initData() {
        tvTitle.setText("病虫防止");
        photosPath=new ArrayList<>();
        tvFramer.setText(entityInfo.getFarmer());
        tvBingChongHaiLeiXing.setText(entityInfo.getType());
        tvYongYaoMingCheng.setText(entityInfo.getYaopin());
        tvNongDu.setText(entityInfo.getNongdu());
        tvFangZhiFangShi.setText(entityInfo.getWay());
        tvFaBangLv.setText(entityInfo.getRate());
        tvArea.setText(entityInfo.getArea());
        Log.i("TAG", "initData: "+entityInfo.getLoss());
        tvYuGuLiang.setText(entityInfo.getLoss());
        tvFangzhiTime.setText(entityInfo.getCreatetime());
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
            gvPhoto.setAdapter(adapter);
        }
    }

    private void initView() {
        gvPhoto = (GridView) findViewById(R.id.second_ym_bcfz_msg_gridView);
        tvFramer = (TextView) findViewById(R.id.second_ym_bcfz_msg_farmer);
        tvYongYaoMingCheng = (TextView) findViewById(R.id.second_ym_bcfz_msg_yongyaomingcheng);
        tvNongDu = (TextView) findViewById(R.id.second_ym_bcfz_msg_nongdu);
        tvFangZhiFangShi = (TextView) findViewById(R.id.second_ym_bcfz_msg_fangzhifangshi);
        tvBingChongHaiLeiXing = (TextView) findViewById(R.id.second_ym_bcfz_msg_bingchonghaileixing);
        tvFaBangLv = (TextView) findViewById(R.id.second_ym_bcfz_fabinglv);
        tvArea = (TextView) findViewById(R.id.second_ym_bcfz_msg_area);
        tvYuGuLiang = (TextView) findViewById(R.id.second_ym_bcfz_msg_yuguliang);
        tvFangzhiTime = (TextView) findViewById(R.id.second_ym_bcfz_msg_fangzhiTime);
        tvDetail = (TextView) findViewById(R.id.second_ym_bcfz_detail);

        tvBtnBack = (Button) findViewById(R.id.second_ym_bcfz_msg_btnBack);
        tvTitle = (TextView) findViewById(R.id.msg_textView);
        ivBack = (ImageView) findViewById(R.id.msg_back_item);

        tvBtnBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.second_ym_bcfz_msg_btnBack:
                this.finish();
                break;
            case R.id.msg_back_item:
                this.finish();
                break;
        }
    }
}
