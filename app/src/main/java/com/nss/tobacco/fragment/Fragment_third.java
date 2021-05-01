package com.nss.tobacco.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nss.tobacco.R;
import com.nss.tobacco.activitymanagement.BcFangzhiActivity;
import com.nss.tobacco.activitymanagement.BiaozhunKuActivity;
import com.nss.tobacco.activityinfo.FuchiZhengceActivity;
import com.nss.tobacco.activityinfo.KaoheBzActivity;
import com.nss.tobacco.activityinfo.PeixunXcActivity;
import com.nss.tobacco.activityinfo.ShijuWenjianActivity;
import com.nss.tobacco.activityinfo.YancaoFalvActivity;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class Fragment_third extends Fragment implements View.OnClickListener{
    LinearLayout ll_kaohebaozhun,ll_peixunxuanchuan,ll_bingchong,ll_biaozhunku,ll_zhengce,ll_wenjian,ll_falv;
    public Fragment_third() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_third, container, false);
        initView(view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setLitsener();//设置监听

    }

    private void setLitsener() {
        ll_kaohebaozhun.setOnClickListener(this);
        ll_peixunxuanchuan.setOnClickListener(this);
        ll_bingchong.setOnClickListener(this);
        ll_biaozhunku.setOnClickListener(this);
        ll_zhengce.setOnClickListener(this);
        ll_wenjian.setOnClickListener(this);
        ll_falv.setOnClickListener(this);

    }

    private void initView(View view) {
        ll_kaohebaozhun = (LinearLayout) view.findViewById(R.id.ll_kaohebiaozhun);
        ll_peixunxuanchuan = (LinearLayout) view.findViewById(R.id.ll_peixunxuanchuan);
        ll_bingchong = (LinearLayout) view.findViewById(R.id.ll_bingchonghai);
        ll_biaozhunku = (LinearLayout) view.findViewById(R.id.ll_biaozhunku);
        ll_zhengce = (LinearLayout) view.findViewById(R.id.ll_fuchizhengce);
        ll_wenjian = (LinearLayout) view.findViewById(R.id.ll_shijuwenjian);
        ll_falv = (LinearLayout) view.findViewById(R.id.ll_yancaofalv);
    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_kaohebiaozhun:
                intent.setClass(getContext(), KaoheBzActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_peixunxuanchuan:
                intent.setClass(getContext(), PeixunXcActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_bingchonghai:
                intent.setClass(getContext(), BcFangzhiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_biaozhunku:
                intent.setClass(getContext(), BiaozhunKuActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_fuchizhengce:
                intent.setClass(getContext(), FuchiZhengceActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_shijuwenjian:
                intent.setClass(getContext(), ShijuWenjianActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yancaofalv:
                intent.setClass(getContext(), YancaoFalvActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
