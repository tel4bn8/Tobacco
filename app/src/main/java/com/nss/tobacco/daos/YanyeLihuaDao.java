package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.YanyeLihua;

import java.util.ArrayList;
import java.util.List;

/**
 * yanyelihuaDao
 */

public class YanyeLihuaDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public YanyeLihuaDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<YanyeLihua> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (YanyeLihua info : memberInfo) {
                // 向表yanyelihua中插入数据
                db.execSQL("INSERT INTO yanyelihua VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getWanggecode(),info.getYear(), info.getK(),info.getCl(),
                        info.getYanjian(), info.getHuanyuantang(),info.getZongtang(),info.getPicktime(),info.getTechnician(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(YanyeLihua entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("wanggecode", entity.getWanggecode());
        cv.put("year",entity.getYear());
        cv.put("k", entity.getK());
        cv.put("cl",entity.getCl());
        cv.put("yanjian", entity.getYanjian());
        cv.put("huanyuantang", entity.getHuanyuantang());
        cv.put("zongtang",entity.getZongtang());
        cv.put("picktime", entity.getPicktime());
        cv.put("technician",entity.getTechnician());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long lihua = db.insert("yanyelihua", null, cv);
        if(lihua>0){
            Log.i("TAG", " lihua: "+lihua);
        }
    }

    /**
     * 通过year来删除数据
     *
     * @param year
     */
    public void delData(String year) {
        String[] args = { year };
        db.delete("yanyelihua", "year=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + year);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM yanyelihua");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过年度查询信息,返回所有的数据
     *
     * @param year
     */
    public ArrayList<YanyeLihua> searchData(final String year) {
        String sql = "SELECT * FROM yanyelihua WHERE year =" + "'" + year + "'";
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<YanyeLihua> searchAllData() {
        String sql = "SELECT * FROM yanyelihua";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过年度来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE yanyelihua SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE year =" + "'" + whereName
                + "'";
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateByYear(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= db.update("yanyelihua", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE yanyelihua SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<YanyeLihua> ExecSQLForMemberInfo(String sql) {
        ArrayList<YanyeLihua> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            YanyeLihua info = new YanyeLihua();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setWanggecode(c.getString(c.getColumnIndex("wanggecode")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setK(c.getString(c.getColumnIndex("k")));
            info.setCl(c.getString(c.getColumnIndex("cl")));
            info.setYanjian(c.getString(c.getColumnIndex("yanjian")));
            info.setHuanyuantang(c.getString(c.getColumnIndex("huanyuantang")));
            info.setZongtang(c.getString(c.getColumnIndex("zongtang")));
            info.setPicktime(c.getString(c.getColumnIndex("picktime")));
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
