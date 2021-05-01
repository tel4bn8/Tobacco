package com.nss.tobacco.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nss.tobacco.R;
import com.nss.tobacco.entity.UserInfo;
import com.nss.tobacco.url.API_Data;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.XUtil;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextpassword;
    private Button buttonsure;
    private Context context;

    //输入的用户名密码
    public static String username;
    private String password;

    //输入监听
    private CharSequence temp;
    private int editStart;
    private int editEnd;
    private Map<String, Object> userMap;

    public static boolean isLogin=false;//判断是否登录

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (isLogin){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }*/
        setContentView(R.layout.activity_login);
        userMap = new HashMap<String, Object>();

        initView();
        initListener();
        /*//注册广播接受者
        IntentFilter filter = new IntentFilter(UserInfoDao.ACTION);
        this.registerReceiver(broadcastReceiver, filter);*/
    }

    private void initListener() {

        buttonsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void initView() {
        editTextUsername = (EditText) findViewById(R.id.login_activity_edit_username);
        editTextUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                username = s.toString();
                userMap.put("username",username);
                Log.i("zz1",username);
                editStart = editTextUsername.getSelectionStart();
                editEnd = editTextUsername.getSelectionEnd();

                if (temp.length() > 11){
                    Toast.makeText(LoginActivity.this,
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    editTextUsername.setText(s);
                    editTextUsername.setSelection(tempSelection1);

                }
            }
        });
        editTextpassword = (EditText) findViewById(R.id.login_activity_edit_pass);
        editTextpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
                userMap.put("password",password);

                editStart = editTextpassword.getSelectionStart();
                editEnd = editTextpassword.getSelectionEnd();
                if (temp.length() > 6){
                    Toast.makeText(LoginActivity.this,
                            "密码字数超过限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    editTextpassword.setText(s);
                    editTextpassword.setSelection(tempSelection1);
                    Log.i("password", s.toString());
                }
            }
        });

        buttonsure = (Button) findViewById(R.id.login_activity_btnsure);
    }

    public void login(){

        XUtil.Post(API_Data.LOGIN, userMap, new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.i("zz2",password);
                Log.i("aaa",result);//打印json

                //json转化成对象

                UserInfo user =new Gson().fromJson(result, UserInfo.class);
                String realname = user.getRealname();
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                isLogin = true;

                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("realname",realname);
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                Toast.makeText(LoginActivity.this, "登录失败，请重试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*//创建广播接收者
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

        }
    };*/
}