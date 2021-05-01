package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.Yumiaohu;

import java.util.ArrayList;
import java.util.List;

/**
 * yumiaohu
 */

public class YumiaohuDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public YumiaohuDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Yumiaohu> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Yumiaohu info : memberInfo) {
                // 向表yumiaohu中插入数据
                db.execSQL("INSERT INTO yumiaohu VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getName(), info.getIdcard(),info.getYear(),
                        info.getSex(), info.getAge(),info.getEducation(), info.getVillage(),info.getPhone(),info.getTechnician(),info.getCreatetime(),info.getYumiaogongchangid(),
                        info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(Yumiaohu entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("name",entity.getName());
        cv.put("idcard", entity.getIdcard());
        cv.put("year",entity.getYear());
        cv.put("sex", entity.getSex());
        cv.put("age", entity.getAge());
        cv.put("education",entity.getEducation());
        cv.put("village",entity.getVillage());
        cv.put("phone",entity.getPhone());
        cv.put("technician",entity.getTechnician());
        cv.put("createtime",entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long yumiao = db.insert("yumiaohu", null, cv);
        if(yumiao>0){
            Log.i("TAG", " yumiao: "+yumiao);
        }
    }

    /**
     * 通过name来删除数据
     * @param name
     */
    public void delData(String name) {
        String[] args = { name };
        db.delete("yumiaohu", "name=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + name);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM yumiaohu");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param name
     */
    public ArrayList<Yumiaohu> searchData(final String name) {
        String sql = "SELECT * FROM yumiaohu WHERE name =" + "'" + name + "'";
        Log.i("yumiaohuDao1",name);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Yumiaohu> searchAllData() {
        String sql = "SELECT * FROM yumiaohu";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE yumiaohu SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE name =" + "'" + whereName
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
        int len= db.update("yumiaohu", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE yumiaohu SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Yumiaohu> ExecSQLForMemberInfo(String sql) {
        ArrayList<Yumiaohu> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Yumiaohu info = new Yumiaohu();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setName(c.getString(c.getColumnIndex("name")));
            info.setIdcard(c.getString(c.getColumnIndex("idcard")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setSex(c.getString(c.getColumnIndex("sex")));
            info.setAge(c.getString(c.getColumnIndex("age")));
            info.setEducation(c.getString(c.getColumnIndex("education")));
            info.setVillage(c.getString(c.getColumnIndex("village")));
            info.setPhone(c.getString(c.getColumnIndex("phone")));
            info.setTechnician(c.getString(c.getColumnIndex("technician")));
            info.setCreatetime(c.getString(c.getColumnIndex("createtime")));
            info.setYumiaogongchangid(c.getString(c.getColumnIndex("yumiaogongchangid")));
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
