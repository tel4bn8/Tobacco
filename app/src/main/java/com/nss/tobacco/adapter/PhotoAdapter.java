package com.nss.tobacco.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.nss.tobacco.R;
import com.nss.tobacco.activity.ShowImageActivity;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.Constants;

import java.util.List;

/**
 * Created by lei on 2016/12/13.
 */

public class PhotoAdapter extends BaseAdapter{
    private Context mContext;
    private List<String> listPhoto;
    private int mScreenWidth;
    public PhotoAdapter(Context context,List<String> list,int screenWidth){
        this.mContext=context;
        this.listPhoto=list;
        this.mScreenWidth=screenWidth;
    }
    @Override
    public int getCount() {
        return listPhoto.size();
    }

    @Override
    public String getItem(int position) {
        return listPhoto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView=LayoutInflater.from(mContext).inflate(R.layout.activity_show_image,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
             viewHolder= (ViewHolder) convertView.getTag();
        }
       int imageWidth = mScreenWidth / Constants.MAX_NUM_COLUMNS;
       int imageHeight = imageWidth * 4 / 3 ;
       RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
       viewHolder.imageView.setLayoutParams(params);
        //绑定数据
        viewHolder.imageView.setImageBitmap(CommonUtil.getBitmapInLocal(getItem(position)));
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ShowImageActivity.class);
                intent.putExtra("ImageUrl",listPhoto.get(position));
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    static class ViewHolder{
        ImageView imageView;
        public ViewHolder(View convertView){
            imageView= (ImageView) convertView.findViewById(R.id.caremaImage);
        }

    }

}
