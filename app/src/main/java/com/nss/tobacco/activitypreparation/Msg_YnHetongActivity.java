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
import com.nss.tobacco.entity.HetongGuanli;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_YnHetongActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView textViewyear;
    private TextView textViewcode;
    private TextView textViewname;
    private TextView textViewtype;
    private TextView textViewmianji1;
    private TextView textViewnum1;
    private TextView textViewtime1;
    private TextView textViewbeizhu;
    private Button btnback;

    private HetongGuanli entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"hetongguanli";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__yn_hetong);
        entity = (HetongGuanli) getIntent().getSerializableExtra("hetongguanliInfo");
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

        textViewcode.setText(entity.getCode());
        textViewyear.setText(entity.getYear());
        textViewname.setText(entity.getFarmer());
        textViewtype.setText(entity.getType());
        textViewmianji1.setText(entity.getArea1());
        textViewnum1.setText(entity.getNum1());
        textViewtime1.setText(entity.getCaoqiantime());
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
        gridView = (GridView) findViewById(R.id.frm_first_yn_hetong_add_caremaView);
        TextView textViewtitle = (TextView) findViewById(R.id.msg_textView);
        textViewtitle.setText("草签合同详情");
        textViewyear = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_qianyueniandu);
        textViewcode = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_hetongID);
        textViewname = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_yannongName);
        textViewtype = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_yanyeType);
        textViewmianji1 = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_caoqianmianji);
        textViewnum1 = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_caoqianNum);
        textViewtime1 = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_caoqianTime);
        textViewbeizhu = (TextView) findViewById(R.id.first_msg_yn_htgl_add_textview_beizhu);
        btnback = (Button) findViewById(R.id.first_msg_yn_htgl_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_yn_htgl_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
