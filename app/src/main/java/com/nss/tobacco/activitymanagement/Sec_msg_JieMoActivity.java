package com.nss.tobacco.activitymanagement;

import android.content.Intent;
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
import com.nss.tobacco.entity.Sec_GuangaiEntity;
import com.nss.tobacco.entity.Sec_JiemoEntity;
import com.nss.tobacco.utils.CommonUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_sec_msg__jie_mo)
public class Sec_msg_JieMoActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_JiemoEntity entityInfo;
    private String path;
    private List<String> photosPath;
    private int screenWidth;//屏幕的宽度

    @ViewInject(R.id.msg_back_item)
    ImageView ivBack;
    @ViewInject(R.id.msg_textView)
    TextView tvTitle;

    @ViewInject(R.id.second_tjgl_jiemo_msg_gridView)
    GridView gridView;

    @ViewInject(R.id.second_tjgl_jiemo_msg_farmer)
    TextView tvFarmer;


    @ViewInject(R.id.second_tjgl_jiemo_msg_jiemo)
    TextView tvJieMo;
    @ViewInject(R.id.second_tjgl_jiemo_msg_jiemotime)
    TextView tvJieMoTime;
    @ViewInject(R.id.second_tjgl_jiemo_msg_xiaopeitu)
    TextView tvXiaoPeiTu;
    @ViewInject(R.id.second_tjgl_jiemo_msg_dapeitu)
    TextView tvDaPeiTu;
    @ViewInject(R.id.second_tjgl_jiemo_msg_area)
    TextView tvArea;
    @ViewInject(R.id.second_tjgl_jiemo_msg_height)
    TextView tvHeight;
    @ViewInject(R.id.second_tjgl_jiemo_msg_paishui)
    TextView tvPaiShui;
    @ViewInject(R.id.second_tjgl_jiemo_msg_pianshu)
    TextView tvPianShu;
    @ViewInject(R.id.second_tjgl_jiemo_msg_detail)
    TextView tvDetail;
    @ViewInject(R.id.second_tjgl_jiemo_msg_btnBack)
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        entityInfo = (Sec_JiemoEntity) getIntent().getSerializableExtra("info");

        //初始化数据
        initData();
        setListener();
    }
    private void setListener() {
        btnBack.setOnClickListener(this);
        ivBack.setOnClickListener(this);
    }
    private void initData() {
        tvTitle.setText("揭膜");
        path= CommonUtil.getSDPath()+ File.separator+"JieMo";
        photosPath=new ArrayList<>();
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.widthPixels;

        tvFarmer.setText(entityInfo.getFarmer());
        tvArea.setText(entityInfo.getArea());
        tvDaPeiTu.setText(entityInfo.getDapeitu());
        tvDetail.setText(entityInfo.getDetail());
        tvHeight.setText(entityInfo.getHeight());
        tvJieMo.setText(entityInfo.getJiemo());
        tvJieMoTime.setText(entityInfo.getJiemotime());
        tvPaiShui.setText(entityInfo.getPaishui());
        tvPianShu.setText(entityInfo.getPaishui());
        tvXiaoPeiTu.setText(entityInfo.getXiaopeitu());
        String photoPath = entityInfo.getPhotoPath();
        String[] split = photoPath.split(" ");
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
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.second_tjgl_jiemo_msg_btnBack:
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
