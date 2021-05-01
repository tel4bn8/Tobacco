package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nss.tobacco.entity.YumiaoGongchang;

import java.util.ArrayList;
import java.util.List;

/**
 * yumaiogongchang
 */

public class YumiaoGongchangDao {
    private DBHelper helper;
    private SQLiteDatabase db;

    public YumiaoGongchangDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<YumiaoGongchang> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (YumiaoGongchang info : memberInfo) {
                // 向表yumiaogongchang中插入数据
                db.execSQL("INSERT INTO yumiaogongchang VALUES(?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getName(), info.getVillage(),info.getStructure(),
                        info.getArea(), info.getMiaosum(),info.getDapengnum(),info.getBuildtime(),info.getTechnician(),info.getCreatetime(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();// 事务成功
        } finally {
            db.endTransaction();// 结束事务
        }
    }

    public void add(YumiaoGongchang entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("name",entity.getName());
        cv.put("village", entity.getVillage());
        cv.put("structure",entity.getStructure());
        cv.put("area",entity.getArea());
        cv.put("miaosum",entity.getMiaosum());
        cv.put("dapengnum", entity.getDapengnum());
        cv.put("buildtime", entity.getBuildtime());
        cv.put("technician",entity.getTechnician());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long gongchang = db.insert("yumiaogongchang", null, cv);
        if(gongchang>0){
            Log.i("TAG", " gongchang: "+gongchang);
        }


    }

    /**
     * 通过village来删除数据
     *
     * @param village
     */
    public void delData(String village) {
        String[] args = { village };
        db.delete("yumiaogongchang", "village=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + village);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM yumiaogongchang");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param village
     */
    public ArrayList<YumiaoGongchang> searchData(final String village) {
        String sql = "SELECT * FROM yumiaogongchang WHERE village =" + "'" + village + "'";
        Log.i("gongchangDao1",village);
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<YumiaoGongchang> searchAllData() {
        String sql = "SELECT * FROM yumiaogongchang";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过村名来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE yumiaogongchang SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE village =" + "'" + whereName
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
        int len= db.update("yumiaogongchang", values, "id=?", new String[]{id});
        return len;
    }

    /**
     * 修改某一列的值
     *
     * @param raw
     * @param rawValue
     */
    public void updateLieData(String raw, String rawValue) {
        String sql = "UPDATE yumiaogongchang SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<YumiaoGongchang> ExecSQLForMemberInfo(String sql) {
        ArrayList<YumiaoGongchang> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            YumiaoGongchang info = new YumiaoGongchang();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setName(c.getString(c.getColumnIndex("name")));
            info.setVillage(c.getString(c.getColumnIndex("village")));
            info.setStructure(c.getString(c.getColumnIndex("structure")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setMiaosum(c.getString(c.getColumnIndex("miaosum")));
            info.setDapengnum(c.getString(c.getColumnIndex("dapengnum")));
            info.setBuildtime(c.getString(c.getColumnIndex("buildtime")));
            info.setTechnician(c.getString(c.getColumnIndex("technician")));
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
