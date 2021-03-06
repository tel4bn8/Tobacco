package com.nss.tobacco.activitypreparation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.PhotoAdapter;
import com.nss.tobacco.entity.YumiaoGongchang;
import com.nss.tobacco.utils.CommonUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Msg_YmhYmGcActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView ivback;
    private GridView gridView;
    private TextView textViewname;
    private TextView textViewvillage;
    private TextView textViewbuildtime;
    private TextView textViewdapengshu;
    private TextView textViewdapengjiegou;
    private TextView textViewmianji;
    private TextView textViewyumiaoshu;
    private TextView textViewdengjitime;
    private TextView textViewdengjiren;
    private TextView textViewbeizhu;
    private Button btnback;

    private YumiaoGongchang entity;
    private String path= CommonUtil.getSDPath()+ File.separator+"yumiaogongchang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__ymh_ym_gc);
        entity = (YumiaoGongchang) getIntent().getSerializableExtra("yumiaogongchangInfo");

        initView();
        initData();
        setListenner();
    }

    private void setListenner() {
        ivback.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    private void initData() {
        List<String> photosPath = new ArrayList<>();

        textViewname.setText(entity.getName());
        textViewvillage.setText(entity.getVillage());
        textViewbuildtime.setText(entity.getBuildtime());
        textViewdapengshu.setText(entity.getDapengnum());
        textViewdapengjiegou.setText(entity.getStructure());
        textViewmianji.setText(entity.getArea());
        textViewyumiaoshu.setText(entity.getMiaosum());
        textViewdengjitime.setText(entity.getCreatetime());
        textViewdengjiren.setText(entity.getTechnician());
        textViewbeizhu.setText(entity.getDetail());

        String photoPath=entity.getPhotopath();
        String[] split = photoPath.split(" ");
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int screenWidth = metrics.widthPixels;

        if(!TextUtils.isEmpty(photoPath)){

            for(int i=0;i<split.length;i++){
                photosPath.add(path+File.separator + split[i]+".jpg");
            }
            PhotoAdapter adapter=new PhotoAdapter(this, photosPath, screenWidth);
            gridView.setAdapter(adapter);
        }
    }

    private void initView() {
        ivback = (ImageView) findViewById(R.id.msg_back_item);
        TextView textViewtitle = (TextView) findViewById(R.id.msg_textView);
        textViewtitle.setText("??????????????????");
        gridView = (GridView) findViewById(R.id.first_msg_ymh_ym_gc_add_caremaView);
        textViewname = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_dapengID);
        textViewvillage = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_HomeAddress);
        textViewbuildtime = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_xiujianshijian);
        textViewdapengshu = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_dapengNum);
        textViewdapengjiegou = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_dapengjiegou);
        textViewmianji = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_mianji);
        textViewyumiaoshu = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_yumiaoNum);
        textViewdengjitime = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_inputTime);
        textViewdengjiren = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_dengjiren);
        textViewbeizhu = (TextView) findViewById(R.id.first_msg_ymh_ym_gc_add_textview_beizhu);
        btnback = (Button) findViewById(R.id.first_msg_ymh_ym_gc_add_btnback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.msg_back_item:
                finish();
                break;
            case R.id.first_msg_ymh_ym_gc_add_btnback:
                finish();
                break;
            default:
                break;
        }
    }
}
