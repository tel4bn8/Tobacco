package com.nss.tobacco.activitymanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.CommonAdapter;
import com.nss.tobacco.adapter.ViewHolder;
import com.nss.tobacco.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ChanlianYuceActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textView;
    private ImageView back_img;
    private TextView add_text,upload;
    private Spinner year_spinner;
    private EditText search_edit;
    private Button search_btn;
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chanlian_yuce);
        initView();
        initData();
        myRecyclerView();
        setLitsener();//监听事件
    }
    protected void initData()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    private void initView() {
        textView = (TextView)findViewById(R.id.textView_item);
        textView.setText("产量预测");
        back_img = (ImageView) findViewById(R.id.back_item);
        add_text = (TextView) findViewById(R.id.add_item);
        upload = (TextView) findViewById(R.id.up_item);
        year_spinner = (Spinner) findViewById(R.id.year_item);
        search_edit = (EditText) findViewById(R.id.searchbox);
        search_btn = (Button) findViewById(R.id.search_item);
        mySpinner();
    }

    private void myRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //添加数据
        mRecyclerView.setAdapter(new CommonAdapter<String>(this, R.layout.item_detailmsg_recyclerview, mDatas)
        {
            @Override
            public void convert(ViewHolder holder, String s)
            {
                holder.setText(R.id.id_num,s);
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                holder.setOnClickListener(R.id.id_num, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"aaaaa"+position,Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        //分割线
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
    }

    private void setLitsener() {
        back_img.setOnClickListener(this);
        add_text.setOnClickListener(this);
        upload.setOnClickListener(this);
        setMySpinnerLitsener();
        setMyEditLitsener();
        search_btn.setOnClickListener(this);
    }
    //搜索框editText监听
    private void setMyEditLitsener() {
        search_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //年度spinner监听
    private void setMySpinnerLitsener() {
        year_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //自定义spinner
    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.year_item, R.layout.item_myspinner);
        Spinner spinner = (Spinner) findViewById(R.id.year_item);
        spinner.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            case R.id.add_item:
                intent.setClass(this, Add_ClyuceActivity.class);
                startActivity(intent);
                break;
            case R.id.up_item:
                Toast.makeText(this,"已经上传成功",Toast.LENGTH_LONG).show();
                break;
            case R.id.year_item:

                break;
            case R.id.searchbox:

                break;
            case R.id.search_item:

                break;
            default:
                break;
        }

    }
}
