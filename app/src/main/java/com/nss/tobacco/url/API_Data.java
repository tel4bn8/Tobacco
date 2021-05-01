package com.nss.tobacco.url;

/**
 * Created by admin on 2016/11/17.
 */

public class API_Data {
    private static final String IP="http://192.168.1.104/";
    public static final String LOGIN = IP+"tobacco-app/yannong/login";
    public static final String APP_UPDATE = IP+"tobacco-app/init/app";

    public static final String LOGOUT = IP + "tobacco-app/yannong/logout";
    public static final String GETLOGIN = IP + "tobacco-app/yannong/getLogin";
    public static final String VILLAGE = IP + "tobacco-app/yannong/queryBySupercode/{supercode}";

    public static final String YantianguihuaUP = IP + "tobacco-app/guanli/yantianguihua";
    public static final String JichushesheUP = IP + "tobacco-app/guanli/jichusheshi";
    public static final String TurangjianceUP = IP + "tobacco-app/guanli/turang";
    public static final String YanyelihuaUP = IP + "tobacco-app/guanli/yanyelihua ";

    public static final String YannongdanganUP = IP + "tobacco-app/yannong/yannong ";
    public static final String YantiandanganUP = IP + "tobacco-app/yannong/yantian ";
    public static final String ZhongzhishenpiUP = IP + "tobacco-app/guanli/zhongzhishenpi";
    public static final String HetongUP = IP + "tobacco-app/guanli/hetongguanli";

    public static final String YumiaohuUP = IP + "tobacco-app/yumiao/yumiaohu";
    public static final String YumiaogongchangUP = IP + "tobacco-app/yumiao/yumiaogongchang";
    public static final String YumiaoshenpiUP = IP + "tobacco-app/yumiao/yumiaoshenqing";

    public static final String WuzifafangUP = IP + "tobacco-app/guanli/wuzifafang";

    public static final String ChengshucaishouUP = IP + "tobacco-app/guanli/chengshucaishou";
    public static final String HongkaoUP = IP + "tobacco-app/guanli/hongkaoguanli";
    public static final String HongkaoshiUP = IP + "tobacco-app/guanli/hongkaoshi";

    public static final String TianjianpingguUP = IP + "tobacco-app/guanli/tianjianpinggu";
    public static final String KaohoupingguUP = IP + "tobacco-app/guanli/kaohoupinggu";
    public static final String ZaihaitongjiUP = IP + "tobacco-app/yannong/zaihaitongji";
    public static final String BaoxianUP = IP + "tobacco-app/guanli/baoxian";

    public static final String MAIOCHUANGZHUIFEI=IP+"tobacco-app/guanli/miaochuangzhuifei";
    //消毒记录接口
    public static final String XIAODU=IP+"tobacco-app/yumiao/xiaodu";
    //苗期管理接口
    public static final String MIAOQIGUANLI=IP+"tobacco-app/guanli/miaoqiguanli";
    //温湿度记录接口
    public static final String WENSHIDUGUANLI=IP+"tobacco-app/yumiao/wenshiduguanli";
    //病虫害防治记录接口
    public static final String BINGCHONGHAI=IP+"tobacco-app/guanli/bingchongfangzhi";
    //育苗发放接口
    public static final String YUMIAOFANGFA=IP+"tobacco-app/guanli/yanmiaofafang";

    //腾茬接口
    public static final String TENGCHA=IP+"tobacco-app/guanli/tengcha";
    //冬耕接口
    public static final String DONGGENG=IP+"tobacco-app/guanli/donggeng";
    //起垄接口
    public static final String QILONG=IP+"tobacco-app/guanli/qilong";
    //冬灌接口
    public static final String DONGGUAN=IP+"tobacco-app/guanli/dongguan";
    //移栽管理接口
    public static final String YIZAI=IP+"tobacco-app/guanli/yizai";
    //基肥接口
    public static final String JIFEI=IP+"tobacco-app/guanli/jifei";
    //穴肥接口
    public static final String XUEFEI=IP+"tobacco-app/guanli/xuefei";
    //追肥接口
    public static final String ZHUIFEI=IP+"tobacco-app/guanli/zhuifei";
    //揭膜中耕培土接口
    public static final String JIEMO=IP+"tobacco-app/guanli/zhonggengpeitu";
    //打顶抑芽接口
    public static final String DADINGYIYA=IP+"tobacco-app/guanli/dadingyiya";
    //灌溉接口
    public static final String GUANGAI=IP+"tobacco-app/guanli/yantianguangai";
    //植保接口
    public static final String ZHIBAO=IP+"tobacco-app/guanli/zhibao";
}
