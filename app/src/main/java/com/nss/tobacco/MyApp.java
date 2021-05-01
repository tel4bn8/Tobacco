package com.nss.tobacco;


import android.app.Application;
import android.content.Context;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.db.table.TableEntity;
import org.xutils.x;

/**
 * 创建的时候创建数据库
 */
public class MyApp extends Application {

    private DbManager.DaoConfig daoConfig;
    public static Context CONTEXT;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        CONTEXT=this;
        daoConfig=new DbManager.DaoConfig();
        daoConfig.setDbName("tobacco.db")
                .setAllowTransaction(true)
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //打开数据库的操作
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        //数据库升级操作
                    }
                })
                .setTableCreateListener(new DbManager.TableCreateListener() {
                    @Override
                    public void onTableCreated(DbManager db, TableEntity<?> table) {
                        //创建表的监听
                        Log.i("onTableCreated",table.getName());
                    }
                });
    }
    public  DbManager.DaoConfig getDaoConfig(){
        return daoConfig;
    }
 
}