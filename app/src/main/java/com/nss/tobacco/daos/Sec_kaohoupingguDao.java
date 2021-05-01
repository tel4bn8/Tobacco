package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.Sec_Kaohoupinggu;

import java.util.ArrayList;
import java.util.List;

/**
 * kaohoupingguDao
 */

public class Sec_kaohoupingguDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public Sec_kaohoupingguDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Sec_Kaohoupinggu> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Sec_Kaohoupinggu info : memberInfo) {
                // 向表kaohoupinggu中插入数据
                db.execSQL("INSERT INTO kaohoupinggu VALUES(?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getKangci(),info.getOneganshu(),
                        info.getOne(),info.getSum(),info.getCreatetime(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(Sec_Kaohoupinggu entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("kangci", entity.getKangci());
        cv.put("oneganshu",entity.getOneganshu());
        cv.put("one", entity.getOne());
        cv.put("sum",entity.getSum());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long lenkaohou = db.insert("kaohoupinggu", null, cv);
        if(lenkaohou>0){
            Log.i("TAG", " len: "+lenkaohou);
        }
    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("kaohoupinggu", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM kaohoupinggu");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<Sec_Kaohoupinggu> searchData(final String farmer) {
        String sql = "SELECT * FROM kaohoupinggu WHERE farmer =" + "'" + farmer + "'";
        Log.i("kaohouDao1",farmer);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Sec_Kaohoupinggu> searchAllData() {
        String sql = "SELECT * FROM kaohoupinggu";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE kaohoupinggu SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
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
        int len= db.update("kaohoupinggu", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE kaohoupinggu SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Sec_Kaohoupinggu> ExecSQLForMemberInfo(String sql) {
        ArrayList<Sec_Kaohoupinggu> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Sec_Kaohoupinggu info = new Sec_Kaohoupinggu();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setKangci(c.getString(c.getColumnIndex("kangci")));
            info.setOneganshu(c.getString(c.getColumnIndex("oneganshu")));
            info.setOne(c.getString(c.getColumnIndex("one")));
            info.setSum(c.getString(c.getColumnIndex("sum")));
            info.setCreatetime(c.getString(c.getColumnIndex("createtime")));
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
