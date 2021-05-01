package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.MyApp;
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.entity.Sec_WenshiduEntity;
import com.nss.tobacco.entity.Sec_YumiaoFafangEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class Sec_YumiaofafangDao {
    private DBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private  Context mContext;
    public Sec_YumiaofafangDao(Context context) {
        mContext=context;
        mDbHelper = new DBHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * 添加一条数据
     **/
    public boolean add(Sec_YumiaoFafangEntity info) {
        ContentValues values = new ContentValues();
        values.put("id", info.getId());
        values.put("farmer",info.getFarmer());
        values.put("type", info.getType());
        values.put("num", info.getNum());
        values.put("createtime",info.getCreatetime());
        values.put("detail", info.getDetail());

        values.put("state", info.getState());
        values.put("photopath",info.getPhotopath());
        long len = mDb.insert("yumiaofafang",null,values);
        if (len > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过farmer来删除数据
     */
    public boolean delete(String farmer) {
        String[] args = {farmer};
        //删除的条数
        int deleteNum = mDb.delete("yumiaofafang", "farmer=?", args);
        if (deleteNum > 0) {
            return true;
        }
        return false;
    }
    /**
     * 通过id来修改状态值
     */
    public int updateStateById(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("yumiaofafang", values, "id=?", new String[]{id});
        return len;
    }
    /**
     * 通过采集状态条件查询
     */
    public List<Sec_YumiaoFafangEntity> searchByState(String state){
        String sql = "SELECT * FROM yumiaofafang WHERE state=" + "'" + state + "'";
        return ExecSQLForList(sql);
    }

    /**
     * 通过farmer条件来查询
     */
    public List<Sec_YumiaoFafangEntity> searchByName(String farmer) {
        String sql = "SELECT * FROM yumiaofafang WHERE farmer=" + "'" + farmer + "'";
        return ExecSQLForList(sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateByFarmer(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("yumiaofafang", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 查询管理表中所有数据
     */
    public List<Sec_YumiaoFafangEntity> searchAll() {
        String sql = "SELECT * FROM yumiaofafang";
        return ExecSQLForList(sql);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM yumiaofafang");
    }

    /**
     * 执行SQl语句返回育苗发放集合信息
     */
    public List<Sec_YumiaoFafangEntity> ExecSQLForList(String sql) {
        List<Sec_YumiaoFafangEntity> list = new ArrayList<>();
        Cursor cursor = ExecSQLForCursor(sql);
        while (cursor.moveToNext()) {
            Sec_YumiaoFafangEntity info = new Sec_YumiaoFafangEntity();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setFarmer(cursor.getString(cursor.getColumnIndex("farmer")));
            info.setType(cursor.getString(cursor.getColumnIndex("type")));
            info.setNum(cursor.getString(cursor.getColumnIndex("num")));
            info.setCreatetime(cursor.getString(cursor.getColumnIndex("createtime")));
            info.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            info.setState(cursor.getString(cursor.getColumnIndex("state")));
            info.setPhotopath(cursor.getString(cursor.getColumnIndex("photopath")));
            list.add(info);
        }
        return list;
    }

    /**
     * 通过名字来修改值
     */
    public void updateByFarmer(String raw, String value, String farmer) {
        String sql = "UPDATE yumiaofafang SET " + raw + "=" + "'" + value + "'" + "WHERE farmer=" + "'" + farmer + "'";
        ExecSQL(sql);
    }

    /**
     * 修改某一列的值
     */
    public void updateRaw(String raw, String rawValue) {
        String sql = "UPDATE yumiaofafang SET " + raw + " = " + "'" + rawValue + "'";
        ExecSQL(sql);
    }


    /**
     * 执行一个SQL语句
     */
    private void ExecSQL(String sql) {
        try {
            mDb.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行一个SQl语句,返回一个游标
     */
    private Cursor ExecSQLForCursor(String sql) {
        Cursor cursor = mDb.rawQuery(sql,null);
        return cursor;
    }

    /**
     * 关闭数据库
     */
    public void closeDB() {
        mDb.close();
    }
}
