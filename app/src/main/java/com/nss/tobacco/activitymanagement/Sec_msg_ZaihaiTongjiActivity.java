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
import com.nss.tobacco.entity.Sec_Zaihaitongji;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_ZaihaiTongjiActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivback;
    private GridView gridView;
    private TextView tvtitle,tvname,tvtype,tvareaSz,tvareaHt,tvlose,tvtime,tvbeizhu;
    private Button btnback;

    private Sec_Zaihaitongji entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"zaihaitongji";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__zaihai_tongji);
        entity = (Sec_Zaihaitongji) getIntent().getSerializableExtra("info");

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

        tvname.setText(entity.getFarmer());
        tvtype.setText(entity.getType());
        tvareaSz.setText(entity.getArea1());
        tvareaHt.setText(entity.getArea2());
        tvlose.setText(entity.getLose());
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
        gridView = (GridView) findViewById(R.id.second_zhtj_add_caremaView);
        tvtitle = (TextView) findViewById(R.id.msg_textView);
        tvtitle.setText("自然灾害统计");
        tvname = (TextView) findViewById(R.id.second_zhtj_msg_zhongzhihu_text);
        tvtype = (TextView) findViewById(R.id.second_zhtj_msg_type_text);
        tvareaSz = (TextView) findViewById(R.id.second_zhtj_msg_shouzimianji_text);
        tvareaHt = (TextView) findViewById(R.id.second_zhtj_msg_hetongmianji_text);
        tvlose = (TextView) findViewById(R.id.second_zhtj_msg_sunshi_text);
        tvtime = (TextView) findViewById(R.id.second_zhtj_msg_time_text);
        tvbeizhu = (TextView) findViewById(R.id.second_zhtj_msg_beizhu_text);
        btnback = (Button) findViewById(R.id.second_zhtj_msg_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.second_zhtj_msg_btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
