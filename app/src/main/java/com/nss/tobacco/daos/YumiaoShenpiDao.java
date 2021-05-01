package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.YumiaoShenpi;

import java.util.ArrayList;
import java.util.List;

/**
 * yumiaoshenpi
 */

public class YumiaoShenpiDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public YumiaoShenpiDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     * @param memberInfo
     */
    public void add(List<YumiaoShenpi> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (YumiaoShenpi info : memberInfo) {
                // 向表yumiaoshenpi中插入数据
                db.execSQL("INSERT INTO yumiaoshenpi VALUES(?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getYear(), info.getType(),info.getArea(),
                        info.getNum(), info.getShenpiren1(),info.getShenpiren2(), info.getApplytime(),info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(YumiaoShenpi entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("year",entity.getYear());
        cv.put("type", entity.getType());
        cv.put("area",entity.getArea());
        cv.put("num", entity.getNum());
        cv.put("shenpiren1", entity.getShenpiren1());
        cv.put("shenpiren2",entity.getShenpiren2());
        cv.put("applytime",entity.getApplytime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long ymsp = db.insert("yumiaoshenpi", null, cv);
        if(ymsp>0){
            Log.i("TAG", " ymsp: "+ymsp);
        }
    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("yumiaoshenpi", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM yumiaoshenpi");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<YumiaoShenpi> searchData(final String farmer) {
        String sql = "SELECT * FROM yumiaoshenpi WHERE farmer =" + "'" + farmer + "'";
        Log.i("jichuDao1",farmer);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<YumiaoShenpi> searchAllData() {
        String sql = "SELECT * FROM yumiaoshenpi";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过姓名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE yumiaoshenpi SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
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
        int len= db.update("yumiaoshenpi", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE yumiaoshenpi SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<YumiaoShenpi> ExecSQLForMemberInfo(String sql) {
        ArrayList<YumiaoShenpi> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            YumiaoShenpi info = new YumiaoShenpi();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setType(c.getString(c.getColumnIndex("type")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setNum(c.getString(c.getColumnIndex("num")));
            info.setShenpiren1(c.getString(c.getColumnIndex("shenpiren1")));
            info.setShenpiren2(c.getString(c.getColumnIndex("shenpiren2")));
            info.setApplytime(c.getString(c.getColumnIndex("applytime")));
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
