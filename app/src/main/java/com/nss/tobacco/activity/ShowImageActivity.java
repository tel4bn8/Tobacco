package com.nss.tobacco.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.nss.tobacco.utils.CommonUtil;

public class ShowImageActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //显示传递过来的照片地址
        String imageUrl = getIntent().getStringExtra("ImageUrl");
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(CommonUtil.getBitmapInLocal(imageUrl));

        setContentView(iv);
    }
}
