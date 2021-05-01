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
import com.nss.tobacco.entity.Sec_Baoxian;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_BaoxianActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvname;
    private TextView tvtoubao;
    private TextView tvlipei;
    private TextView tvhetong;
    private TextView tvtime;
    private TextView tvbeizhu;
    private Button btnBack;

    private Sec_Baoxian entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"baoxian";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__baoxian);
        entity = (Sec_Baoxian) getIntent().getSerializableExtra("info");

        initView();
        initData();
        setListenner();
    }

    private void setListenner() {
        ivback.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initData() {
        List<String> photosPath = new ArrayList<>();

        tvname.setText(entity.getFarmer());
        tvtoubao.setText(entity.getToubaoarea());
        tvlipei.setText(entity.getLipeiarea());
        tvhetong.setText(entity.getHetongarea());
        tvtime.setText(entity.getCreatetime());
        tvbeizhu.setText(entity.getDetail());

        String photoPath=entity.getPhotopath();
        String[] split = photoPath.split(" ");
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;

        if(!TextUtils.isEmpty(photoPath)){

            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this, photosPath, screenWidth);
            gridView.setAdapter(adapter);
        }
    }

    private void initView() {
        ivback = (ImageView) findViewById(R.id.msg_back_item);
        gridView = (GridView) findViewById(R.id.second_bx_msg_caremaView);
        TextView tvtitle = (TextView) findViewById(R.id.msg_textView);
        tvtitle.setText("保险详情");
        tvname = (TextView) findViewById(R.id.second_bx_msg_zhongzhihu_text);
        tvtoubao = (TextView) findViewById(R.id.second_bx_msg_toubaomianji_text);
        tvlipei = (TextView) findViewById(R.id.second_bx_msg_lipeimianji_text);
        tvhetong = (TextView) findViewById(R.id.second_bx_msg_hetongmianji_text);
        tvtime = (TextView) findViewById(R.id.second_bx_msg_shijian_text);
        tvbeizhu = (TextView) findViewById(R.id.second_bx_msg_beizhu_text);
        btnBack = (Button) findViewById(R.id.second_bx_msg_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.second_bx_msg_btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
