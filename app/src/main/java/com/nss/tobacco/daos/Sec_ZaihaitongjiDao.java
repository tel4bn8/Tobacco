package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.MyApp;
import com.nss.tobacco.entity.Sec_Zaihaitongji;
import com.nss.tobacco.entity.Sec_ZaihaitongjiEntity;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * zaihaiDao
 */

public class Sec_ZaihaitongjiDao {

    private DBHelper helper;
    private SQLiteDatabase db;

    public Sec_ZaihaitongjiDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Sec_Zaihaitongji> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Sec_Zaihaitongji info : memberInfo) {
                // 向表zaihaitongji中插入数据
                db.execSQL("INSERT INTO zaihaitongji VALUES(?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getType(),info.getArea1(),
                        info.getArea2(), info.getLose(),info.getCreatetime(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(Sec_Zaihaitongji entity) {
        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("type", entity.getType());
        cv.put("area1",entity.getArea1());
        cv.put("area2", entity.getArea2());
        cv.put("lose",entity.getLose());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long len1zaihai = db.insert("zaihaitongji", null, cv);
        if(len1zaihai>0){
            Log.i("TAG", " len: "+len1zaihai);
        }
    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("zaihaitongji", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM zaihaitongji");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<Sec_Zaihaitongji> searchData(final String farmer) {
        String sql = "SELECT * FROM zaihaitongji WHERE farmer =" + "'" + farmer + "'";
        Log.i("jichuDao1",farmer);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Sec_Zaihaitongji> searchAllData() {
        String sql = "SELECT * FROM zaihaitongji";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE zaihaitongji SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
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
        int len= db.update("zaihaitongji", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE zaihaitongji SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Sec_Zaihaitongji> ExecSQLForMemberInfo(String sql) {
        ArrayList<Sec_Zaihaitongji> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Sec_Zaihaitongji info = new Sec_Zaihaitongji();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setType(c.getString(c.getColumnIndex("type")));
            info.setArea1(c.getString(c.getColumnIndex("area1")));
            info.setArea2(c.getString(c.getColumnIndex("area2")));
            info.setLose(c.getString(c.getColumnIndex("lose")));
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
