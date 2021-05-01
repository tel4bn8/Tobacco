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
import com.nss.tobacco.entity.Sec_WenshiduEntity;
import com.nss.tobacco.entity.Sec_ZhibaoEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__zhi_bao)
public class Sec_msg_ZhiBaoActivity extends AppCompatActivity implements View.OnClickListener{

    private Sec_ZhibaoEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"ZhiBao";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @ViewInject(R.id.second_tjgl_zhibao_msg_gridView)
    GridView gridView;
    @ViewInject(R.id.second_tjgl_zhibao_msg_farmer)
    TextView tvFarmer;

    @ViewInject(R.id.second_tjgl_zhibao_msg_time)
    TextView tvTime;
    @ViewInject(R.id.second_tjgl_zhibao_msg_type)
    TextView tvType;
    @ViewInject(R.id.second_tjgl_zhibao_msg_yaoming)
    TextView tvYaoMiang;

    @ViewInject(R.id.second_tjgl_zhibao_msg_nongdu)
    TextView tvNongDu;
    @ViewInject(R.id.second_tjgl_zhibao_msg_detail)
    TextView tvDetail;
    @ViewInject(R.id.second_tjgl_zhibao_msg_btnBack)
    Button btnBack;

    @ViewInject(R.id.msg_back_item)
    ImageView ivBack;

    @ViewInject(R.id.msg_textView)
    TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__zhi_bao);
        x.view().inject(this);
        entityInfo= (Sec_ZhibaoEntity) getIntent().getSerializableExtra("info");
        btnBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        //初始化控件数据
        initData();

    }
    private void initData(){
        tvTitle.setText("植保");
        photosPath=new ArrayList<>();
        tvFarmer.setText(entityInfo.getFarmer());
        tvYaoMiang.setText(entityInfo.getYaominag());
        tvNongDu.setText(entityInfo.getNongdu());
        tvTime.setText(entityInfo.getCreatetime());
        tvType.setText(entityInfo.getType());
        tvDetail.setText(entityInfo.getDetail());
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_tjgl_zhibao_msg_btnBack:
                this.finish();
                break;
        }
    }
}
