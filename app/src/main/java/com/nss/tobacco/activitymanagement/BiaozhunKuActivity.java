package com.nss.tobacco.activitymanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.activityinfo.Third_biaozhunzhishi_first_Activity;
import com.nss.tobacco.activityinfo.Third_biaozhunzhishi_second_Activity;

public class BiaozhunKuActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biaozhun_ku);
        TextView textView = (TextView) findViewById(R.id.textView_item);
        textView.setText("标准知识库");
        ImageView iv = (ImageView) findViewById(R.id.back_item);
        iv.setOnClickListener(this);
        Button btn1 = (Button) findViewById(R.id.biaozhun1);
        Button btn2 = (Button) findViewById(R.id.biaozhun2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.biaozhun1:
                intent.setClass(this,Third_biaozhunzhishi_first_Activity.class);
                startActivity(intent);
                break;
            case R.id.biaozhun2:
                intent.setClass(this,Third_biaozhunzhishi_second_Activity.class);
                startActivity(intent);
                break;
            case R.id.back_item:
                finish();
                break;
            default:
                break;
        }
    }
}
