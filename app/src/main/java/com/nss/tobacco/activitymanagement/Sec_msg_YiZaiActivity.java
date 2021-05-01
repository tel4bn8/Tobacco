package com.nss.tobacco.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.Sec_XiaoduInfoEntity;
import com.nss.tobacco.entity.Sec_YizaiEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__yi_zai)
public class Sec_msg_YiZaiActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewInject(R.id.msg_back_item)
    private ImageView ivBack;

    @ViewInject(R.id.msg_textView)
    private TextView tvTitle;

    @ViewInject(R.id.second_yzgl_yzgl_msg_gridView)
    private GridView mGridView;

    @ViewInject(R.id.spinner_zhongzhihu)
    private TextView tvFarmer;

    @ViewInject(R.id.spinner_yanmiaoleixing)
    private TextView tvType;

    @ViewInject(R.id.mianji_edit)
    private TextView tvArea;
    @ViewInject(R.id.hangju_edit)
    private TextView tvRowJu;
    @ViewInject(R.id.zhuju_edit)
    private TextView tvZhuJu;
    @ViewInject(R.id.spinner_gaimo)
    private TextView tvGaiMo;
    @ViewInject(R.id.msg_spinner_fangshi)
    private TextView tvWay;
    @ViewInject(R.id.spinner_haichong)
    private TextView tvFangZhi;

    @ViewInject(R.id.chenghuolv_edit)
    private TextView tvRate;
    @ViewInject(R.id.start_text)
    private TextView tvStartTime;
    @ViewInject(R.id.end_text)
    private TextView tvEndTime;
    @ViewInject(R.id.beizhu_edit)
    private TextView tvDetail;

    @ViewInject(R.id.btn_back)
    private Button btnBack;
    private Sec_YizaiEntity entityInfo;
    private String path;
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        //初始化数据
        initData();
        setListener();
    }
    private void setListener() {
        ivBack.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }
    private void initData() {
        tvTitle.setText("移栽");
        path= CommonUtil.getSDPath()+ File.separator+"YiZaiGuanLi";
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;
        entityInfo = (Sec_YizaiEntity) getIntent().getSerializableExtra("info");
        Log.i("TAG", " Sec_msg_YiZaiActivity initData: "+entityInfo.getWay());
        tvFarmer.setText(entityInfo.getFarmer());
        tvArea.setText(entityInfo.getArea());
        tvType.setText(entityInfo.getType());
        tvWay.setText(entityInfo.getWay());
        tvDetail.setText(entityInfo.getDetail());
        tvFangZhi.setText(entityInfo.getFangzhi());
        tvGaiMo.setText(entityInfo.getGaimo());
        tvRowJu.setText(entityInfo.getRowdis());
        tvZhuJu.setText(entityInfo.getBetwdis());
        tvRate.setText(entityInfo.getRate());
        tvStartTime.setText(entityInfo.getStarttime());
        tvEndTime.setText(entityInfo.getEndtime());
        String photoPath = entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            mGridView.setAdapter(adapter);
        }

    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.msg_back_item:
                finish();
                break;
            default:
                break;
        }
    }
}
