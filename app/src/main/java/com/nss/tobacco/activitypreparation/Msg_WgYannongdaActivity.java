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
import com.nss.tobacco.entity.Yannong;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_WgYannongdaActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageViewback;
    private GridView gridView;
    private TextView textViewname;
    private TextView textViewidcard;
    private TextView textViewphone;
    private TextView textViewsex;
    private TextView textViewage;
    private TextView textViewworkyear;
    private TextView textVieweducation;
    private TextView textViewarea;
    private TextView textViewyear;
    private TextView textViewskill;
    private TextView textViewcredit;
    private TextView textViewgrade;
    private TextView textViewvillage;
    private TextView textViewcreatetime;
    private TextView textViewdetail;
    private TextView textViewtechnician;
    private Button buttonback;

    private Yannong entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"yannongdangan";
    private List<String> photosPath;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__wg_yannongda);
        entity = (Yannong) getIntent().getSerializableExtra("yannongdanganInfo");
        initView();
        initData();
        setListenner();
    }

    private void setListenner() {
        imageViewback.setOnClickListener(this);
        buttonback.setOnClickListener(this);
    }

    private void initData() {
        photosPath = new ArrayList<>();
        textViewname.setText(entity.getName());
        textViewidcard.setText(entity.getIdcard());
        textViewphone.setText(entity.getPhone());
        textViewsex.setText(entity.getSex());
        textViewage.setText(entity.getAge());
        textViewworkyear.setText(entity.getWorkyear());
        textVieweducation.setText(entity.getEducation());
        textViewarea.setText(entity.getArea());
        textViewyear.setText(entity.getYear());
        textViewskill.setText(entity.getSkill());
        textViewcredit.setText(entity.getCredit());
        textViewgrade.setText(entity.getGrade());
        textViewvillage.setText(entity.getVillage());
        textViewcreatetime.setText(entity.getCreatetime());
        textViewdetail.setText(entity.getDetail());
        textViewtechnician.setText(entity.getTechnician());

        String photoPath=entity.getPhotopath();
        String[] split = photoPath.split(" ");
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        if(!TextUtils.isEmpty(photoPath)){

            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gridView.setAdapter(adapter);
        }
    }

    private void initView() {
        imageViewback = (ImageView) findViewById(R.id.msg_back_item);
        TextView textViewtitle = (TextView) findViewById(R.id.msg_textView);
        textViewtitle.setText("烟农档案详情");
        gridView = (GridView) findViewById(R.id.first_msg_yn_ynda_add_caremaView);
        textViewname = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_name);
        textViewidcard = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_IDcardNum);
        textViewphone = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_phoneNum);
        textViewsex = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_sex);
        textViewage = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_age);
        textViewworkyear = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_zhongzhiAge);
        textVieweducation = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_cultureLevel);
        textViewarea = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_zhongzhiArea);
        textViewyear = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_zhongzhiYears);
        textViewskill = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_zhongzhiLevel);
        textViewcredit = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_credit);
        textViewgrade = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_starPingding);
        textViewvillage = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_HomeAddress);
        textViewcreatetime = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_inputTime);
        textViewdetail = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_beizhu);
        textViewtechnician = (TextView) findViewById(R.id.first_msg_yn_ynda_add_textview_wanggeFuzeren);
        buttonback = (Button) findViewById(R.id.first_msg_yn_ynda_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_yn_ynda_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
