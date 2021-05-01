package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.Sec_HongkaoshiEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * hongkaishidao
 */

public class Sec_HongkaoshiDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public Sec_HongkaoshiDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Sec_HongkaoshiEntity> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Sec_HongkaoshiEntity info : memberInfo) {
                // 向表hongkaoshi中插入数据
                db.execSQL("INSERT INTO hongkaoshi VALUES(?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getName(), info.getTel(),info.getXiaoguo(),
                        info.getCreatetime(), info.getDetail(),info.getTechnician(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(Sec_HongkaoshiEntity entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("name",entity.getName());
        cv.put("tel", entity.getTel());
        cv.put("xiaoguo",entity.getXiaoguo());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("technician",entity.getTechnician());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long hkslen = db.insert("hongkaoshi", null, cv);
        if(hkslen>0){
            Log.i("TAG", " len: "+hkslen);
        }


    }

    /**
     * 通过name来删除数据
     *
     * @param name
     */
    public void delData(String name) {
        String[] args = { name };
        db.delete("hongkaoshi", "name=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + name);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM hongkaoshi");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param name
     */
    public ArrayList<Sec_HongkaoshiEntity> searchData(final String name) {
        String sql = "SELECT * FROM hongkaoshi WHERE name =" + "'" + name + "'";
        Log.i("Dao1",name);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Sec_HongkaoshiEntity> searchAllData() {
        String sql = "SELECT * FROM hongkaoshi";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE hongkaoshi SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE name =" + "'" + whereName
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
        int len= db.update("hongkaoguanli", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE hongkaoshi SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Sec_HongkaoshiEntity> ExecSQLForMemberInfo(String sql) {
        ArrayList<Sec_HongkaoshiEntity> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Sec_HongkaoshiEntity info = new Sec_HongkaoshiEntity();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setName(c.getString(c.getColumnIndex("name")));
            info.setTel(c.getString(c.getColumnIndex("tel")));
            info.setXiaoguo(c.getString(c.getColumnIndex("xiaoguo")));
            info.setCreatetime(c.getString(c.getColumnIndex("createtime")));
            info.setDetail(c.getString(c.getColumnIndex("detail")));
            info.setTechnician(c.getString(c.getColumnIndex("technician")));
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
