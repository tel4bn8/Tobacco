package com.nss.tobacco.activitymanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.Sec_DadingEntity;
import com.nss.tobacco.entity.Sec_DongGengEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__da_ding)
public class Sec_msg_DaDingActivity extends AppCompatActivity implements OnClickListener{

    @ViewInject(R.id.second_tjgl_dading_msg_gridView)
    GridView gridView;

    @ViewInject(R.id.second_tjgl_dading_msg_farmer)
    TextView tvFarmer;
    @ViewInject(R.id.second_tjgl_dading_msg_zhangshi)
    TextView tvZhangShi;
    @ViewInject(R.id.second_tjgl_dading_msg_yiyaji)
    TextView tvYiYaJi;
    @ViewInject(R.id.second_tjgl_dading_msg_xianleitime)
    TextView tvXianLeiTime;
    @ViewInject(R.id.second_tjgl_dading_msg_dadingtime)
    TextView tvDaDingTime;

    @ViewInject(R.id.second_tjgl_dading_msg_youhuatime)
    TextView tvYouHuaTime;
    @ViewInject(R.id.second_tjgl_dading_msg_youhuashu)
    TextView tvYouHuaShu;
    @ViewInject(R.id.second_tjgl_dading_msg_detail)
    TextView tvDetail;

    @ViewInject(R.id.msg_textView)
    TextView tvTitle;

    @ViewInject(R.id.second_tjgl_dading_msg_btnback)
    Button btnBack;

    @ViewInject(R.id.msg_back_item)
    ImageView ivItem;


    private Sec_DadingEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"DaDing";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        entityInfo= (Sec_DadingEntity) getIntent().getSerializableExtra("info");
        photosPath=new ArrayList<>();
        //初始化控件数据
        initData();
        btnBack.setOnClickListener(this);
        ivItem.setOnClickListener(this);

    }

    private void initData() {
        tvTitle.setText("打顶抑芽");
        photosPath=new ArrayList<>();
        tvFarmer.setText(entityInfo.getFarmer());
        tvZhangShi.setText(entityInfo.getZhangshi());
        tvYiYaJi.setText(entityInfo.getYiyaji());
        tvXianLeiTime.setText(entityInfo.getXianleitime());
        tvDaDingTime.setText(entityInfo.getDadingtime());
        tvYouHuaTime.setText(entityInfo.getYouhuatime());
        tvYouHuaShu.setText(entityInfo.getYouhuashu());
        tvDetail.setText(entityInfo.getDetail());
        String photoPath=entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+ File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gridView.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_tjgl_dading_msg_btnback:
                this.finish();
                break;
        }
    }
}
