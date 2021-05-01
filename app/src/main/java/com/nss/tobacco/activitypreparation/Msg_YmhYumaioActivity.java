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
import com.nss.tobacco.entity.Yumiaohu;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_YmhYumaioActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView textViewname;
    private TextView textViewidcard;
    private TextView textViewagesex;
    private TextView textViewage;
    private TextView textViewphone;
    private TextView textViewedu;
    private TextView textViewyear;
    private TextView textViewaddress;
    private TextView textViewgongchang;
    private TextView textViewtime;
    private TextView textViewyanjiyuan;
    private TextView textViewbeizhu;
    private Button btnback;

    private Yumiaohu entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"yumiaohu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__ymh_yumaio);
        entity = (Yumiaohu) getIntent().getSerializableExtra("yumiaohuInfo");

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

        textViewname.setText(entity.getName());
        textViewidcard.setText(entity.getIdcard());
        textViewagesex.setText(entity.getSex());
        textViewage.setText(entity.getAge());
        textViewphone.setText(entity.getPhone());
        textViewedu.setText(entity.getEducation());
        textViewyear.setText(entity.getYear());
        textViewaddress.setText(entity.getVillage());
        textViewgongchang.setText(entity.getYumiaogongchangid());
        textViewtime.setText(entity.getCreatetime());
        textViewyanjiyuan.setText(entity.getTechnician());
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
        textViewtitle.setText("育苗户详细");
        gridView = (GridView) findViewById(R.id.first_msg_ymh_ymh_add_caremaView);
        textViewname = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_yumiaohuName);
        textViewidcard = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_IDcardNum);
        textViewagesex = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_sex);
        textViewage = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_age);
        textViewphone = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_phoneNum);
        textViewedu = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_cultureLevel);
        textViewyear = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_yumiaoYears);
        textViewaddress = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_HomeAddress);
        textViewgongchang = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_yumiaodian);
        textViewtime = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_inputTime);
        textViewyanjiyuan = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_yanjiyuan);
        textViewbeizhu = (TextView) findViewById(R.id.first_msg_ymh_ymh_add_textview_beizhu);

        btnback = (Button) findViewById(R.id.first_msg_ymh_ymh_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_ymh_ymh_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
