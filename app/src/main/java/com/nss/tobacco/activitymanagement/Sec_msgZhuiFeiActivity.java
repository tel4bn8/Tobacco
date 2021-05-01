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
import com.nss.tobacco.entity.Sec_JiFeiEntity;
import com.nss.tobacco.entity.Sec_ZhuifeiEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg_zhui_fei)
public class Sec_msgZhuiFeiActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_ZhuifeiEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"ZhuiFei";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @ViewInject(R.id.msg_back_item)
    ImageView ivBack;
    @ViewInject(R.id.msg_textView)
    TextView tvTitle;
    @ViewInject(R.id.second_tjsf_zhuifei_msg_gridView)
    GridView gridView;
    @ViewInject(R.id.second_tjsf_zhuifei_msg_farmer)
    TextView tvFarmer;

    @ViewInject(R.id.second_tjsf_zhuifei_msg_time)
    TextView tvTime;
    @ViewInject(R.id.second_tjsf_zhuifei_msg_type)
    TextView tvType;
    @ViewInject(R.id.second_tjsf_zhuifei_msg_num)
    TextView tvNum;
    @ViewInject(R.id.second_tjsf_zhuifei_msg_area)
    TextView tvArea;
    @ViewInject(R.id.second_tjsf_zhuifei_msg_detail)
    TextView tvDetail;
    @ViewInject(R.id.second_tjsf_zhuifei_msg_btnBack)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        entityInfo= (Sec_ZhuifeiEntity) getIntent().getSerializableExtra("info");
        //初始化控件数据
        initData();
        initListener();

    }
    private void initData() {
        tvTitle.setText("追肥");
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        tvFarmer.setText(entityInfo.getFarmer());
        tvNum.setText(entityInfo.getNum());
        tvArea.setText(entityInfo.getArea());
        tvTime.setText(entityInfo.getCreatetime());

        tvType.setText(entityInfo.getType());
        tvDetail.setText(entityInfo.getDetail());
        String photoPath = entityInfo.getPhotopath();
        String[] split = photoPath.split(" ");
        if(!TextUtils.isEmpty(photoPath)){
            for(int i=0;i<split.length;i++){
                photosPath.add(path+ File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this,photosPath,screenWidth);
            gridView.setAdapter(adapter);
        }



    }
    private void initListener(){
        btnBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_tjsf_zhuifei_msg_btnBack:
                this.finish();
                break;
        }
    }
}
