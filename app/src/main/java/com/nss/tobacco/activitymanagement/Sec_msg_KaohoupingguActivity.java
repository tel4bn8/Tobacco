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
import com.nss.tobacco.entity.Sec_Kaohoupinggu;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_KaohoupingguActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView tvtitle,tvfarmer,tvkangci,tvdankang,tvdangan,tvzongchan,tvtime,tvbeizhu;
    private Button btnback;

    private Sec_Kaohoupinggu entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"kaohoupinggu";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__kaohoupinggu);
        entity = (Sec_Kaohoupinggu) getIntent().getSerializableExtra("info");
        initView();
        //初始化控件数据
        initData();
    }

    private void initData() {
        tvtitle.setText("烤后评估详情");
        photosPath=new ArrayList<>();
        tvfarmer.setText(entity.getFarmer());
        tvkangci.setText(entity.getKangci());
        tvdankang.setText(entity.getOneganshu());
        tvdangan.setText(entity.getOne());
        tvzongchan.setText(entity.getSum());
        tvtime.setText(entity.getCreatetime());
        tvbeizhu.setText(entity.getDetail());
        String photoPath=entity.getPhotopath();
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
        gridView = (GridView) findViewById(R.id.second_msg_khpg_add_caremaView);
        tvtitle = (TextView) findViewById(R.id.msg_textView);
        tvfarmer = (TextView) findViewById(R.id.second_msg_khpg_add_zhongzhihu_text);
        tvkangci = (TextView) findViewById(R.id.second_msg_khpg_add_kangci_text);
        tvdankang = (TextView) findViewById(R.id.second_msg_khpg_add_dankangganshu_text);
        tvdangan = (TextView) findViewById(R.id.second_msg_khpg_add_danganzhongliang_text);
        tvzongchan = (TextView) findViewById(R.id.second_msg_khpg_add_zongchan_text);
        tvtime = (TextView) findViewById(R.id.second_msg_khpg_add_shijian_text);
        tvbeizhu = (TextView) findViewById(R.id.second_msg_khpg_add_baizhu_text);
        btnback = (Button) findViewById(R.id.second_msg_khpg_add_btn_back);
        ivback.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_msg_khpg_add_btn_back:
                this.finish();
                break;
        }
    }
}
