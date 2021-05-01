package com.nss.tobacco.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.nss.tobacco.entity.Sec_HongkaoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * hongkaoguanli
 */

public class Sec_HongkaoDao {

    private DBHelper helper;
    private SQLiteDatabase db;

    public Sec_HongkaoDao(Context context){
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    /**
     * 向表info中增加一个成员信息
     *
     * @param memberInfo
     */
    public void add(List<Sec_HongkaoEntity> memberInfo) {
        db.beginTransaction();// 开始事务
        try {
            for (Sec_HongkaoEntity info : memberInfo) {
                // 向表hongkaoguanli中插入数据
                db.execSQL("INSERT INTO hongkaoguanli VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[] {info.getId(), info.getFarmer(), info.getArea(),info.getBeforeweight(),
                        info.getAfterweight(),info.getElectric(),info.getCoal(),info.getZhuanyehongkao(),info.getQuqingquza(),info.getMoney(), info.getCreatetime(), info.getDetail(), info.getState(), info.getPhotopath()});
            }
            db.setTransactionSuccessful();//事务成功
        } finally {
            db.endTransaction();//结束事务
        }
    }

    public void add(Sec_HongkaoEntity entity) {

        ContentValues cv = new ContentValues();
        cv.put("id", entity.getId());
        cv.put("farmer",entity.getFarmer());
        cv.put("area", entity.getArea());
        cv.put("beforeweight",entity.getBeforeweight());
        cv.put("afterweight", entity.getAfterweight());
        cv.put("electric",entity.getElectric());
        cv.put("coal",entity.getCoal());
        cv.put("zhuanyehongkao",entity.getZhuanyehongkao());
        cv.put("quqingquza",entity.getQuqingquza());
        cv.put("money",entity.getMoney());
        cv.put("createtime", entity.getCreatetime());
        cv.put("detail", entity.getDetail());
        cv.put("state",entity.getState());
        cv.put("photopath",entity.getPhotopath());
        long lenhonghao = db.insert("hongkaoguanli", null, cv);
        if(lenhonghao>0){
            Log.i("TAG", " lenhonghao: "+lenhonghao);
        }

    }

    /**
     * 通过farmer来删除数据
     *
     * @param farmer
     */
    public void delData(String farmer) {
        String[] args = { farmer };
        db.delete("hongkaoguanli", "farmer=?", args);
        Log.i(WirelessQA.TAG, "delete data by " + farmer);
    }

    /**
     * 清空数据
     */
    public void clearData() {
        ExecSQL("DELETE FROM hongkaoguanli");
        Log.i(WirelessQA.TAG, "clear data");
    }

    /**
     * 通过村名查询信息,返回所有的数据
     *
     * @param farmer
     */
    public ArrayList<Sec_HongkaoEntity> searchData(final String farmer) {
        String sql = "SELECT * FROM hongkaoguanli WHERE farmer =" + "'" + farmer + "'";
        return ExecSQLForMemberInfo(sql);
    }

    public ArrayList<Sec_HongkaoEntity> searchAllData() {
        String sql = "SELECT * FROM hongkaoguanli";
        return ExecSQLForMemberInfo(sql);
    }

    /**
     * 通过烟农来修改值
     * @param raw
     * @param rawValue
     * @param whereName
     */
    public void updateData(String raw, String rawValue, String whereName) {
        String sql = "UPDATE hongkaoguanli SET " + raw + " =" + " " + "'" + rawValue + "'" + " WHERE farmer =" + "'" + whereName
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
        String sql = "UPDATE hongkaoguanli SET " + raw + " =" + " " + "'" + rawValue + "'" ;
        ExecSQL(sql);
        Log.i(WirelessQA.TAG, sql);
    }
    /**
     * 执行SQL命令返回list
     *
     * @param sql
     * @return
     */
    private ArrayList<Sec_HongkaoEntity> ExecSQLForMemberInfo(String sql) {
        ArrayList<Sec_HongkaoEntity> list = new ArrayList();
        Cursor c = ExecSQLForCursor(sql);
        while (c.moveToNext()) {
            Sec_HongkaoEntity info = new Sec_HongkaoEntity();

            info.setId(c.getString(c.getColumnIndex("id")));
            info.setFarmer(c.getString(c.getColumnIndex("farmer")));
            info.setArea(c.getString(c.getColumnIndex("area")));
            info.setBeforeweight(c.getString(c.getColumnIndex("beforeweight")));
            info.setAfterweight(c.getString(c.getColumnIndex("afterweight")));
            info.setElectric(c.getString(c.getColumnIndex("electric")));
            info.setCoal(c.getString(c.getColumnIndex("coal")));
            info.setZhuanyehongkao(c.getString(c.getColumnIndex("zhuanyehongkao")));
            info.setQuqingquza(c.getString(c.getColumnIndex("quqingquza")));
            info.setMoney(c.getString(c.getColumnIndex("money")));
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
