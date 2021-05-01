package com.nss.tobacco.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SetYearActivity extends AppCompatActivity {

    private TextView textView;

    private ImageView imageView;

    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2;

    String year = getNowYear();
    int a = Integer.parseInt(year)+1;
    String nextyear = String.valueOf(a);

    private String time ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_year);


        initView();

        initListener();
    }

    private void initListener() {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton1.getId() == checkedId){
                    Toast.makeText(SetYearActivity.this, "已选中"+year+"业务年度", Toast.LENGTH_SHORT).show();
                }else if (radioButton2.getId() == checkedId){
                    Toast.makeText(SetYearActivity.this, "已选中"+nextyear+"业务年度", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void initView() {

        textView = (TextView) findViewById(R.id.next_lev_title_tv);
        //textView.setText("业务年度设置");

        imageView = (ImageView) findViewById(R.id.next_lev_title_back);

        radioGroup = (RadioGroup) findViewById(R.id.frm_forth_ndsz_rg);

        radioButton1 = (RadioButton) findViewById(R.id.frm_forth_ndsz_rb_2016);
        radioButton1.setText(year+"业务年度");
        radioButton2 = (RadioButton) findViewById(R.id.frm_forth_ndsz_rb_2017);
        radioButton2.setText(nextyear+"业务年度");
    }

    private String getNowYear(){

        String DEFAULT_TIME_FORMAT = "yyyy";

        SimpleDateFormat dateformat = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
        time = dateformat.format(Calendar.getInstance().getTime());

        return time;
    }
}
