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
import com.nss.tobacco.entity.YantianGuihua;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_WgYantianGhActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvVillage;
    private TextView tvYear;
    private TextView tvArea;
    private TextView tvlng;
    private TextView tvlat;
    private TextView tvdixing;
    private TextView tvsoil;
    private TextView tvfeili;
    private TextView tvmoditime;
    private TextView tvtechnician;
    private TextView tvdetail;
    private Button btnback;

    private YantianGuihua entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"yantianguihua";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__wg_yantian_gh);
        entity = (YantianGuihua) getIntent().getSerializableExtra("yantianguihuaInfo");
        initView();
        initData();
        iniListenner();
    }

    private void iniListenner() {
        ivback.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    private void initData() {
        List<String> photosPath = new ArrayList<>();

        tvYear.setText(entity.getYear());
        tvVillage.setText(entity.getVillage());
        tvArea.setText(entity.getArea());
        tvlng.setText(entity.getLng());
        tvlat.setText(entity.getLat());
        tvdixing.setText(entity.getDixing());
        tvsoil.setText(entity.getSoil());
        tvfeili.setText(entity.getFeili());
        tvmoditime.setText(entity.getModitime());
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
        TextView tvTitle = (TextView) findViewById(R.id.msg_textView);
        tvTitle.setText("烟田规划");

        ivback = (ImageView) findViewById(R.id.msg_back_item);
        gridView = (GridView) findViewById(R.id.first_msg__wg_yantian_gh_add_caremaView);
        tvVillage = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_village);
        tvYear = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_guhuaniandu);
        tvArea = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_mianji);
        tvlng = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_jingdu);
        tvlat = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_weidu);
        tvdixing = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_dixing);
        tvsoil = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_turangfeili);
        tvfeili = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_turangleixing);
        tvmoditime = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_modiTime);
        tvtechnician = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_yanjiyuan);
        tvdetail = (TextView) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_textview_beizhu);

        btnback = (Button) findViewById(R.id.frm_first_msg_wg_yantian_gh_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.frm_first_msg_wg_yantian_gh_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
