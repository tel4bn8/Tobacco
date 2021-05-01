package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.ZhongzhiShenpi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/11/7.
 */

public class ZhongzhiShenpiDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public ZhongzhiShenpiDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<ZhongzhiShenpi> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (ZhongzhiShenpi info : memberInfo) {
                // 向表zhongzhishenpi中插入数据
                db.execSQL("INSERT INTO zhongzhishenpi VALUES(?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getYear(),info.getType(),
                        info.getArea(), info.getShenpiren1(),info.getShenpiren2(), info.getCreatetime(),info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(ZhongzhiShenpi entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("year", entity.getYear());
        cv.put("type",entity.getType());
        cv.put("area", entity.getArea());
        cv.put("shenpiren1", entity.getShenpiren1());
        cv.put("shenpiren2",entity.getShenpiren2());
        cv.put("createtime",entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long len1 = db.insert("zhongzhishenpi", null, cv);
        if(len1>0){
            Log.i("TAG", " len1: "+len1);
        }

    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("zhongzhishenpi", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM zhongzhishenpi");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<ZhongzhiShenpi> searchData(final String farmer) {
        String sql = "SELECT * FROM zhongzhishenpi WHERE farmer =" + "'" + farmer + "'";
        Log.i("zhongzhishenpiDao1",farmer);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<ZhongzhiShenpi> searchAllData() {
        String sql = "SELECT * FROM zhongzhishenpi";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE zhongzhishenpi SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
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
        int len= db.update("zhongzhishenpi", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE zhongzhishenpi SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<ZhongzhiShenpi> ExecSQLForMemberInfo(String sql) {
        ArrayList<ZhongzhiShenpi> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            ZhongzhiShenpi info = new ZhongzhiShenpi();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setType(c.getString(c.getColumnIndex("type")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setShenpiren1(c.getString(c.getColumnIndex("shenpiren1")));
            info.setShenpiren2(c.getString(c.getColumnIndex("shenpiren2")));
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
