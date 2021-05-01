package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.YantianDangan;

import java.util.ArrayList;
import java.util.List;

/**
 * YantianDanganDao
 */

public class YantianDanganDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public YantianDanganDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<YantianDangan> memberInfo){
        db.beginTransaction();
        try {
            for (YantianDangan info : memberInfo){
                //向表中插入数据
                db.execSQL("INSERT INTO yantiandangan VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        new Object[]{ info.getId(),info.getFarmer(), info.getYear(), info.getArea(), info.getLng(), info.getLat(), info.getSrc(),info.getDixing(),
                                info.getSoil(), info.getPrecrop(), info.getWater(), info.getDilishuiping(), info.getCreatetime(),info.getDetail(), info.getState(), info.getPhotopath()});
                db.setTransactionSuccessful();
            }

        }finally {
            db.endTransaction();
        }
    }


    public void add(YantianDangan entity){
        ContentValues cv = new ContentValues();

        cv.put("id", entity.getId());
        cv.put("farmer", entity.getFarmer());
        cv.put("year", entity.getYear());
        cv.put("area", entity.getArea());
        cv.put("lng", entity.getLng());
        cv.put("lat", entity.getLat());
        cv.put("src", entity.getSrc());
        cv.put("dixing", entity.getDixing());
        cv.put("soil", entity.getSoil());
        cv.put("precrop", entity.getPrecrop());
        cv.put("water", entity.getWater());
        cv.put("dilishuiping", entity.getDilishuiping());
        cv.put("createtime",entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state", entity.getState());
        cv.put("photopath", entity.getPhotopath());
        long dangan = db.insert("yantiandangan", null, cv);
        if(dangan>0){
            Log.i("TAG", " dangan: "+dangan);
        }
    }



    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */

    public void delData(String farmer){
        String[] args = { farmer };
        db.delete("yantiandangan", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }



    /**
     * 清空数据
     */
    public void clearData() {

        ExecSQL("DELETE FROM yantiandangan");
        Log.i(WirelessQA.TAG, "clear data");
    }


    /**
     * 通过村庄查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<YantianDangan> searchData(final String farmer) {
        String sql = "SELECT * FROM yantiandangan WHERE farmer =" + "'" + farmer + "'";
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<YantianDangan> searchAllData() {
        String sql = "SELECT * FROM yantiandangan";
        return ExecSQLForMemberInfo(sql);
    }


    /**
     * 通过烟农姓名来修改值
     *
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE yantiandangan SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
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
        int len= db.update("yantiandangan", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE yantiandangan SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<YantianDangan> ExecSQLForMemberInfo(String sql) {
        ArrayList<YantianDangan> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            YantianDangan info = new YantianDangan();
            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setLng(c.getString(c.getColumnIndex("lng")));
            info.setLat(c.getString(c.getColumnIndex("lat")));
            info.setSrc(c.getString(c.getColumnIndex("src")));
            info.setDixing(c.getString(c.getColumnIndex("dixing")));
            info.setSoil(c.getString(c.getColumnIndex("soil")));
            info.setPrecrop(c.getString(c.getColumnIndex("precrop")));
            info.setWater(c.getString(c.getColumnIndex("water")));
            info.setDilishuiping(c.getString(c.getColumnIndex("dilishuiping")));
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
