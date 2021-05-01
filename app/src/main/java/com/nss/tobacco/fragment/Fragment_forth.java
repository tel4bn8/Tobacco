package com.nss.tobacco.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.activity.AboutUs;
import com.nss.tobacco.activity.HelpDoc;
import com.nss.tobacco.activity.MainActivity;
import com.nss.tobacco.activity.SetYearActivity;

import java.io.File;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class Fragment_forth extends Fragment implements View.OnClickListener{

    private TextView forthAdminName;

    private LinearLayout linearLayout1, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6;
    private Button button;

    private String realname;

    //app更新
    private Handler handler;
    private static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;

    public Fragment_forth() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取烟技员姓名
        realname = ((MainActivity)getActivity()).getRealname();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_forth, container, false);

        initView(view);

        initListener();

        return view;
    }

    private void initListener() {
        linearLayout1.setOnClickListener(this);
        linearLayout2.setOnClickListener(this);
        linearLayout3.setOnClickListener(this);
        linearLayout4.setOnClickListener(this);
        linearLayout5.setOnClickListener(this);
        linearLayout6.setOnClickListener(this);

        button.setOnClickListener(this);
    }

    private void initView(View view) {

        //显示管理员姓名
        forthAdminName = (TextView) view.findViewById(R.id.frm_forth_tv_adminName);
        forthAdminName.setText(realname);

        linearLayout1 = (LinearLayout) view.findViewById(R.id.frm_forth_ndsz);
        linearLayout2 = (LinearLayout) view.findViewById(R.id.frm_forth_sjql);
        linearLayout3 = (LinearLayout) view.findViewById(R.id.frm_forth_bzwd);
        linearLayout4 = (LinearLayout) view.findViewById(R.id.frm_forth_mmxg);
        linearLayout5 = (LinearLayout) view.findViewById(R.id.frm_forth_rjsj);
        linearLayout6 = (LinearLayout) view.findViewById(R.id.frm_forth_gyxt);

        button = (Button) view.findViewById(R.id.frm_forth_btn_out);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.frm_forth_ndsz://年度设置
                intent.setClass(getContext(), SetYearActivity.class);
                startActivity(intent);
                break;
            case R.id.frm_forth_sjql://数据清理

                Toast.makeText(getContext(), "数据已清理",Toast.LENGTH_SHORT).show();
                break;
            case R.id.frm_forth_bzwd://帮助文档
                intent.setClass(getContext(), HelpDoc.class);
                startActivity(intent);
                break;
            case R.id.frm_forth_mmxg://密码修改
                Toast.makeText(getContext(), "后台测试",Toast.LENGTH_SHORT).show();
                break;
            case R.id.frm_forth_rjsj://软件升级
                Toast.makeText(getContext(), "您的软件已是最新版本",Toast.LENGTH_SHORT).show();
                break;
            case R.id.frm_forth_gyxt://关于系统
                intent.setClass(getContext(), AboutUs.class);
                startActivity(intent);
                break;
            case R.id.frm_forth_btn_out://退出
                myDialog();
                break;
        }
    }
    private void myDialog() {
        new android.support.v7.app.AlertDialog.Builder(getContext()).setTitle("系统提示")//设置对话框标题
                .setMessage("确认退出系统吗？")//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub

                        getActivity().finish();
                    }
                }).setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                // TODO Auto-generated method stub
                Log.i("alertdialog", " 请保存数据！");
            }
        }).show();//在按键响应事件中显示此对话框
    }
}
