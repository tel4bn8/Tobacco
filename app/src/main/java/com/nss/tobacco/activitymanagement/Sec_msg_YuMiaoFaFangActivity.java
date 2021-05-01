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
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.entity.Sec_YumiaoFafangEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Sec_msg_YuMiaoFaFangActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView ivBackTilte;
    TextView tvTitle;

    TextView tvFarmer;
    TextView tvCreateTime;
    TextView tvPingZhong;
    TextView tvNum;
    TextView tvDetail;
    GridView gvPhoto;
    Button btnBack;
    private Sec_YumiaoFafangEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"YuMiaoFaFang";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec_msg__yu_miao_fa_fang);
        entityInfo= (Sec_YumiaoFafangEntity) getIntent().getSerializableExtra("info");
        initView();
        initData();

    }
    private void initView() {
        tvTitle=(TextView) findViewById(R.id.msg_textView);
        ivBackTilte=(ImageView) findViewById(R.id.msg_back_item);
        tvFarmer=(TextView) findViewById(R.id.second_ym_ymff_msg_farmer);
        tvCreateTime=(TextView) findViewById(R.id.second_ym_ymff_msg_createtime);
        tvPingZhong=(TextView) findViewById(R.id.second_ym_ymff_msg_pingzhong);
        tvNum=(TextView) findViewById(R.id.second_ym_ymff_msg_num);
        tvDetail=(TextView) findViewById(R.id.second_ym_ymff_msg_detail);

        gvPhoto=(GridView) findViewById(R.id.second_ym_ymff_msg_gridView);

        btnBack= (Button) findViewById(R.id.second_ym_ymff_msg_btnBack);
        ivBackTilte.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initData(){
        photosPath=new ArrayList<>();
        tvTitle.setText("育苗发放");
        photosPath=new ArrayList<>();
        tvFarmer.setText(entityInfo.getFarmer());
        tvPingZhong.setText(entityInfo.getType());
        tvNum.setText(entityInfo.getNum());
        tvCreateTime.setText(entityInfo.getCreatetime());
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
            gvPhoto.setAdapter(adapter);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_ym_ymff_msg_btnBack:
                this.finish();
                break;
        }
    }
}
