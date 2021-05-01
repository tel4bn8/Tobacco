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
import com.nss.tobacco.activitymanagement.BaoxianActivity;
import com.nss.tobacco.activitymanagement.BingchongHaiActivity;
import com.nss.tobacco.activitymanagement.ChengshuCaishouActivity;
import com.nss.tobacco.activitymanagement.DadingYiyaActivity;
import com.nss.tobacco.activitymanagement.DonggengActivity;
import com.nss.tobacco.activitymanagement.DongguanActivity;
import com.nss.tobacco.activitymanagement.GuangaiActivity;
import com.nss.tobacco.activitymanagement.HongkaoActivity;
import com.nss.tobacco.activitymanagement.HongkaoshiActivity;
import com.nss.tobacco.activitymanagement.JiemoActivity;
import com.nss.tobacco.activitymanagement.JifeiActivity;
import com.nss.tobacco.activitymanagement.KaohoupingguActivity;
import com.nss.tobacco.activitymanagement.MiaochaungZhuifeiActivity;
import com.nss.tobacco.activitymanagement.MiaoqiguanliActivity;
import com.nss.tobacco.activitymanagement.QilongActivity;
import com.nss.tobacco.activitymanagement.ShougouActivity;
import com.nss.tobacco.activitymanagement.TengchaActivity;
import com.nss.tobacco.activitymanagement.TianjianpingguActivity;
import com.nss.tobacco.activitymanagement.XiaoduActivity;
import com.nss.tobacco.activitymanagement.ZhuifeiActivity;
import com.nss.tobacco.activitymanagement.WenshiduActivity;
import com.nss.tobacco.activitymanagement.XuefeiActivity;
import com.nss.tobacco.activitymanagement.YannongPingjiActivity;
import com.nss.tobacco.activitymanagement.YizaiGuanliActivity;
import com.nss.tobacco.activitymanagement.YumiaoFafangActivity;
import com.nss.tobacco.activitymanagement.ZaihaiTongjiActivity;
import com.nss.tobacco.activitymanagement.ZhibaoActivity;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class Fragment_second extends Fragment implements View.OnClickListener{
    LinearLayout ll_xiaodu,ll_miaoqiguanli,ll_miaochaungzhuifei,ll_wenshidu,ll_bingchong,ll_yumiaofafang,ll_tengcha,ll_donggeng,ll_qilong,ll_dongguan,ll_yizai,
                 ll_jifei,ll_xuefei,ll_tianjianzhuifei,ll_jimo,ll_dading,ll_guangai,ll_zhibao,ll_caishou,ll_hongkao,ll_hongkaoshi,ll_tianjianpinggu,
                 ll_kaohoupinggu,ll_zaihai,ll_baoxian,ll_shougou,ll_pingji;
    public Fragment_second() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frm_second, container, false);
        initView(view);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setLitsener();//设置监听

    }
    private void initView(View view) {
        ll_xiaodu = (LinearLayout) view.findViewById(R.id.ll_xiaodu);
        ll_miaoqiguanli = (LinearLayout) view.findViewById(R.id.ll_miaoqiguanli);
        ll_miaochaungzhuifei = (LinearLayout) view.findViewById(R.id.ll_miaochuangzhuifei);
        ll_wenshidu = (LinearLayout) view.findViewById(R.id.ll_wenshidu);
        ll_bingchong = (LinearLayout) view.findViewById(R.id.ll_bingchongfangzhi);
        ll_yumiaofafang = (LinearLayout) view.findViewById(R.id.ll_yumiaofafang);
        ll_tengcha = (LinearLayout) view.findViewById(R.id.ll_tengcha);
        ll_donggeng = (LinearLayout) view.findViewById(R.id.ll_donggeng);
        ll_qilong = (LinearLayout) view.findViewById(R.id.ll_qilong);
        ll_dongguan = (LinearLayout) view.findViewById(R.id.ll_dongguan);
        ll_yizai = (LinearLayout) view.findViewById(R.id.ll_yizai);
        ll_jifei = (LinearLayout) view.findViewById(R.id.ll_jifei);
        ll_xuefei = (LinearLayout) view.findViewById(R.id.ll_xuefei);
        ll_tianjianzhuifei = (LinearLayout) view.findViewById(R.id.ll_tianjianzhuifei);
        ll_jimo = (LinearLayout) view.findViewById(R.id.ll_jiemopeitu);
        ll_dading = (LinearLayout) view.findViewById(R.id.ll_dadingyiya);
        ll_guangai = (LinearLayout) view.findViewById(R.id.ll_guangai);
        ll_zhibao = (LinearLayout) view.findViewById(R.id.ll_zhibao);
        ll_caishou = (LinearLayout) view.findViewById(R.id.ll_caishou);
        ll_hongkao = (LinearLayout) view.findViewById(R.id.ll_hongkao);
        ll_hongkaoshi = (LinearLayout) view.findViewById(R.id.ll_hongkaoshi);
        ll_tianjianpinggu = (LinearLayout) view.findViewById(R.id.ll_tianjianpinggu);
        ll_kaohoupinggu = (LinearLayout) view.findViewById(R.id.ll_kaohoupinggu);
        ll_zaihai = (LinearLayout) view.findViewById(R.id.ll_ziranzaihai);
        ll_tianjianpinggu = (LinearLayout) view.findViewById(R.id.ll_tianjianpinggu);
        ll_baoxian = (LinearLayout) view.findViewById(R.id.ll_baoxian);
        ll_shougou = (LinearLayout) view.findViewById(R.id.ll_shougouchaxun);
        ll_pingji = (LinearLayout) view.findViewById(R.id.ll_yannongpingji);
    }

    private void setLitsener() {
        ll_xiaodu.setOnClickListener(this);
        ll_miaoqiguanli.setOnClickListener(this);
        ll_miaochaungzhuifei.setOnClickListener(this);
        ll_wenshidu.setOnClickListener(this);
        ll_bingchong.setOnClickListener(this);
        ll_yumiaofafang.setOnClickListener(this);
        ll_tengcha.setOnClickListener(this);
        ll_donggeng.setOnClickListener(this);
        ll_qilong.setOnClickListener(this);
        ll_dongguan.setOnClickListener(this);
        ll_yizai.setOnClickListener(this);
        ll_jifei.setOnClickListener(this);
        ll_xuefei.setOnClickListener(this);
        ll_tianjianzhuifei.setOnClickListener(this);
        ll_jimo.setOnClickListener(this);
        ll_dading.setOnClickListener(this);
        ll_guangai.setOnClickListener(this);
        ll_zhibao.setOnClickListener(this);
        ll_caishou.setOnClickListener(this);
        ll_hongkao.setOnClickListener(this);
        ll_hongkaoshi.setOnClickListener(this);
        ll_tianjianpinggu.setOnClickListener(this);
        ll_kaohoupinggu.setOnClickListener(this);
        ll_zaihai.setOnClickListener(this);
        ll_baoxian.setOnClickListener(this);
        ll_shougou.setOnClickListener(this);
        ll_pingji.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_xiaodu:
                intent.setClass(getContext(), XiaoduActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_miaoqiguanli:
                intent.setClass(getContext(), MiaoqiguanliActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_miaochuangzhuifei:
                intent.setClass(getContext(), MiaochaungZhuifeiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_wenshidu:
                intent.setClass(getContext(), WenshiduActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_bingchongfangzhi:
                intent.setClass(getContext(), BingchongHaiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yumiaofafang:
                intent.setClass(getContext(), YumiaoFafangActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_tengcha:
                intent.setClass(getContext(), TengchaActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_donggeng:
                intent.setClass(getContext(), DonggengActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_qilong:
                intent.setClass(getContext(), QilongActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_dongguan:
                intent.setClass(getContext(), DongguanActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yizai:
                intent.setClass(getContext(), YizaiGuanliActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_jifei:
                intent.setClass(getContext(), JifeiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_xuefei:
                intent.setClass(getContext(), XuefeiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_tianjianzhuifei:
                intent.setClass(getContext(), ZhuifeiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_jiemopeitu:
                intent.setClass(getContext(), JiemoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_dadingyiya:
                intent.setClass(getContext(), DadingYiyaActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_guangai:
                intent.setClass(getContext(), GuangaiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_zhibao:
                intent.setClass(getContext(), ZhibaoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_caishou:
                intent.setClass(getContext(), ChengshuCaishouActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_hongkao:
                intent.setClass(getContext(), HongkaoActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_hongkaoshi:
                intent.setClass(getContext(), HongkaoshiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_tianjianpinggu:
                intent.setClass(getContext(), TianjianpingguActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_kaohoupinggu:
                intent.setClass(getContext(), KaohoupingguActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_ziranzaihai:
                intent.setClass(getContext(), ZaihaiTongjiActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_baoxian:
                intent.setClass(getContext(), BaoxianActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_shougouchaxun:
                intent.setClass(getContext(), ShougouActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_yannongpingji:
                intent.setClass(getContext(), YannongPingjiActivity.class);
                startActivity(intent);
                break;
        }
    }
}
