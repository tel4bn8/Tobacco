package com.nss.tobacco.daos;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.nss.tobacco.MyApp;
import com.nss.tobacco.entity.UserInfo;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.List;

/**
 * 用户操作
 */

public class UserInfoDao {

    public static boolean isSave;
    public static boolean isUpdate;
    private Context context;
    private final static DbManager manager= x.getDb(new MyApp().getDaoConfig());
    public final static String ACTION="SAVEORUPDATEFINISH";

    public UserInfoDao(Context context) {
        this.context = context;
    }

    //保存数据到数据库
    public  boolean saveUserInfo(UserInfo userInfo){
        try {

            isSave=manager.saveBindingId(userInfo);
            sendFinishBroad();
            return isSave;

        } catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }
    //查询数据
    public UserInfo getUserInfoFromDb(String phoneNum){
        UserInfo userInfo=null;
        try {
            userInfo= manager.findById(UserInfo.class,phoneNum);
            sendFinishBroad();
        } catch (DbException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
    //查询第一个数据
    public UserInfo getUserInfoFromDb(){
        UserInfo userInfo=null;
        try {
            userInfo= manager.findFirst(UserInfo.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        return userInfo;
    }
    //查询所有的数据
    public void getAll(){
        try {
            List<UserInfo> list = manager.findAll(UserInfo.class);
            for (UserInfo userInfo:list){
                Log.i("getAll",userInfo.toString());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    //更新数据
    public void upUserInfo(UserInfo userInfo){

        try {
            manager.saveOrUpdate(userInfo);
            isUpdate=true;
            sendFinishBroad();
        } catch (DbException e) {
            e.printStackTrace();
        }
    }
    //增加列数
    public void addColum(String newColum){
        try {
            manager.addColumn(UserInfo.class,newColum);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    //发送广播
    public void sendFinishBroad(){
        Intent intent=new Intent();
        intent.setAction(ACTION);
        context.sendBroadcast(intent);
    }
}
