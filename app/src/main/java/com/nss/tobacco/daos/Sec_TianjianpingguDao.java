package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.Sec_TianjianpingguEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * tianjianpingguDao
 */

public class Sec_TianjianpingguDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public Sec_TianjianpingguDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Sec_TianjianpingguEntity> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Sec_TianjianpingguEntity info : memberInfo) {
                // 向表tianjianpinggu中插入数据
                db.execSQL("INSERT INTO tianjianpinggu VALUES(?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getArea(),info.getZhunum(),
                        info.getLeafnum(),info.getOne(),info.getSum(),info.getCreatetime(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(Sec_TianjianpingguEntity entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("area", entity.getArea());
        cv.put("zhunum",entity.getZhunum());
        cv.put("leafnum", entity.getLeafnum());
        cv.put("one", entity.getOne());
        cv.put("sum",entity.getSum());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long lentjpg = db.insert("tianjianpinggu", null, cv);
        if(lentjpg>0){
            Log.i("TAG", " len: "+lentjpg);
        }


    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("tianjianpinggu", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM tianjianpinggu");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<Sec_TianjianpingguEntity> searchData(final String farmer) {
        String sql = "SELECT * FROM tianjianpinggu WHERE farmer =" + "'" + farmer + "'";
        Log.i("jichuDao1",farmer);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Sec_TianjianpingguEntity> searchAllData() {
        String sql = "SELECT * FROM tianjianpinggu";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE tianjianpinggu SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
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
        int len= db.update("tianjianpinggu", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE tianjianpinggu SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Sec_TianjianpingguEntity> ExecSQLForMemberInfo(String sql) {
        ArrayList<Sec_TianjianpingguEntity> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Sec_TianjianpingguEntity info = new Sec_TianjianpingguEntity();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setZhunum(c.getString(c.getColumnIndex("zhunum")));
            info.setLeafnum(c.getString(c.getColumnIndex("leafnum")));
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
