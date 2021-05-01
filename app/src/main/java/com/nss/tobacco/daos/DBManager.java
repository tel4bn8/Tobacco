package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.Sec_XiaoduInfoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/17 0017.
 *
 */

public class DBManager {
//
//    private DBHelper helper;
//    private SQLiteDatabase db;
//
//    /**
//     *
//     * @param context
//     */
//    public DBManager(Context context){
//        helper = new DBHelper(context);
//        db = helper.getWritableDatabase();
//    }
//
//    /**
//     *
//     *
//     * @param memberInfo
//     */
//    public void add(List<Sec_XiaoduInfoEntity> memberInfo){
//        db.beginTransaction();// 开始事务
//        try {
//            for (Sec_XiaoduInfoEntity info : memberInfo) {
//                Log.i(WirelessQA.TAG, "------add memberInfo----------");
//                Log.i(WirelessQA.TAG, "------add memberInfo----------"+info.farmer + "/" + info.createtime+ "/" + info.yaopin + "/" + info.sheshi + "/" +info.detail);
//                // 向表xiaodu中插入数据
//                db.execSQL("INSERT INTO xiaodu VALUES(null,?,?,?,?,?,?)", new Object[] { info.farmer, info.createtime,info.yaopin,info.sheshi,
//                        info.detail,info.state });
//            }
//            db.setTransactionSuccessful();// 事务成功
//        } finally {
//            db.endTransaction();// 结束事务
//        }
//    }
//    /**
//     * @param id
//     * @param farmer
//     * @param createtime
//     * @param yaopin
//     * @param sheshi
//     * @param detail
//     * @param state
//     */
//    public void add(String id, String farmer,String createtime, String yaopin, String sheshi, String detail,String state) {
//        Log.i(WirelessQA.TAG, "------add data----------");
//        ContentValues cv = new ContentValues();
//        cv.put("id", id);
//        cv.put("farmer",farmer);
//        cv.put("createtime", createtime);
//        cv.put("yaopin", yaopin);
//        cv.put("sheshi", sheshi);
//        cv.put("detail", detail);
//        cv.put("state",state);
//        db.insert("xiaodu", null, cv);
//        Log.i(WirelessQA.TAG, farmer +"/" + createtime+ "/"+ yaopin +  "/" + sheshi + "/"  +detail+ "/"  +state);
//    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
//    public void delData(String farmer) {
//        // ExecSQL("DELETE FROM info WHERE name ="+"'"+name+"'");
//        String[] args = { farmer };
//        db.delete("xiaodu", "farmer=?", args);
//        Log.i(WirelessQA.TAG, "delete data by " + farmer);
//
//    }
//
//    /**
//     * 清空数据
//     */
//    public void clearData(){
//        ExecSQL("DELETE FROM xiaodu");
//        Log.i(WirelessQA.TAG, "clear data");
//    }
//    /**
//     * 通过名字查询信息,返回所有的数据
//     *
//     * @param farmer
//     */
//    public ArrayList<Sec_XiaoduInfoEntity> searchData(final String farmer) {
//        String sql = "SELECT * FROM xiaodu WHERE farmer =" + "'" + farmer + "'";
//        return ExecSQLForMemberInfo(sql);
//    }
//
//    public ArrayList<Sec_XiaoduInfoEntity> searchAllData() {
//        String sql = "SELECT * FROM xiaodu";
//        return ExecSQLForMemberInfo(sql);
//    }
//
//    /**
//     * 通过名字来修改值
//     *
//     * @param raw
//     * @param rawValue
//     * @param whereName
//     */
//    public void updateData(String raw, String rawValue, String whereName) {
//        String sql = "UPDATE xiaodu SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
//                + "'";
//        ExecSQL(sql);
//        Log.i(WirelessQA.TAG, sql);
//    }
//    /**
//     * 修改某一列的值
//     *
//     * @param raw
//     * @param rawValue
//     */
//    public void updateLieData(String raw, String rawValue) {
//        String sql = "UPDATE xiaodu SET " + raw + " =" + " " + "'" + rawValue + "'" ;
//        ExecSQL(sql);
//        Log.i(WirelessQA.TAG, sql);
//    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
//    private ArrayList<Sec_XiaoduInfoEntity> ExecSQLForMemberInfo(String sql) {
//        ArrayList<Sec_XiaoduInfoEntity> list = new ArrayList();
//        Cursor c = ExecSQLForCursor(sql);
//        while (c.moveToNext()) {
//            Sec_XiaoduInfoEntity info = new Sec_XiaoduInfoEntity();
//            info.id = c.getString(c.getColumnIndex("id"));
//            info.farmer = c.getString(c.getColumnIndex("farmer"));
//            info.createtime = c.getString(c.getColumnIndex("createtime"));
//            info.yaopin = c.getString(c.getColumnIndex("yaopin"));
//            info.sheshi = c.getString(c.getColumnIndex("sheshi"));
//            info.detail = c.getString(c.getColumnIndex("detail"));
//            info.state = c.getString(c.getColumnIndex("state"));
//            list.add(info);
//        }
//        c.close();
//        return list;
//    }

//    /**
//     * 执行一个SQL语句
//     *
//     * @param sql
//     */
//    private void ExecSQL(String sql){
//        try {
//            db.execSQL(sql);
//            Log.i("execSql: ", sql);
//        } catch (Exception e) {
//            Log.e("ExecSQL Exception", e.getMessage());
//            e.printStackTrace();
//        }
//    }

//    /**
//     * 执行SQL，返回一个游标
//     *
//     * @param sql
//     * @return
//     */
//    private Cursor ExecSQLForCursor(String sql) {
//        Cursor c = db.rawQuery(sql, null);
//        return c;
//    }
//
//    public void closeDB() {
//        db.close();
//    }
}