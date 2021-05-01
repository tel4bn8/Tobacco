package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.HetongGuanli;

import java.util.ArrayList;
import java.util.List;

/**
 * hetongguanli
 */

public class HetongGuanliDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public HetongGuanliDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<HetongGuanli> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (HetongGuanli info : memberInfo) {
                // 向表hetongguanli中插入数据
                db.execSQL("INSERT INTO hetongguanli VALUES(?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getCode(),info.getYear(), info.getFarmer(),info.getType(),
                        info.getArea1(), info.getNum1(),info.getCaoqiantime(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(HetongGuanli entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("code", entity.getCode());
        cv.put("year",entity.getYear());
        cv.put("farmer", entity.getFarmer());
        cv.put("type",entity.getType());
        cv.put("area1", entity.getArea1());
        cv.put("num1", entity.getNum1());
        cv.put("caoqiantime",entity.getCaoqiantime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long hetong = db.insert("hetongguanli", null, cv);
        if(hetong>0){
            Log.i("TAG", " hetong: "+hetong);
        }
    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("hetongguanli", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM hetongguanli");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过姓名查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<HetongGuanli> searchData(final String farmer) {
        String sql = "SELECT * FROM hetongguanli WHERE farmer =" + "'" + farmer + "'";
        Log.i("hetongDao1",farmer);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<HetongGuanli> searchAllData() {
        String sql = "SELECT * FROM hetongguanli";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE hetongguanli SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
                + "'";
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateByFarmer(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= db.update("hetongguanli", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE hetongguanli SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<HetongGuanli> ExecSQLForMemberInfo(String sql) {
        ArrayList<HetongGuanli> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            HetongGuanli info = new HetongGuanli();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setCode(c.getString(c.getColumnIndex("code")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setType(c.getString(c.getColumnIndex("type")));
            info.setArea1(c.getString(c.getColumnIndex("area1")));
            info.setNum1(c.getString(c.getColumnIndex("num1")));
            info.setCaoqiantime(c.getString(c.getColumnIndex("caoqiantime")));
            info.setDetail(c.getString(c.getColumnIndex("detail")));
            info.setState(c.getString(c.getColumnIndex("state")));
            info.setPhotopath(c.getString(c.getColumnIndex("photopath")));
            list.add(info);
        }
        c.close();
        return list;
    }

    /**
     * 执行一个SQL语句
     *
     * @param sql
     */
    private void ExecSQL(String sql) {
        try {
            db.execSQL(sql);
            Log.i("execSql: ", sql);
        } catch (Exception e) {
            Log.e("ExecSQL Exception", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 执行SQL，返回一个游标
     *
     * @param sql
     * @return
     */
    private Cursor ExecSQLForCursor(String sql) {
        Cursor c = db.rawQuery(sql, null);
        return c;
    }

    public void closeDB() {
        db.close();
    }
}