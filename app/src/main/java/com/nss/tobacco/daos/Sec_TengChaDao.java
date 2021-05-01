package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.entity.Sec_TengChaEntity;
import com.nss.tobacco.entity.Sec_YumiaoFafangEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei on 2016/12/14.
 */

public class Sec_TengChaDao {
    private DBHelper mDbHelper;
    private SQLiteDatabase mDb;
    private Context mContext;
    public Sec_TengChaDao(Context context) {
        mContext=context;
        mDbHelper = new DBHelper(mContext);
        mDb = mDbHelper.getWritableDatabase();
    }

    /**
     * 添加多条数据
     */
    public void add(List<Sec_TengChaEntity> entity) {

        mDb.beginTransaction();
        try {
            for (Sec_TengChaEntity info : entity) {
                //向苗床追肥表中添加数据
                mDb.execSQL("INSERT INTO tengcha VALUES (?,?,?,?,?,?,?)",new Object[]{info.getId(),info.getFarmer(),info.getArea(),info.getCreateTime(),info.getDetail(),info.getState(),info.getPhotoPath()});
            }
            //事务成功
            mDb.setTransactionSuccessful();
        } finally {
            mDb.endTransaction();
        }
    }

    /**
     * 添加一条数据
     **/
    public boolean add(Sec_TengChaEntity info) {
        Log.i("TAG", "add: ");
        ContentValues values = new ContentValues();
        values.put("id", info.getId());
        values.put("farmer",info.getFarmer());
        values.put("area", info.getArea());
        values.put("createtime", info.getCreateTime());
        values.put("detail", info.getDetail());
        values.put("state", info.getState());
        values.put("photopath", info.getPhotoPath());

        long len = mDb.insert("tengcha",null,values);
        Log.i("TAG", "add: "+info.getId()+info.getFarmer());
        Log.i("TAG", "add: "+len);
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
        int deleteNum = mDb.delete("tengcha", "farmer=?", args);
        if (deleteNum > 0) {
            return true;
        }
        return false;
    }
    /**
     * 通过id来修改状态值
     */
    public int updateStateByFarmer(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("tengcha", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateById(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= mDb.update("tengcha", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 通过采集状态条件查询
     */
    public List<Sec_TengChaEntity> searchByState(String state){
        String sql = "SELECT * FROM tengcha WHERE state=" + "'" + state + "'";
        return ExecSQLForList(sql);
    }

    /**
     * 通过farmer条件来查询
     */
    public List<Sec_TengChaEntity> searchByName(String farmer) {
        String sql = "SELECT * FROM tengcha WHERE farmer=" + "'" + farmer + "'";
        return ExecSQLForList(sql);
    }


    /**
     * 查询苗床管理表中所有数据
     */
    public List<Sec_TengChaEntity> searchAll() {
        String sql = "SELECT * FROM tengcha";
        return ExecSQLForList(sql);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM tengcha");
    }

    /**
     * 执行SQl语句返回苗床追肥集合信息
     */
    public List<Sec_TengChaEntity> ExecSQLForList(String sql) {
        List<Sec_TengChaEntity> list = new ArrayList<>();
        Cursor cursor = ExecSQLForCursor(sql);
        while (cursor.moveToNext()) {
            Sec_TengChaEntity info = new Sec_TengChaEntity();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setFarmer(cursor.getString(cursor.getColumnIndex("farmer")));
            info.setArea(cursor.getString(cursor.getColumnIndex("area")));
            info.setCreateTime(cursor.getString(cursor.getColumnIndex("createtime")));
            info.setDetail(cursor.getString(cursor.getColumnIndex("detail")));
            info.setState(cursor.getString(cursor.getColumnIndex("state")));
            info.setPhotoPath(cursor.getString(cursor.getColumnIndex("photopath")));
            list.add(info);
        }
        return list;
    }

    /**
     * 通过名字来修改值
     */
    public void updateByFarmer(String raw, String value, String farmer) {
        String sql = "UPDATE tengcha SET " + raw + "=" + "'" + value + "'" + "WHERE farmer=" + "'" + farmer + "'";
        ExecSQL(sql);
    }

    /**
     * 修改某一列的值
     */
    public void updateRaw(String raw, String rawValue) {
        String sql = "UPDATE tengcha SET " + raw + " = " + "'" + rawValue + "'";
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
