package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.MyApp;
import com.nss.tobacco.entity.Sec_CaishouEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 成熟采收
 */

public class Sec_CaishouDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public Sec_CaishouDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Sec_CaishouEntity> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Sec_CaishouEntity info : memberInfo) {
                // 向表chengshucaishou中插入数据
                db.execSQL("INSERT INTO chengshucaishou VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getStarttime(),info.getEndtime(),
                        info.getPick(),info.getSame(),info.getKangci(),info.getGanci(),info.getXianyanfenlei(), info.getZhuanyecaiji(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(Sec_CaishouEntity entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("starttime", entity.getStarttime());
        cv.put("endtime",entity.getEndtime());
        cv.put("pick", entity.getPick());
        cv.put("same", entity.getSame());
        cv.put("kangci",entity.getKangci());
        cv.put("ganci", entity.getGanci());
        cv.put("xianyanfenlei", entity.getXianyanfenlei());
        cv.put("zhuanyecaiji",entity.getZhuanyecaiji());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long lencaiji = db.insert("chengshucaishou", null, cv);
        if(lencaiji>0){
            Log.i("TAG", " len: "+lencaiji);
        }


    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("chengshucaishou", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM chengshucaishou");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<Sec_CaishouEntity> searchData(final String farmer) {
        String sql = "SELECT * FROM chengshucaishou WHERE farmer =" + "'" + farmer + "'";
        Log.i("jichuDao1",farmer);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Sec_CaishouEntity> searchAllData() {
        String sql = "SELECT * FROM chengshucaishou";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE chengshucaishou SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
                + "'";
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateByName(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= db.update("chengshucaishou", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE chengshucaishou SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Sec_CaishouEntity> ExecSQLForMemberInfo(String sql) {
        ArrayList<Sec_CaishouEntity> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Sec_CaishouEntity info = new Sec_CaishouEntity();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setStarttime(c.getString(c.getColumnIndex("starttime")));
            info.setEndtime(c.getString(c.getColumnIndex("endtime")));
            info.setPick(c.getString(c.getColumnIndex("pick")));
            info.setSame(c.getString(c.getColumnIndex("same")));
            info.setKangci(c.getString(c.getColumnIndex("kangci")));
            info.setGanci(c.getString(c.getColumnIndex("ganci")));
            info.setXianyanfenlei(c.getString(c.getColumnIndex("xianyanfenlei")));
            info.setZhuanyecaiji(c.getString(c.getColumnIndex("zhuanyecaiji")));
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
