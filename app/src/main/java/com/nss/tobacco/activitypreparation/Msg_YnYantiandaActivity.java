package com.nss.tobacco.activitypreparation;

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
import com.nss.tobacco.entity.YantianDangan;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_YnYantiandaActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageViewback;
    private GridView gridView;
    private TextView textViewfamername;
    private TextView textViewyear;
    private TextView textViewmianji;
    private TextView textViewjingdu;
    private TextView textViewweidu;
    private TextView textViewTudisrc;
    private TextView textViewTudiType;
    private TextView textViewqianchazuowu;
    private TextView textViewguangai;
    private TextView textViewdixing;
    private TextView textViewdili;
    private TextView textViewcreatetime;
    private TextView textViewbeizhu;
    private Button btnback;

    private YantianDangan entity = null;
    private String path= CommonUtil.getSDPath()+ File.separator+"yantiandangan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__yn_yantianda);
        entity = (YantianDangan) getIntent().getSerializableExtra("yantiandanganInfo");

        initView();
        initData();
        iniListenner();
    }

    private void iniListenner() {
        imageViewback.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    private void initData() {
        List<String> photosPath = new ArrayList<>();

        textViewfamername.setText(entity.getFarmer());
        textViewyear.setText(entity.getYear());
        textViewmianji.setText(entity.getArea());
        textViewjingdu.setText(entity.getLng());
        textViewweidu.setText(entity.getLat());
        textViewTudisrc.setText(entity.getSrc());
        Log.i("ssssrc",entity.getSrc()+"妹妹");
        textViewTudiType.setText(entity.getSoil());
        textViewqianchazuowu.setText(entity.getPrecrop());
        textViewguangai.setText(entity.getWater());
        textViewdixing.setText(entity.getDixing());
        textViewdili.setText(entity.getDilishuiping());
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
        imageViewback = (ImageView) findViewById(R.id.msg_back_item);
        gridView = (GridView) findViewById(R.id.first_msg_yn_ytda_add_caremaView);
        TextView textViewtitle = (TextView) findViewById(R.id.msg_textView);
        textViewtitle.setText("烟田档案详情");

        textViewfamername = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_yannongName);
        textViewyear = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_shiyongniandu);
        textViewmianji = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_mianji);
        textViewjingdu = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_jingdu);
        textViewweidu = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_weidu);
        textViewTudisrc = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_soiltsrc);
        textViewTudiType = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_soiltype);
        textViewqianchazuowu = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_precrop);
        textViewguangai = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_water);
        textViewdixing = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_dixing);
        textViewdili = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_dilishuiping);
        textViewcreatetime = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_inputTime);
        textViewbeizhu = (TextView) findViewById(R.id.first_msg_yn_ytda_add_textview_beizhu);
        btnback = (Button) findViewById(R.id.first_msg_yn_ytda_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_yn_ytda_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
