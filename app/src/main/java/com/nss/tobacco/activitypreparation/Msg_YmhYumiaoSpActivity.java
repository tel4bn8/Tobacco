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
import com.nss.tobacco.entity.YumiaoShenpi;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_YmhYumiaoSpActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView textViewfarmer;
    private TextView textViewyear;
    private TextView textViewtype;
    private TextView textViewnum;
    private TextView textViewmianji;
    private TextView textViewyjysp;
    private TextView textViewtime;
    private TextView textViewbeizhu;
    private Button btnback;

    private YumiaoShenpi entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"yumiaoshenpi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__ymh_yumiao_sp);
        entity = (YumiaoShenpi) getIntent().getSerializableExtra("yumiaoshenpiInfo");

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

        textViewfarmer.setText(entity.getFarmer());
        textViewyear.setText(entity.getYear());
        textViewtype.setText(entity.getType());
        textViewnum.setText(entity.getNum());
        textViewmianji.setText(entity.getArea());
        textViewyjysp.setText(entity.getShenpiren1());
        textViewtime.setText(entity.getApplytime());
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
        textViewtitle.setText("育苗审批详情");
        gridView = (GridView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_caremaView);
        btnback = (Button) findViewById(R.id.first_msg_ymh_yumiao_sp_add_btnback);
        textViewfarmer = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_gongmiaohu);
        textViewyear = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_gongmiaoYear);
        textViewtype = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_yanyeType);
        textViewnum = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_yumiaoNum);
        textViewmianji = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_yumiaoMianji);
        textViewyjysp = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_yanjiyuanshenpi);
        textViewtime = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_shenqingTime);
        textViewbeizhu = (TextView) findViewById(R.id.first_msg_ymh_yumiao_sp_add_textview_beizhu);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_ymh_yumiao_sp_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
