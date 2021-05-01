package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.MyApp;
import com.nss.tobacco.entity.Sec_DadingEntity;
import com.nss.tobacco.entity.Sec_JiemoEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class Sec_DadingDao {
    private DBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context mContext;
    public Sec_DadingDao(Context context) {
        mContext=context;
        mDbHelper = new DBHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }


    /**
     * 添加一条数据
     **/
    public boolean add(Sec_DadingEntity info){
        ContentValues values = new ContentValues();
        values.put("id", info.getId());
        values.put("farmer",info.getFarmer());
        values.put("zhangshi",info.getZhangshi());
        values.put("yiyaji",info.getYiyaji());
        values.put("xianleitime",info.getXianleitime());
        values.put("dadingtime",info.getDadingtime());
        values.put("youhuatime",info.getYouhuatime());
        values.put("youhuashu",info.getYouhuashu());
        values.put("detail",info.getDetail());
        values.put("state",info.getState());
        values.put("photopath",info.getPhotopath());
        long len = mDb.insert("dadingyiya",null,values);
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
        int deleteNum = mDb.delete("dadingyiya", "farmer=?", args);
        if (deleteNum > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过farmer条件来查询
     */
    public List<Sec_DadingEntity> searchByName(String farmer) {
        String sql = "SELECT * FROM dadingyiya WHERE farmer=" + "'" + farmer + "'";
        return ExecSQLForList(sql);
    }


    /**
     * 通过id来修改状态值
     */
    public int updateStateByFarmer(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("dadingyiya", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateById(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("dadingyiya", values, "id=?", new String[]{id});
        return len;
    }
    /**
     * 通过采集状态条件查询
     */
    public List<Sec_DadingEntity> searchByState(String state){
        String sql = "SELECT * FROM dadingyiya WHERE state=" + "'" + state + "'";
        return ExecSQLForList(sql);
    }



    /**
     * 查询揭膜管理表中所有数据
     */
    public List<Sec_DadingEntity> searchAll() {
        String sql = "SELECT * FROM dadingyiya";
        return ExecSQLForList(sql);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM dadingyiya");
    }

    /**
     * 执行SQl语句返回揭膜追肥集合信息
     */
    public List<Sec_DadingEntity> ExecSQLForList(String sql) {
        List<Sec_DadingEntity> list = new ArrayList<>();
        Cursor cursor = ExecSQLForCursor(sql);
        while (cursor.moveToNext()) {
            Sec_DadingEntity info = new Sec_DadingEntity();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setFarmer(cursor.getString(cursor.getColumnIndex("farmer")));
            info.setZhangshi(cursor.getString(cursor.getColumnIndex("zhangshi")));
            info.setYiyaji(cursor.getString(cursor.getColumnIndex("yiyaji")));
            info.setXianleitime(cursor.getString(cursor.getColumnIndex("xianleitime")));
            info.setDadingtime(cursor.getString(cursor.getColumnIndex("dadingtime")));
            info.setYouhuatime(cursor.getString(cursor.getColumnIndex("youhuatime")));
            info.setYouhuashu(cursor.getString(cursor.getColumnIndex("youhuashu")));
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
        String sql = "UPDATE dadingyiya SET " + raw + "=" + "'" + value + "'" + "WHERE farmer=" + "'" + farmer + "'";
        ExecSQL(sql);
    }

    /**
     * 修改某一列的值
     */
    public void updateRaw(String raw, String rawValue) {
        String sql = "UPDATE dadingyiya SET " + raw + " = " + "'" + rawValue + "'";
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
