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
import com.nss.tobacco.entity.Sec_HongkaoEntity;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_HongkaoActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvname;
    private TextView tvmianji;
    private TextView tvtime;
    private TextView tvzhuanye;
    private TextView tvquqing;
    private TextView tvxiangzhong;
    private TextView tvganzhong;
    private TextView tvelc;
    private TextView tvcaol;
    private TextView tvchengben;
    private TextView tvbeizhu;
    private Button btnback;

    private Sec_HongkaoEntity entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"hongkaoguanli";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__hongkao);
        entity = (Sec_HongkaoEntity) getIntent().getSerializableExtra("hongkaoguanliInfo");

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
        tvmianji.setText(entity.getArea());
        tvtime.setText(entity.getCreatetime());
        tvzhuanye.setText(entity.getZhuanyehongkao());
        tvquqing.setText(entity.getQuqingquza());
        tvxiangzhong.setText(entity.getBeforeweight());
        tvganzhong.setText(entity.getAfterweight());
        tvelc.setText(entity.getElectric());
        tvcaol.setText(entity.getCoal());
        tvchengben.setText(entity.getMoney());
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
        gridView = (GridView) findViewById(R.id.second_msg_hk_add_caremaView);
        TextView tvtitle = (TextView) findViewById(R.id.msg_textView);
        tvtitle.setText("烘烤详情");
        tvname = (TextView) findViewById(R.id.second_msg_hk_add_zhongzhihu);
        tvmianji = (TextView) findViewById(R.id.second_msg_hk_add_mianji);
        tvtime = (TextView) findViewById(R.id.second_msg_hk_add_shijian);
        tvzhuanye = (TextView) findViewById(R.id.second_msg_hk_add_zhuanyehua);
        tvquqing = (TextView) findViewById(R.id.second_msg_hk_add_quqingquza);
        tvxiangzhong = (TextView) findViewById(R.id.second_msg_hk_add_xianyanzhong);
        tvganzhong = (TextView) findViewById(R.id.second_msg_hk_add_hongganzhong);
        tvelc = (TextView) findViewById(R.id.second_msg_hk_add_haodian);
        tvcaol = (TextView) findViewById(R.id.second_msg_hk_add_haomei);
        tvchengben = (TextView) findViewById(R.id.second_msg_hk_add_chengben);
        tvbeizhu = (TextView) findViewById(R.id.second_msg_hk_add_beizhu);
        btnback = (Button) findViewById(R.id.second_msg_hk_add_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.second_msg_hk_add_btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
