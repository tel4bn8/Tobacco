package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.WuziFafang;

import java.util.ArrayList;
import java.util.List;

/**
 * wuzidao
 */

public class WuziFafangDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public WuziFafangDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<WuziFafang> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (WuziFafang info : memberInfo) {
                // 向表wuzifafang中插入数据
                db.execSQL("INSERT INTO wuzifafang VALUES(?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getName(),
                        info.getNum(), info.getDanwei(),info.getMoney(), info.getCreatetime(),info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(WuziFafang entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("name",entity.getName());
        cv.put("num", entity.getNum());
        cv.put("danwei",entity.getDanwei());
        cv.put("money",entity.getMoney());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long wuzi = db.insert("wuzifafang", null, cv);
        if(wuzi>0){
            Log.i("TAG", " wuzi: "+wuzi);
        }


    }

    /**
     * 通过name来删除数据
     *
     * @param name
     */
    public void delData(String name) {
        String[] args = { name };
        db.delete("wuzifafang", "name=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + name);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM wuzifafang");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过物资名查询信息,返回所有的数据
     *
     * @param name
     */
    public ArrayList<WuziFafang> searchData(final String name) {
        String sql = "SELECT * FROM wuzifafang WHERE name =" + "'" + name + "'";
        Log.i("wuziDao1",name);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<WuziFafang> searchAllData() {
        String sql = "SELECT * FROM wuzifafang";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE wuzifafang SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE name =" + "'" + whereName
                + "'";
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE wuzifafang SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<WuziFafang> ExecSQLForMemberInfo(String sql) {
        ArrayList<WuziFafang> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            WuziFafang info = new WuziFafang();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setName(c.getString(c.getColumnIndex("name")));
            info.setNum(c.getString(c.getColumnIndex("num")));
            info.setDanwei(c.getString(c.getColumnIndex("danwei")));
            info.setMoney(c.getString(c.getColumnIndex("money")));
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
     * 通过id来修改状态值
     */
    public int updateStateByFarmer(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= db.update("wuzifafang", values, "id=?", new String[]{id});
        return len;
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
