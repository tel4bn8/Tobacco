package com.nss.tobacco.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;

/**
 *
 */
public class CommonUtil {

    /**
     * 获取SD卡路径
     */
    public static String getSDPath() {
        String sdPath = null;
        // 判断sd卡是否存在
        boolean sdCardExit = Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
        if (sdCardExit) {
            // 获取根目录
            sdPath = Environment.getExternalStorageDirectory().toString();
        }
        return sdPath;
    }

    /**
     * 返回32位UUID字符串
     * @return
     */
    public static String getUUID32(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    /**
     * 加载本地图片
     * @return
     */
    public static Bitmap getBitmapInLocal(String path) {
        try {
            FileInputStream fis = new FileInputStream(path);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 删除文件
     * @param filePath
     */
    public static void deleteFile(String filePath){
        if(filePath == null || "".equals(filePath)){
            return;
        }
        File file = new File(filePath);
        if(file.exists()){
            file.delete();
        }
    }

    /**
     * 图片文件进行压缩处理
     * @param sourceFilePath
     * @param targetFilePath
     * @return
     */
    public static boolean dealImage(String sourceFilePath, String targetFilePath, String location1){
        try{
            int dh = 1024;
            int dw = 768;
            BitmapFactory.Options factory=new BitmapFactory.Options();
            factory.inJustDecodeBounds=true; //当为true时  允许查询图片不为 图片像素分配内存
            InputStream is = new FileInputStream(sourceFilePath);
            Bitmap bmp=BitmapFactory.decodeStream(is, null, factory);
            int hRatio=(int)Math.ceil(factory.outHeight/(float)dh); //图片是高度的几倍
            int wRatio=(int)Math.ceil(factory.outWidth/(float)dw); //图片是宽度的几倍
            System.out.println("hRatio:"+hRatio+"  wRatio:"+wRatio);
            //缩小到  1/ratio的尺寸和 1/ratio^2的像素
            if(hRatio>1||wRatio>1){
                if(hRatio>wRatio){
                    factory.inSampleSize=hRatio;
                }
                else
                    factory.inSampleSize=wRatio;
            }
            factory.inJustDecodeBounds=false;
            is.close();
            is = new FileInputStream(sourceFilePath);
            bmp=BitmapFactory.decodeStream(is, null, factory);
            Bitmap newbm = addTimeFlag(bmp, location1);
            OutputStream outFile = new FileOutputStream(targetFilePath);
            newbm.compress(Bitmap.CompressFormat.JPEG, 60, outFile);
            outFile.close();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 添加时间水印
     */
    private static Bitmap addTimeFlag(Bitmap src, String location0){
        // 获取原始图片与水印图片的宽与高
        int w = src.getWidth();
        int h = src.getHeight();

        Bitmap newBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(newBitmap);
        // 往位图中开始画入src原始图片
        mCanvas.drawBitmap(src, 0, 0, null);
        //添加文字
        Paint textPaint = new Paint();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = sdf.format(new Date(System.currentTimeMillis()));
        textPaint.setColor(Color.WHITE) ;
        textPaint.setTextSize(26);
        String familyName = "宋体";
//        Typeface typeface = Typeface.create(familyName,
//                Typeface.BOLD_ITALIC);
//        textPaint.setTypeface(typeface);
//        textPaint.setTextAlign(Align.CENTER);

        mCanvas.drawText(time, (float)(w*1)/15, (float)(h*13.5)/15, textPaint);
        mCanvas.drawText(location0, (float)(w*1)/15, (float)(h*14.5)/15, textPaint);
        mCanvas.save(Canvas.ALL_SAVE_FLAG);
        mCanvas.restore();
        return newBitmap ;
    }
}
