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
import com.nss.tobacco.entity.TurangJiance;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_WgTurangActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvWanggeID;
    private TextView tvYewuYear;
    private TextView tvYoujizhi;
    private TextView tvPH;
    private TextView tvN;
    private TextView tvP;
    private TextView tvK;
    private TextView tvCl;
    private TextView tvpicktime;
    private TextView tvtechnician;
    private TextView tvdetail;
    private Button btnback;

    private TurangJiance entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"turangjiance";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__wg_turang);
        entity = (TurangJiance) getIntent().getSerializableExtra("turangjianceInfo");
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

        tvYewuYear.setText(entity.getYear());
        tvWanggeID.setText(entity.getWanggecode());
        tvYoujizhi.setText(entity.getYoujizhi());
        tvPH.setText(entity.getPh());
        tvN.setText(entity.getN());
        tvP.setText(entity.getP());
        tvK.setText(entity.getK());
        tvCl.setText(entity.getCl());
        tvpicktime.setText(entity.getPicktime());
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
        tvTitle.setText("土壤检测结果");
        gridView = (GridView) findViewById(R.id.first_msg_wg_turang_add_caremaView);
        tvWanggeID = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_wanggeID);
        tvYewuYear = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_yewuYear);
        tvYoujizhi = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_youjizhi);
        tvPH = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_PH);
        tvN = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_suxiaoN);
        tvP = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_suxiaoP);
        tvK = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_suxiaoK);
        tvCl = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_Cl);
        tvpicktime = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_inputTime);
        tvtechnician = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_yanjiyuan);
        tvdetail = (TextView) findViewById(R.id.first_msg_wg_turang_add_textview_beizhu);

        btnback = (Button) findViewById(R.id.first_msg_wg_turang_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_wg_turang_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
