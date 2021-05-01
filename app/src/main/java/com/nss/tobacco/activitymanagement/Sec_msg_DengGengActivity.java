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
import com.nss.tobacco.entity.Sec_DongGengEntity;
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__deng_geng)
public class Sec_msg_DengGengActivity extends AppCompatActivity implements View.OnClickListener{
    @ViewInject(R.id.second_zdql_dg_msg_gridView)
    GridView gridViewPhoto;

    @ViewInject(R.id.second_zdql_dg_msg_farmer)
    TextView tvFarmer;
    @ViewInject(R.id.second_zdql_dg_msg_createtime)
    TextView tvCreateTime;
    @ViewInject(R.id.second_zdql_dg_msg_area)
    TextView tvArea;
    @ViewInject(R.id.second_zdql_dg_msg_donggengshendu)
    TextView tvDepth;
    @ViewInject(R.id.second_zdql_dg_msg_detail)
    TextView tvDetail;

    @ViewInject(R.id.msg_textView)
    private TextView tvTitle;
    @ViewInject(R.id.msg_back_item)
    private ImageView ivBackTilte;
    @ViewInject(R.id.second_zdql_dg_msg_btnBack)
    private Button btnBack;

    private Sec_DongGengEntity entityInfo;
    private String path= CommonUtil.getSDPath()+ File.separator+"DongGeng";
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        entityInfo= (Sec_DongGengEntity) getIntent().getSerializableExtra("info");
        photosPath=new ArrayList<>();
        //初始化控件数据
        initData();
        btnBack.setOnClickListener(this);
        ivBackTilte.setOnClickListener(this);

    }
    private void initData(){
        tvTitle.setText("冬耕");
        photosPath=new ArrayList<>();
        tvFarmer.setText(entityInfo.getFarmer());
        tvCreateTime.setText(entityInfo.getCreatetime());
        tvArea.setText(entityInfo.getArea());
        tvDepth.setText(entityInfo.getDepth());
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
            gridViewPhoto.setAdapter(adapter);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                this.finish();
                break;
            case R.id.second_zdql_dg_msg_btnBack:
                this.finish();
                break;
        }
    }
}
