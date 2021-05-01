package com.nss.tobacco.activitypreparation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.ZhongzhiShenpi;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_YnZhongzhiActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView textViewfamer;
    private TextView textViewtype;
    private TextView textViewmianji;
    private TextView textViewyear;
    private TextView textViewstopsp;
    private TextView textViewyanjisp;
    private TextView textViewcreatetime;
    private TextView textViewbeizhu;
    private Button btnback;

    private ZhongzhiShenpi entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"zhongzhishenpi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__yn_zhongzhi);
        entity = (ZhongzhiShenpi) getIntent().getSerializableExtra("zhongzhishenpiInfo");

        initView();
        initData();
        setListenner();
    }

    private void setListenner() {
        ivback.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    private void initData() {
        List<String> photosPath = new ArrayList<>();

        textViewfamer.setText(entity.getFarmer());
        textViewyear.setText(entity.getYear());
        textViewtype.setText(entity.getType());
        textViewmianji.setText(entity.getArea());
        textViewyanjisp.setText(entity.getShenpiren1());
        textViewstopsp.setText(entity.getShenpiren2());
        textViewcreatetime.setText(entity.getCreatetime());
        textViewbeizhu.setText(entity.getDetail());

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
        TextView textViewtitle = (TextView) findViewById(R.id.msg_textView);
        textViewtitle.setText("种植审批详情");
        gridView = (GridView) findViewById(R.id.first_msg_yn_zhongzhi_sp_add_caremaView);
        textViewfamer = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_yannongName);
        textViewtype = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_yancaoleixing);
        textViewmianji = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_zhongzhimianji);
        textViewyear = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_shengpiniandu);
        textViewstopsp = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_stopShengpi);
        textViewyanjisp = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_yanjiyuanShengpi);
        textViewcreatetime = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_inputTime);
        textViewbeizhu = (TextView) findViewById(R.id.first_msg_yn_zzsp_add_textview_beizhu);
        btnback = (Button) findViewById(R.id.first_msg_yn_zzsp_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_yn_zzsp_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
