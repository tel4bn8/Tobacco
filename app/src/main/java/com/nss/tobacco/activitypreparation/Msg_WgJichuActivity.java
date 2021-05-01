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
import com.nss.tobacco.entity.JichuSheshi;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_WgJichuActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvVillage;
    private TextView tvkaofangnum;
    private TextView tvjijingnum;
    private TextView tvcreatetime;
    private TextView tvtechnician;
    private TextView tvdetail;
    private TextView tvyear;
    private Button btnback;

    private JichuSheshi entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"jichusheshi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__wg_jichu);
        entity = (JichuSheshi) getIntent().getSerializableExtra("jichusheshiInfo");
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

        tvyear.setText(entity.getYear());
        tvVillage.setText(entity.getVillage());
        tvkaofangnum.setText(entity.getKaofangnum());
        tvjijingnum.setText(entity.getJijingnum());
        tvcreatetime.setText(entity.getCreatetime());
        tvtechnician.setText(entity.getTechnician());
        tvdetail.setText(entity.getDetail());

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
        TextView tvTitle = (TextView) findViewById(R.id.msg_textView);
        tvTitle.setText("基础设施详情");
        gridView = (GridView) findViewById(R.id.first_msg_wg_jichu_add_caremaView);

        tvVillage = (TextView) findViewById(R.id.first_msg_wg_jichu_add_textview_village);
        tvkaofangnum = (TextView) findViewById(R.id.first_msg_wg_jichu_add_textview_kaofangNum);
        tvjijingnum = (TextView) findViewById(R.id.first_msg_wg_jichu_add_textview_jijingNum);
        tvcreatetime = (TextView) findViewById(R.id.first_msg_wg_jichu_add_textview_inputTime);
        tvtechnician = (TextView) findViewById(R.id.first_msg_wg_jichu_add_textview_yanjiyuan);
        tvyear = (TextView) findViewById(R.id.first_msg_wg_jichu_add_textview_yewuYear);
        tvdetail = (TextView) findViewById(R.id.first_msg_wg_jichu_add_textview_beizhu);

        btnback = (Button) findViewById(R.id.first_msg_wg_jichu_add_btn_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_wg_jichu_add_btn_back:
                finish();
                break;
            default:
                break;
        }
    }
}
