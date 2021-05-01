package com.nss.tobacco.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;

public class AboutUs extends AppCompatActivity {

    private ImageView imageView;

    private TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        initView();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textView2.setText("V"+getVersion());


    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.next_lev_title_back);

        textView1 = (TextView) findViewById(R.id.next_lev_title_tv);
        textView1.setText("关于系统");
        textView2 = (TextView) findViewById(R.id.frm_forth_gywm_rjbb_Version);
    }

    private String getVersion() {

        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);

            return packageInfo.versionName;//返回当前版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

            return "未知版本号";
        }
    }
}
