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
import com.nss.tobacco.entity.Sec_HongkaoshiEntity;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_HongkaoshiActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvtitle,tvname,tvtel,tvxiaoguo,tvtime,tvyanjiyuan,tvbeizhu;
    private Button btnback;
    private Sec_HongkaoshiEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"hongkaoshi";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__hongkaoshi);
        entityInfo= (Sec_HongkaoshiEntity) getIntent().getSerializableExtra("hongkaoshiInfo");
        initView();
        //初始化控件数据
        initData();

    }

    private void initData() {
        tvtitle.setText("烘烤师");
        photosPath=new ArrayList<>();
        tvname.setText(entityInfo.getName());
        tvtel.setText(entityInfo.getTel());
        tvxiaoguo.setText(entityInfo.getXiaoguo());
        tvyanjiyuan.setText(entityInfo.getTechnician());
        tvtime.setText(entityInfo.getCreatetime());
        tvbeizhu.setText(entityInfo.getDetail());
        String photoPath=entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gridView.setAdapter(adapter);
        }
    }

    private void initView() {
        ivback = (ImageView) findViewById(R.id.msg_back_item);
        gridView = (GridView) findViewById(R.id.second_msg_hks_add_caremaView);
        tvtitle = (TextView) findViewById(R.id.msg_textView);
        tvname = (TextView) findViewById(R.id.second_msg_hks_add_name_text);
        tvtel = (TextView) findViewById(R.id.second_msg_hks_add_tel_text);
        tvxiaoguo = (TextView) findViewById(R.id.second_msg_hks_add_xiaoguo_text);
        tvtime = (TextView) findViewById(R.id.second_msg_hks_add_time_text);
        tvyanjiyuan = (TextView) findViewById(R.id.second_hks_add_yanjiyuan_text);
        tvbeizhu = (TextView) findViewById(R.id.second_msg_hks_add_beizhu_text);
        btnback = (Button) findViewById(R.id.second_msg_hks_add_btn_back);
        ivback.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_msg_hks_add_btn_back:
                this.finish();
                break;
        }
    }
}
