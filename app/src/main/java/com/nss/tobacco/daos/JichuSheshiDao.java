package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.nss.tobacco.entity.JichuSheshi;

import java.util.ArrayList;
import java.util.List;

/**
 * 设施表操作类
 */

public class JichuSheshiDao {

    private DBHelper helper;
    private SQLiteDatabase db;

    public JichuSheshiDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<JichuSheshi> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (JichuSheshi info : memberInfo) {
                // 向表jichusheshi中插入数据
                db.execSQL("INSERT INTO jichusheshi VALUES(?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getYear(), info.getVillage(),info.getKaofangnum(),
                        info.getJijingnum(), info.getCreatetime(),info.getTechnician(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(JichuSheshi entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("year",entity.getYear());
        cv.put("village", entity.getVillage());
        cv.put("kaofangnum",entity.getKaofangnum());
        cv.put("jijingnum", entity.getJijingnum());
        cv.put("createtime", entity.getCreatetime());
        cv.put("technician",entity.getTechnician());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long len = db.insert("jichusheshi", null, cv);
        if(len>0){
            Log.i("TAG", " len: "+len);
        }


    }

    /**
     * 通过village来删除数据
     *
     * @param village
     */
    public void delData(String village) {
        String[] args = { village };
        db.delete("jichusheshi", "village=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + village);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM jichusheshi");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param village
     */
    public ArrayList<JichuSheshi> searchData(final String village) {
        String sql = "SELECT * FROM jichusheshi WHERE village =" + "'" + village + "'";
        Log.i("jichuDao1",village);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<JichuSheshi> searchAllData() {
        String sql = "SELECT * FROM jichusheshi";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE jichusheshi SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE village =" + "'" + whereName
                + "'";
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }

    /**
     * 通过id来修改状态值
     */
    public int updateStateByVillage(String state, String id) {
        ContentValues values=new ContentValues();
        values.put("state",state);
        int len= db.update("jichusheshi", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE jichusheshi SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<JichuSheshi> ExecSQLForMemberInfo(String sql) {
        ArrayList<JichuSheshi> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            JichuSheshi info = new JichuSheshi();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setYear(c.getString(c.getColumnIndex("year")));
            info.setVillage(c.getString(c.getColumnIndex("village")));
            info.setKaofangnum(c.getString(c.getColumnIndex("kaofangnum")));
            info.setJijingnum(c.getString(c.getColumnIndex("jijingnum")));
            info.setCreatetime(c.getString(c.getColumnIndex("createtime")));
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
