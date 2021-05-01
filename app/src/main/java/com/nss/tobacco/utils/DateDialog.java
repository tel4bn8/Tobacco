package com.nss.tobacco.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/10/19 0019.
 * 自定义的日期选择对话框
 */

public class DateDialog {
    private Calendar calendar; // 通过Calendar获取系统时间
    private Context context;

    public DateDialog(Context context) {
        this.context = context;


    }
    private StringBuffer sb;
    @SuppressLint("NewApi")
    public void setDate(final TextView text) {
        calendar = Calendar.getInstance();
        // 通过自定义控件AlertDialog实现
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View view = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.item_date_time_dialog, null);
        final DatePicker datePicker = (DatePicker) view
                .findViewById(R.id.date_picker);
        // 设置日期简略显示 否则详细显示 包括:星期周
        datePicker.setCalendarViewShown(false);
        // 初始化当前日期
        calendar.setTimeInMillis(System.currentTimeMillis());
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                    }
                });
        // 设置date布局
        builder.setView(view);
        builder.setTitle("设置日期信息");
        builder.setNegativeButton("确  定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 日期格式
                        sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        text.setText(sb);
                        Toast.makeText(context,sb,Toast.LENGTH_LONG).show();
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }
}
