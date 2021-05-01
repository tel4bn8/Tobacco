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

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.WuziFafang;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_WzWuzifafangActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvtitle;
    private TextView tvname;
    private TextView tvfarmer;
    private TextView tvnum;
    private TextView tvdanwei;
    private TextView tvmoney;
    private TextView tvtime;
    private TextView tvbeizhu;
    private Button btnback;

    private WuziFafang entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"wuzifafang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__wz_wuzifafang);
        entity = (WuziFafang) getIntent().getSerializableExtra("wuzifafangInfo");

        initView();
        initData();
        setListenner();

    }

    private void setListenner() {
        ivback.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    private void initData() {
        tvtitle.setText("物资发放详情");
        List<String> photosPath = new ArrayList<>();

        tvname.setText(entity.getName());
        tvfarmer.setText(entity.getFarmer());
        tvnum.setText(entity.getNum());
        tvdanwei.setText(entity.getDanwei());
        tvmoney.setText(entity.getMoney());
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
        gridView = (GridView) findViewById(R.id.first_msg_wz_wuzi_ff_add_caremaView);
        tvtitle = (TextView) findViewById(R.id.msg_textView);
        tvname = (TextView) findViewById(R.id.first_msg_wz_wuzi_ff_add_textview_name);
        tvfarmer = (TextView) findViewById(R.id.first_msg_wz_wuzi_ff_add_textview_farmer);
        tvnum = (TextView) findViewById(R.id.first_msg_wz_wuzi_ff_add_textview_num);
        tvdanwei = (TextView) findViewById(R.id.first_msg_wz_wuzi_ff_add_textview_danwei);
        tvmoney = (TextView) findViewById(R.id.first_msg_wz_wuzi_ff_add_textview_money);
        tvtime = (TextView) findViewById(R.id.first_msg_wz_wuzi_ff_add_textview_inputTime);
        tvbeizhu = (TextView) findViewById(R.id.first_msg_wz_wuzi_ff_add_textview_beizhu);
        btnback = (Button) findViewById(R.id.first_msg_wz_wuzi_ff_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_wz_wuzi_ff_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
