package com.nss.tobacco.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.activity.LoginActivity;
import com.nss.tobacco.activity.MainActivity;
import com.nss.tobacco.activitypreparation.WgJichuActivity;
import com.nss.tobacco.activitypreparation.WgTurangActivity;
import com.nss.tobacco.activitypreparation.WgYantianGhActivity;
import com.nss.tobacco.activitypreparation.WgYanyeLhActivity;
import com.nss.tobacco.activitypreparation.WzWuziFfActivity;
import com.nss.tobacco.activitypreparation.YmhYmGcActivity;
import com.nss.tobacco.activitypreparation.YmhYumaioActivity;
import com.nss.tobacco.activitypreparation.YmhYumiaoSpActivity;
import com.nss.tobacco.activitypreparation.YnHetongActivity;
import com.nss.tobacco.activitypreparation.YnYannongDaActivity;
import com.nss.tobacco.activitypreparation.YnYantianDaActivity;
import com.nss.tobacco.activitypreparation.YnZhongzhiSpActivity;
import com.nss.tobacco.utils.Gongli;
import com.nss.tobacco.utils.Lunar;
import com.nss.tobacco.utils.SolarTerms;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 第一页(产前准备)
 */

public class Fragment_first extends Fragment implements View.OnClickListener {

    private ImageView imageView;

    private TextView textViewName, textView2, textView3, textView4, textView5;

    private String time, realname;

    private LinearLayout linearLayout11, linearLayout12, linearLayout13, linearLayout14, linearLayout21, linearLayout22, linearLayout23,
            linearLayout24, linearLayout31, linearLayout32, linearLayout33, linearLayout42;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取烟技员姓名
        realname = ((MainActivity) getActivity()).getRealname();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_first, container, false);

        initView(view);
        handler.post(updateThread);

        return view;
    }

    private void initView(View v) {

        Calendar calendar = Calendar.getInstance();
        Lunar lunar = new Lunar(calendar);
        SolarTerms terms = new SolarTerms();

        imageView = (ImageView) v.findViewById(R.id.frm_home_ic_headphoto);//测试用，升级删除

        //显示烟技员(管理员)姓名
        textViewName = (TextView) v.findViewById(R.id.frm_first_headName);
        textViewName.setText(realname);

        //时间
        textView2 = (TextView) v.findViewById(R.id.frm_first_gonglishijian);

        //显示公历时间
        textView3 = (TextView) v.findViewById(R.id.frm_first_gonglixingqi);
        textView3.setText(Gongli.StringData());

        //显示农历
        textView4 = (TextView) v.findViewById(R.id.frm_first_nongli);
        textView4.setText(lunar.toString());

        //显示节气
        textView5 = (TextView) v.findViewById(R.id.frm_first_jieqi);
        textView5.setText(terms.solarTermToString());

        linearLayout11 = (LinearLayout) v.findViewById(R.id.frm_first_wgh_ytgh);
        linearLayout12 = (LinearLayout) v.findViewById(R.id.frm_first_wgh_jcss);
        linearLayout13 = (LinearLayout) v.findViewById(R.id.frm_first_wgh_trjc);
        linearLayout14 = (LinearLayout) v.findViewById(R.id.frm_first_wgh_yylh);

        linearLayout21 = (LinearLayout) v.findViewById(R.id.frm_first_yn_ynda);
        linearLayout22 = (LinearLayout) v.findViewById(R.id.frm_first_yn_ytda);
        linearLayout23 = (LinearLayout) v.findViewById(R.id.frm_first_yn_zzsp);
        linearLayout24 = (LinearLayout) v.findViewById(R.id.frm_first_yn_htgl);

        linearLayout31 = (LinearLayout) v.findViewById(R.id.frm_first_ymh_ymh);
        linearLayout32 = (LinearLayout) v.findViewById(R.id.frm_first_ymh_ymgc);
        linearLayout33 = (LinearLayout) v.findViewById(R.id.frm_first_ymh_ymsp);

        linearLayout42 = (LinearLayout) v.findViewById(R.id.frm_first_wz_wzff);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initListener();
    }

    private void initListener() {
        imageView.setOnClickListener(this);

        linearLayout11.setOnClickListener(this);
        linearLayout12.setOnClickListener(this);
        linearLayout13.setOnClickListener(this);
        linearLayout14.setOnClickListener(this);

        linearLayout21.setOnClickListener(this);
        linearLayout22.setOnClickListener(this);
        linearLayout23.setOnClickListener(this);
        linearLayout24.setOnClickListener(this);

        linearLayout31.setOnClickListener(this);
        linearLayout32.setOnClickListener(this);
        linearLayout33.setOnClickListener(this);

        linearLayout42.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {

            //网格化
            case R.id.frm_first_wgh_ytgh:
                intent.setClass(getContext(), WgYantianGhActivity.class);//烟田规划
                startActivity(intent);
                break;
            case R.id.frm_first_wgh_jcss:
                intent.setClass(getContext(), WgJichuActivity.class);
                startActivity(intent);
                break;
            case R.id.frm_first_wgh_trjc:
                intent.setClass(getContext(), WgTurangActivity.class);//土壤检测
                startActivity(intent);
                break;
            case R.id.frm_first_wgh_yylh:
                intent.setClass(getContext(), WgYanyeLhActivity.class);//烟叶理化
                startActivity(intent);
                break;

            //烟农
            case R.id.frm_first_yn_ynda:
                intent.setClass(getContext(), YnYannongDaActivity.class);
                startActivity(intent);
                break;
            case R.id.frm_first_yn_ytda:
                intent.setClass(getContext(), YnYantianDaActivity.class);
                startActivity(intent);
                break;
            case R.id.frm_first_yn_zzsp:
                intent.setClass(getContext(), YnZhongzhiSpActivity.class);
                startActivity(intent);
                break;
            case R.id.frm_first_yn_htgl:
                intent.setClass(getContext(), YnHetongActivity.class);
                startActivity(intent);
                break;

            //育苗户
            case R.id.frm_first_ymh_ymh:
                intent.setClass(getContext(), YmhYumaioActivity.class);
                startActivity(intent);
                break;
            case R.id.frm_first_ymh_ymgc:
                intent.setClass(getContext(), YmhYmGcActivity.class);
                startActivity(intent);
                break;
            case R.id.frm_first_ymh_ymsp:
                intent.setClass(getContext(), YmhYumiaoSpActivity.class);
                startActivity(intent);
                break;

            //物资
            case R.id.frm_first_wz_wzff:
                intent.setClass(getContext(), WzWuziFfActivity.class);
                startActivity(intent);
                break;

            case R.id.frm_home_ic_headphoto://测试用，#删
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    Handler handler = new Handler();
    Runnable updateThread = new Runnable() {
        @Override
        public void run() {
            //将要执行的线程对象放入到队列当中，待时间结束后，运行制定的线程对象
            handler.postDelayed(updateThread, 60 * 1000);

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            time = dateFormat.format(Calendar.getInstance().getTime());
            textView2.setText(time);
        }
    };
}
