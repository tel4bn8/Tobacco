package com.nss.tobacco.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 测试
 */
public class DBHelper  extends SQLiteOpenHelper {

    public static final String DB_NAME = "tobacco.db";
    /*public static final String DB_TABLE_NAME = "xiaodu";*/
    private static final int DB_VERSION=3;
    public DBHelper(Context context) {
        //Context context, String name, CursorFactory factory, int version
        //factory输入null,使用默认值
        super(context, DB_NAME, null, DB_VERSION);
    }
    //数据第一次创建的时候会调用onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建
        //烟田规划表
        db.execSQL("CREATE TABLE IF NOT EXISTS yantianguihua" +
                "(id VARCHAR PRIMARY KEY, village STRING,year STRING, area STRING,lng STRING,lat STRING," +
                "dixing STRING,tuzhi STRING,soil STRING,feili STRING,moditime STRING,technician STRING,detail STRING,state STRING,photopath STRING)");
        //基础设施表
        db.execSQL("CREATE TABLE IF NOT EXISTS jichusheshi" +
                "(id VARCHAR PRIMARY KEY, year STRING,village STRING, kaofangnum STRING,jijingnum STRING,createtime STRING," +
                "technician STRING,detail STRING,state STRING,photopath STRING)");
        //土壤检测表
        db.execSQL("CREATE TABLE IF NOT EXISTS turangjiance" +
                "(id VARCHAR PRIMARY KEY, wanggecode STRING,year STRING, youjizhi STRING,ph STRING,n STRING," +
                "p STRING,k STRING,cl STRING,picktime STRING,technician STRING,detail STRING,state STRING,photopath STRING)");
        //烟叶理化表
        db.execSQL("CREATE TABLE IF NOT EXISTS yanyelihua" +
                "(id VARCHAR PRIMARY KEY, wanggecode STRING,year STRING,k STRING,cl STRING,yanjian STRING," +
                "huanyuantang STRING,zongtang STRING,picktime STRING,technician STRING,detail STRING,state STRING,photopath STRING)");
        //烟农档案
        db.execSQL("CREATE TABLE IF NOT EXISTS yannongdangan" +
                "(id VARCHAR PRIMARY KEY, name STRING,idcard STRING,phone STRING,sex STRING,age STRING,workyear STRING,education STRING,area STRING,year STRING,skill STRING," +
                "credit STRING,grade STRING,village STRING,createtime STRING,detail STRING,technician STRING,state STRING,photopath STRING)");
        //烟田档案
        db.execSQL("CREATE TABLE IF NOT EXISTS yantiandangan" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,year STRING,area STRING,lng STRING,lat STRING,src STRING,dixing STRING,soil STRING,precrop STRING,water STRING," +
                "dilishuiping STRING,createtime STRING,detail STRING,state STRING,photopath STRING)");
        //种植审批
        db.execSQL("CREATE TABLE IF NOT EXISTS zhongzhishenpi" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,year STRING, type STRING,area STRING,shenpiren1 STRING," +
                "shenpiren2 STRING,createtime STRING,detail STRING,state STRING,photopath STRING)");
        //草签合同
        db.execSQL("CREATE TABLE IF NOT EXISTS hetongguanli" +
                "(id VARCHAR PRIMARY KEY, code STRING,year STRING, farmer STRING,type STRING,area1 STRING," +
                "num1 STRING,caoqiantime STRING,detail STRING,state STRING,photopath STRING)");
        //育苗户
        db.execSQL("CREATE TABLE IF NOT EXISTS yumiaohu" +
                "(id VARCHAR PRIMARY KEY, name STRING,idcard STRING,year STRING,sex STRING,age STRING,education STRING,village STRING,phone STRING,technician STRING," +
                "createtime STRING,yumiaogongchangid STRING,detail STRING,state STRING,photopath STRING)");
        //育苗工场
        db.execSQL("CREATE TABLE IF NOT EXISTS yumiaogongchang" +
                "(id VARCHAR PRIMARY KEY, name STRING,village STRING,structure STRING,area STRING,miaosum STRING,dapengnum STRING,buildtime STRING,technician STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");
        //育苗审批
        db.execSQL("CREATE TABLE IF NOT EXISTS yumiaoshenpi" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,year STRING,type STRING,area STRING,num STRING,shenpiren1 STRING,shenpiren2 STRING,applytime STRING," +
                "detail STRING,state STRING,photopath STRING)");
        //物资发放
        db.execSQL("CREATE TABLE IF NOT EXISTS wuzifafang" +
                "(id VARCHAR PRIMARY KEY, farmer STRING, name STRING,num STRING,danwei STRING,money STRING,createtime STRING,detail STRING,state STRING,photopath STRING)");
        //消毒表
        db.execSQL("CREATE TABLE IF NOT EXISTS xiaodu" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,createtime STRING, yaopin STRING,sheshi STRING,detail STRING,state STRING,photopath STRING)");

        //苗期管理表
        db.execSQL("CREATE TABLE IF NOT EXISTS miaoqiguanli" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,miaoqi STRING, createtime STRING,miaoqing STRING," +
                "zhungmiaolv STRING,detail STRING,state STRING,photopath STRING)");


        //苗床追肥表
        db.execSQL("CREATE TABLE IF NOT EXISTS miaochuangzhuifei"+
               "(id VARCHAR PRIMARY KEY, farmer STRING,type STRING,"+
                "num STRING,area STRING,way STRING,createtime STRING,detail STRING,state STRING,photopath STRING)"
        );
        //病虫防治表
        db.execSQL("CREATE TABLE IF NOT EXISTS bingchongfangzhi"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,yaopin STRING,nongdu STRING," +
                "way STRING,type STRING,rate STRING,area STRING,loss STRING,createtime STRING,"+
                "detail STRING,state STRING,photopath STRING)");
        //温湿度管理表
        db.execSQL("CREATE TABLE IF NOT EXISTS wenshiduguanli"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,wendu STRING,shidu STRING," +
                "miaoqing STRING,createtime STRING,detail STRING,state STRING,photopath STRING)");

        //育苗发放表
        db.execSQL("CREATE TABLE IF NOT EXISTS yumiaofafang"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,createtime STRING,type STRING," +
                "num STRING,detail STRING,state STRING,photopath STRING)");


        //整地起垄模块
        //腾茬表
        db.execSQL("CREATE TABLE IF NOT EXISTS tengcha"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,area STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");
        //冬耕表
        db.execSQL("CREATE TABLE IF NOT EXISTS donggeng"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,area STRING,depth STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");

        //起垄表
        db.execSQL("CREATE TABLE IF NOT EXISTS qilong"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,area STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");
        //冬灌表
        db.execSQL("CREATE TABLE IF NOT EXISTS dongguan"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,area STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");

        //移栽管理
        //移栽管理表
        db.execSQL("CREATE TABLE IF NOT EXISTS yizai"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,type STRING,area STRING," +
                "rowdis STRING,betwdis STRING,gaimo STRING,way STRING,fangzhi STRING," +
                "rate STRING,starttime STRING,endtime STRING,detail STRING,state STRING,photopath STRING)");

        //田间施肥
        //基肥表
        db.execSQL("CREATE TABLE IF NOT EXISTS jifei"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,createtime STRING,type STRING," +
                "num STRING,area STRING,detail STRING,state STRING,photopath STRING)");
        //穴肥表
        db.execSQL("CREATE TABLE IF NOT EXISTS xuefei"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,createtime STRING,type STRING," +
                "num STRING,area STRING,detail STRING,state STRING,photopath STRING)");
        //追肥表
        db.execSQL("CREATE TABLE IF NOT EXISTS zhuifei"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,createtime STRING,type STRING," +
                "num STRING,area STRING,detail STRING,state STRING,photopath STRING)");

        //田间管理
        //揭膜中耕培土表
        db.execSQL("CREATE TABLE IF NOT EXISTS jiemo"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,jiemo STRING,jiemotime STRING," +
                "xiaopeitu STRING,dapeitu STRING,area STRING,height STRING,paishui STRING," +
                "detail STRING,state STRING,photopath STRING)");
        //打顶抑芽表
        db.execSQL("CREATE TABLE IF NOT EXISTS dadingyiya"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,zhangshi STRING,yiyaji STRING," +
                "xianleitime STRING,dadingtime STRING,youhuatime STRING,youhuashu STRING,"+
                "detail STRING,state STRING,photopath STRING)");
        //灌溉表
        db.execSQL("CREATE TABLE IF NOT EXISTS guangai"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,worktime STRING,way STRING," +
                "area STRING,detail STRING,state STRING,photopath STRING)");
       //植保表
        db.execSQL("CREATE TABLE IF NOT EXISTS zhibao"+
                "(id VARCHAR PRIMARY KEY,farmer STRING,createtime STRING," +
                "type STRING,yaoming STRING,nongdu STRING,detail STRING,state STRING,photopath STRING)");

        //成熟采收
        db.execSQL("CREATE TABLE IF NOT EXISTS chengshucaishou" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,starttime STRING,endtime STRING,pick STRING,same STRING,kangci STRING,ganci STRING,xianyanfenlei STRING," +
                "zhuanyecaiji STRING,detail STRING,state STRING,photopath STRING)");
        //成熟采收
        db.execSQL("CREATE TABLE IF NOT EXISTS hongkaoguanli" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,area STRING,beforeweight STRING,afterweight STRING,electric STRING,coal STRING,zhuanyehongkao STRING,quqingquza STRING," +
                "money STRING,createtime STRING,detail STRING,state STRING,photopath STRING)");
        //烘烤师
        db.execSQL("CREATE TABLE IF NOT EXISTS hongkaoshi" +
                "(id VARCHAR PRIMARY KEY, name STRING,tel STRING,xiaoguo STRING,createtime STRING,detail STRING,technician STRING,state STRING,photopath STRING)");
        //田间评估
        db.execSQL("CREATE TABLE IF NOT EXISTS tianjianpinggu" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,area STRING,zhunum STRING,leafnum STRING,one STRING,sum STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");
        //烤后评估
        db.execSQL("CREATE TABLE IF NOT EXISTS kaohoupinggu" +
                "(id VARCHAR PRIMARY KEY, farmer STRING,kangci STRING,oneganshu STRING,one STRING,sum STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");
        //灾害统计
        db.execSQL("CREATE TABLE IF NOT EXISTS zaihaitongji " +
                "(id VARCHAR PRIMARY KEY, farmer STRING,type STRING,area1 STRING,area2 STRING,lose STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");
        //保险
        db.execSQL("CREATE TABLE IF NOT EXISTS baoxian " +
                "(id VARCHAR PRIMARY KEY, farmer STRING,toubaoarea STRING,lipeiarea STRING,hetongarea STRING," +
                "createtime STRING,detail STRING,state STRING,photopath STRING)");

    }
    //数据库第一次创建时onCreate方法会被调用，我们可以执行创建表的语句，当系统发现版本变化之后，会调用onUpgrade方法，我们可以执行修改表结构等语句
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //在表info中增加一列other
        //db.execSQL("ALTER TABLE info ADD COLUMN other STRING");
        Log.i("WIRELESSQA", "update sqlite "+oldVersion+"---->"+newVersion);
    }
}

