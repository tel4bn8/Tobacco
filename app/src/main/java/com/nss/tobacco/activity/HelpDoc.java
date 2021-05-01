package com.nss.tobacco.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;

public class HelpDoc extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_doc);
        initView();
        textView.setText("帮助文档");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.back_item);
        textView = (TextView) findViewById(R.id.textView_item);
    }
}
