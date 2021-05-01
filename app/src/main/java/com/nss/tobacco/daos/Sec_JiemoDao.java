package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.MyApp;
import com.nss.tobacco.entity.Sec_GuangaiEntity;
import com.nss.tobacco.entity.Sec_JiemoEntity;
import com.nss.tobacco.entity.Sec_ZhuifeiEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/12 0012.
 */

public class Sec_JiemoDao {
    private DBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context mContext;
    public Sec_JiemoDao(Context context) {
        mContext=context;
        mDbHelper = new DBHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * 添加一条数据
     **/
    public boolean add(Sec_JiemoEntity info) {
        ContentValues values = new ContentValues();
        values.put("id", info.getId());
        values.put("farmer",info.getFarmer());
        values.put("jiemo",info.getJiemo());
        values.put("jiemotime",info.getJiemotime());
        values.put("xiaopeitu",info.getXiaopeitu());
        values.put("dapeitu",info.getDapeitu());
        values.put("area",info.getArea());
        values.put("height",info.getHeight());
        values.put("paishui",info.getPaishui());
        values.put("detail",info.getDetail());
        values.put("state",info.getState());
        values.put("photopath",info.getPhotoPath());
        long len = mDb.insert("jiemo",null,values);

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
        int deleteNum = mDb.delete("jiemo", "farmer=?", args);
        if (deleteNum > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过farmer条件来查询
     */
    public List<Sec_JiemoEntity> searchByName(String farmer) {
        String sql = "SELECT * FROM jiemo WHERE farmer=" + "'" + farmer + "'";
        return ExecSQLForList(sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateById(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("jiemo", values, "id=?", new String[]{id});
        return len;
    }
    /**
     * 通过采集状态条件查询
     */
    public List<Sec_JiemoEntity> searchByState(String state){
        String sql = "SELECT * FROM jiemo WHERE state=" + "'" + state + "'";
        return ExecSQLForList(sql);
    }

    /**
     * 查询揭膜管理表中所有数据
     */
    public List<Sec_JiemoEntity> searchAll() {
        String sql = "SELECT * FROM jiemo";
        return ExecSQLForList(sql);
    }
    /**
     * 通过id来修改状态值
     */
    public int updateStateByFarmer(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("jiemo", values, "id=?", new String[]{id});
        return len;
    }
    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM jiemo");
    }

    /**
     * 执行SQl语句返回揭膜追肥集合信息
     */
    public List<Sec_JiemoEntity> ExecSQLForList(String sql) {
        List<Sec_JiemoEntity> list = new ArrayList<>();
        Cursor cursor = ExecSQLForCursor(sql);
        while (cursor.moveToNext()) {
            Sec_JiemoEntity info = new Sec_JiemoEntity();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setFarmer(cursor.getString(cursor.getColumnIndex("farmer")));
            info.setJiemo(cursor.getString(cursor.getColumnIndex("jiemo")));
            info.setJiemotime(cursor.getString(cursor.getColumnIndex("jiemotime")));
            info.setXiaopeitu(cursor.getString(cursor.getColumnIndex("xiaopeitu")));
            info.setDapeitu(cursor.getString(cursor.getColumnIndex("dapeitu")));
            info.setArea(cursor.getString(cursor.getColumnIndex("area")));
            info.setHeight(cursor.getString(cursor.getColumnIndex("height")));
            info.setPaishui(cursor.getString(cursor.getColumnIndex("paishui")));
            info.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            info.setState(cursor.getString(cursor.getColumnIndex("state")));
            info.setPhotoPath(cursor.getString(cursor.getColumnIndex("photopath")));
            list.add(info);

        }

        return list;
    }

    /**
     * 通过名字来修改值
     */
    public void updateByFarmer(String raw, String value, String farmer) {
        String sql = "UPDATE jiemo SET " + raw + "=" + "'" + value + "'" + "WHERE farmer=" + "'" + farmer + "'";
        ExecSQL(sql);
    }

    /**
     * 修改某一列的值
     */
    public void updateRaw(String raw, String rawValue) {
        String sql = "UPDATE jiemo SET " + raw + " = " + "'" + rawValue + "'";
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
