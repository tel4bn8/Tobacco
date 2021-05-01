package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.Sec_DongGengEntity;
import com.nss.tobacco.entity.Sec_TengChaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei on 2016/12/19.
 */

public class Sec_DongGengDao {
    private DBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context mContext;
    public Sec_DongGengDao(Context context) {
        mContext=context;
        mDbHelper = new DBHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * 添加多条数据
     */
    public void add(List<Sec_DongGengEntity> entity) {

        mDb.beginTransaction();
        try {
            for (Sec_DongGengEntity info : entity) {
                //向苗床追肥表中添加数据
                mDb.execSQL("INSERT INTO donggeng VALUES (?,?,?,?,?,?,?)",new Object[]{info.getId(),info.getFarmer(),info.getArea(),info.getDepth(),info.getCreatetime(),info.getDetail(),info.getState(),info.getPhotopath()});
            }
            //事务成功
            mDb.setTransactionSuccessful();
        } finally {
            mDb.endTransaction();
        }
    }


    /**
     * 通过id来修改状态值
     */
    public int updateStateByFarmer(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("donggeng", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateById(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("donggeng", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 通过采集状态条件查询
     */
    public List<Sec_DongGengEntity> searchByState(String state){
        String sql = "SELECT * FROM donggeng WHERE state=" + "'" + state + "'";
        return ExecSQLForList(sql);
    }

    /**
     * 添加一条数据
     **/
    public boolean add(Sec_DongGengEntity info) {
        Log.i("TAG", "add: "+info.getDepth());
        ContentValues values = new ContentValues();
        values.put("id", info.getId());
        values.put("farmer",info.getFarmer());
        values.put("area", info.getArea());
        values.put("depth", info.getDepth());
        values.put("createtime", info.getCreatetime());
        values.put("detail", info.getDetail());
        values.put("state", info.getState());
        values.put("photopath", info.getPhotopath());
        long len = mDb.insert("donggeng",null,values);
        if (len > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过farmer来删除数据
     */
    public boolean delete(String farmer) {
        String[] args = {farmer};
        //删除的条数
        int deleteNum = mDb.delete("donggeng", "farmer=?", args);
        if (deleteNum > 0) {
            return true;
        }
        return false;
    }

    /**
     * 通过farmer条件来查询
     */
    public List<Sec_DongGengEntity> searchByName(String farmer) {
        String sql = "SELECT * FROM donggeng WHERE farmer=" + "'" + farmer + "'";
        return ExecSQLForList(sql);
    }


    /**
     * 查询苗床管理表中所有数据
     */
    public List<Sec_DongGengEntity> searchAll() {
        String sql = "SELECT * FROM donggeng";
        return ExecSQLForList(sql);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM donggeng");
    }

    /**
     * 执行SQl语句返回苗床追肥集合信息
     */
    public List<Sec_DongGengEntity> ExecSQLForList(String sql) {
        List<Sec_DongGengEntity> list = new ArrayList<>();
        Cursor cursor = ExecSQLForCursor(sql);
        while (cursor.moveToNext()) {
            Sec_DongGengEntity info = new Sec_DongGengEntity();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setFarmer(cursor.getString(cursor.getColumnIndex("farmer")));
            info.setArea(cursor.getString(cursor.getColumnIndex("area")));
            info.setCreatetime(cursor.getString(cursor.getColumnIndex("createtime")));
            info.setDepth(cursor.getString(cursor.getColumnIndex("depth")));
            info.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            info.setState(cursor.getString(cursor.getColumnIndex("state")));
            info.setPhotopath(cursor.getString(cursor.getColumnIndex("photopath")));
            list.add(info);
        }
        return list;
    }

    /**
     * 通过名字来修改值
     */
    public void updateByFarmer(String raw, String value, String farmer) {
        String sql = "UPDATE donggeng SET " + raw + "=" + "'" + value + "'" + "WHERE farmer=" + "'" + farmer + "'";
        ExecSQL(sql);
    }

    /**
     * 修改某一列的值
     */
    public void updateRaw(String raw, String rawValue) {
        String sql = "UPDATE donggeng SET " + raw + " = " + "'" + rawValue + "'";
        ExecSQL(sql);
    }


    /**
     * 执行一个SQL语句
     */
    private void ExecSQL(String sql) {
        try {
            mDb.execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 执行一个SQl语句,返回一个游标
     */
    private Cursor ExecSQLForCursor(String sql) {
        Cursor cursor = mDb.rawQuery(sql,null);
        return cursor;
    }

    /**
     * 关闭数据库
     */
    public void closeDB() {
        mDb.close();
    }
}
