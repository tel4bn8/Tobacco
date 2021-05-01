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
import com.nss.tobacco.entity.Sec_CaishouEntity;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_CaishouActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView textViewname;
    private TextView textViewstart;
    private TextView textViewend;
    private TextView textViewpick;
    private TextView textViewsame;
    private TextView textViewkangci;
    private TextView textViewganshu;
    private TextView textViewfeilei;
    private TextView textViewzhuanye;
    private TextView textViewbeizhu;
    private Button btnback;

    private Sec_CaishouEntity entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"chengshucaishou";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__caishou);
        entity = (Sec_CaishouEntity) getIntent().getSerializableExtra("chengshucaishouInfo");

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

        textViewname.setText(entity.getFarmer());
        textViewstart.setText(entity.getStarttime());
        textViewend.setText(entity.getEndtime());
        textViewpick.setText(entity.getPick());
        textViewsame.setText(entity.getSame());
        textViewkangci.setText(entity.getKangci());
        textViewganshu.setText(entity.getGanci());
        textViewfeilei.setText(entity.getXianyanfenlei());
        textViewzhuanye.setText(entity.getZhuanyecaiji());
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
        gridView = (GridView) findViewById(R.id.second_msg_cscs_add_caremaView);
        TextView textViewtitle = (TextView) findViewById(R.id.msg_textView);
        textViewtitle.setText("成熟采收详情");
        textViewname = (TextView) findViewById(R.id.second_msg_cscs_add_text_zhongzhihu);
        textViewstart = (TextView) findViewById(R.id.second_msg_cscs_add_text_start);
        textViewend = (TextView) findViewById(R.id.second_msg_cscs_add_text_end);
        textViewpick = (TextView) findViewById(R.id.second_msg_cscs_add_text_anchengshudu);
        textViewsame = (TextView) findViewById(R.id.second_msg_cscs_add_text_chengshudu);
        textViewkangci = (TextView) findViewById(R.id.second_msg_cscs_add_text_kangci);
        textViewganshu = (TextView) findViewById(R.id.second_msg_cscs_add_text_ganshu);
        textViewfeilei = (TextView) findViewById(R.id.second_msg_cscs_add_text_xianyanfenlei);
        textViewzhuanye = (TextView) findViewById(R.id.second_msg_cscs_add_text_zhuanyedui);
        textViewbeizhu = (TextView) findViewById(R.id.second_msg_cscs_add_text_beizhu);
        btnback = (Button) findViewById(R.id.second_msg_cscs_add_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.second_msg_cscs_add_btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
