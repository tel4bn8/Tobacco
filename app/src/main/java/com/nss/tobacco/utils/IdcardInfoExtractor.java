package com.nss.tobacco.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by admin on 2016/11/5.
 */

public class IdcardInfoExtractor {

    // 年份
    private int year;
    // 月份
    private int month;
    // 日期
    private int day;
    // 性别
    private String gender;
    // 出生日期
    private Date birthday;
    //年龄
    private int age;

    /**
     * 通过构造方法初始化各个成员属性
     */
    public IdcardInfoExtractor(String idcard) {

        try {

                // 获取性别
                String id17 = idcard.substring(16, 17);
                if (Integer.parseInt(id17) % 2 != 0) {
                    this.gender = "男";
                } else {
                    this.gender = "女";
                }

                // 获取出生日期
                String birthday = idcard.substring(6, 14);
                Date birthdate = new SimpleDateFormat("yyyyMMdd")
                        .parse(birthday);
                this.birthday = birthdate;
                GregorianCalendar currentDay = new GregorianCalendar();
                currentDay.setTime(birthdate);
                this.year = currentDay.get(Calendar.YEAR);
                this.month = currentDay.get(Calendar.MONTH) + 1;
                this.day = currentDay.get(Calendar.DAY_OF_MONTH);

                //获取年龄
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy");
                String year=simpleDateFormat.format(new Date());
                this.age=Integer.parseInt(year)-this.year;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @return the month
     */
    public int getMonth() {
        return month;
    }

    /**
     * @return the day
     */
    public int getDay() {
        return day;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        return "性别：" + this.gender + ",出生日期："
                + this.birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
