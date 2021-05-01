package com.nss.tobacco.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nss.tobacco.R;
import com.nss.tobacco.entity.FarmerEntity;

import java.util.List;

/**
 * 自定义烟农姓名spinner适配器类
 */

public class FarmerAdapter extends BaseAdapter {
    private List<FarmerEntity> mList;
    private Context mContext;

    public FarmerAdapter(List<FarmerEntity> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    public FarmerAdapter() {
        super();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.item_myspinner, null);
        if(convertView!=null)
        {
            TextView _TextView1=(TextView)convertView.findViewById(R.id.spin_textView);
            _TextView1.setText(mList.get(position).getName());
        }
        return convertView;
    }
}