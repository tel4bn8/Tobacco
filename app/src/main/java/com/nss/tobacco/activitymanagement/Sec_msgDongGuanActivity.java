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
import com.nss.tobacco.entity.Sec_DongGuanEntity;
import com.nss.tobacco.entity.Sec_TengChaEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg_dong_guan)
public class Sec_msgDongGuanActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.msg_back_item)
    ImageView ivBack;
    @ViewInject(R.id.msg_textView)
    TextView tvTitle;

    @ViewInject(R.id.second_zdql_dongguan_msg_gridView)
    GridView gridView;
    @ViewInject(R.id.second_zdql_dongguan_msg_farmer)
    TextView tvFarmer;
    @ViewInject(R.id.second_zdql_dongguan_msg_createtime)
    TextView tvCreateTime;
    @ViewInject(R.id.second_zdql_dongguan_msg_area)
    TextView tvArea;
    @ViewInject(R.id.second_zdql_dongguan_msg_detail)
    TextView tvDetail;
    @ViewInject(R.id.second_zdql_dongguan_msg_btnBack)
    Button btnBack;

    private Sec_DongGuanEntity entityInfo;
    private String path;
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);


        //初始化数据
        initData();
        //控件监听
        initListener();

    }
    private void initData() {
        tvTitle.setText("冬灌");
        path= CommonUtil.getSDPath()+ File.separator+"DongGuan";
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        entityInfo = (Sec_DongGuanEntity) getIntent().getSerializableExtra("info");
        tvFarmer.setText(entityInfo.getFarmer());
        tvArea.setText(entityInfo.getArea());
        tvCreateTime.setText(entityInfo.getCreatetime());
        tvDetail.setText(entityInfo.getDetail());
        String photoPath = entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gridView.setAdapter(adapter);
        }



    }
    private void initListener() {
        btnBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_zdql_dongguan_msg_btnBack:
                this.finish();
                break;
        }
    }
}
