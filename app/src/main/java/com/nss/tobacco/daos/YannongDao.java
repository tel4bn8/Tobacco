package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.Yannong;

import java.util.ArrayList;
import java.util.List;

/**
 * 烟农档案
 */

public class YannongDao {

    private DBHelper helper;
    private SQLiteDatabase db;

    public YannongDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Yannong> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Yannong info : memberInfo) {
                // 向表yannongdangan中插入数据
                db.execSQL("INSERT INTO yannongdangan VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getName(),info.getIdcard(),info.getPhone(),
                        info.getSex(),info.getAge(),info.getWorkyear(),info.getEducation(),info.getArea(),info.getYear(),info.getSkill(), info.getCredit(),info.getGrade(),
                        info.getVillage(),info.getCreatetime(), info.getDetail(),info.getTechnician(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(Yannong entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("name",entity.getName());
        cv.put("idcard",entity.getIdcard());
        cv.put("phone",entity.getPhone());
        cv.put("sex",entity.getSex());
        cv.put("age",entity.getAge());
        cv.put("workyear",entity.getWorkyear());
        cv.put("education",entity.getEducation());
        cv.put("area",entity.getArea());
        cv.put("year",entity.getYear());
        cv.put("skill",entity.getSkill());
        cv.put("credit",entity.getCredit());
        cv.put("grade",entity.getGrade());
        cv.put("village", entity.getVillage());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("technician",entity.getTechnician());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long yanno = db.insert("yannongdangan", null, cv);
        if(yanno>0){
            Log.i("TAG", " yanno: "+yanno);
        }
    }

    /**
     * 通过name来删除数据
     * @param name
     */
    public void delData(String name) {
        String[] args = { name };
        db.delete("yannongdangan", "name=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + name);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM yannongdangan");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param name
     */
    public ArrayList<Yannong> searchData(final String name) {
        String sql = "SELECT * FROM yannongdangan WHERE name =" + "'" + name + "'";
        Log.i("yannongDao1",name);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Yannong> searchAllData() {
        String sql = "SELECT * FROM yannongdangan";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE yannongdangan SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE name =" + "'" + whereName
                + "'";
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateByName(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= db.update("yannongdangan", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE yannongdangan SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Yannong> ExecSQLForMemberInfo(String sql) {
        ArrayList<Yannong> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Yannong info = new Yannong();
            info.setId(c.getString(c.getColumnIndex("id")));
            info.setName(c.getString(c.getColumnIndex("name")));
            info.setIdcard(c.getString(c.getColumnIndex("idcard")));
            info.setPhone(c.getString(c.getColumnIndex("phone")));
            info.setSex(c.getString(c.getColumnIndex("sex")));
            info.setAge(c.getString(c.getColumnIndex("age")));
            info.setWorkyear(c.getString(c.getColumnIndex("workyear")));
            info.setEducation(c.getString(c.getColumnIndex("education")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setSkill(c.getString(c.getColumnIndex("skill")));
            info.setCredit(c.getString(c.getColumnIndex("credit")));
            info.setGrade(c.getString(c.getColumnIndex("grade")));
            info.setVillage(c.getString(c.getColumnIndex("village")));
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
