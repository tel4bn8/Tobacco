package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.YantianGuihua;

import java.util.ArrayList;
import java.util.List;

/**
 * YantianGuihuaDao
 */

public class YantianGuihuaDao {

    private DBHelper helper;
    private SQLiteDatabase db;

    public YantianGuihuaDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<YantianGuihua> memberInfo){
        db.beginTransaction();
        try {
            for (YantianGuihua info : memberInfo){
                //向表中插入数据
                db.execSQL("INSERT INTO yantianguihua VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{ info.getId(),info.getVillage(), info.getYear(), info.getArea(), info.getLng(), info.getLat(), info.getDixing(),
                                info.getSoil(), info.getFeili(), info.getModitime(), info.getTechnician(), info.getDetail(), info.getState(), info.getPhotopath()});
                db.setTransactionSuccessful();
            }

        }finally {
                db.endTransaction();
        }
    }


    public void add(YantianGuihua entity){
        ContentValues cv = new ContentValues();

        cv.put("id", entity.getId());
        cv.put("village", entity.getVillage());
        cv.put("year", entity.getYear());
        cv.put("area", entity.getArea());
        cv.put("lng", entity.getLng());
        cv.put("lat", entity.getLat());
        cv.put("dixing", entity.getDixing());
        cv.put("soil", entity.getSoil());
        cv.put("feili", entity.getFeili());
        cv.put("moditime", entity.getModitime());
        cv.put("technician", entity.getTechnician());
        cv.put("detail", entity.getDetail());
        cv.put("state", entity.getState());
        cv.put("photopath", entity.getPhotopath());
        long guihua = db.insert("yantianguihua", null, cv);
        if(guihua>0){
            Log.i("TAG", " guihua: "+guihua);
        }
    }



    /**
     * 通过village来删除数据
     *
     * @param village
     */

    public void delData(String village){
        String[] args = { village };
        db.delete("yantianguihua", "village=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + village);
    }



    /**
     * 清空数据
     */
    public void clearData() {

        ExecSQL("DELETE FROM yantianguihua");
        Log.i(WirelessQA.TAG, "clear data");
    }


    /**
     * 通过村庄查询信息,返回所有的数据
     *
     * @param village
     */
    public ArrayList<YantianGuihua> searchData(final String village) {
        String sql = "SELECT * FROM yantianguihua WHERE village =" + "'" + village + "'";
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<YantianGuihua> searchAllData() {
        String sql = "SELECT * FROM yantianguihua";
        return ExecSQLForMemberInfo(sql);
    }


    /**
     * 通过村名来修改值
     *
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE yantianguihua SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE village =" + "'" + whereName
                + "'";
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateByVillage(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= db.update("yantianguihua", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE yantianguihua SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<YantianGuihua> ExecSQLForMemberInfo(String sql) {
        ArrayList<YantianGuihua> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            YantianGuihua info = new YantianGuihua();
            info.setId(c.getString(c.getColumnIndex("id")));
            info.setVillage(c.getString(c.getColumnIndex("village")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setLng(c.getString(c.getColumnIndex("lng")));
            info.setLat(c.getString(c.getColumnIndex("lat")));
            info.setDixing(c.getString(c.getColumnIndex("dixing")));
            info.setSoil(c.getString(c.getColumnIndex("soil")));
            info.setFeili(c.getString(c.getColumnIndex("feili")));
            info.setModitime(c.getString(c.getColumnIndex("moditime")));
            info.setTechnician(c.getString(c.getColumnIndex("technician")));
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
