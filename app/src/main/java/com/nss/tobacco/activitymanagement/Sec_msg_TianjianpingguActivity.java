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
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.Sec_TianjianpingguEntity;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_TianjianpingguActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvname;
    private TextView tvtime;
    private TextView tvmianji;
    private TextView tvzhushu;
    private TextView tvyeshu;
    private TextView tvdanye;
    private TextView tvzongzhong;
    private TextView tvbeizhu;
    private Button btnback;

    private Sec_TianjianpingguEntity entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"tianjianpinggu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__tianjianpinggu);
        entity = (Sec_TianjianpingguEntity) getIntent().getSerializableExtra("tianjianpingguInfo");

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
        tvtime.setText(entity.getCreatetime());
        tvmianji.setText(entity.getArea());
        tvzhushu.setText(entity.getZhunum());
        tvyeshu.setText(entity.getLeafnum());
        tvdanye.setText(entity.getOne());
        tvzongzhong.setText(entity.getSum());
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
        gridView = (GridView) findViewById(R.id.second_msg_tjpg_add_caremaView);
        TextView tvtitle = (TextView) findViewById(R.id.msg_textView);
        tvtitle.setText("田间评估详情");
        tvname = (TextView) findViewById(R.id.second_msg_tjpg_add_zhongzhihu_text);
        tvtime = (TextView) findViewById(R.id.second_msg_tjpg_add_pinggushijian);
        tvmianji = (TextView) findViewById(R.id.second_msg_tjpg_add_pinggumianji);
        tvzhushu = (TextView) findViewById(R.id.second_msg_tjpg_add_zhushu);
        tvyeshu = (TextView) findViewById(R.id.second_msg_tjpg_add_youxiaoyeshu);
        tvdanye = (TextView) findViewById(R.id.second_msg_tjpg_add_danye);
        tvzongzhong = (TextView) findViewById(R.id.second_msg_tjpg_add_zongzhong);
        tvbeizhu = (TextView) findViewById(R.id.second_msg_tjpg_add_beizhu);
        btnback = (Button) findViewById(R.id.second_msg_tjpg_add_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.second_msg_tjpg_add_btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
