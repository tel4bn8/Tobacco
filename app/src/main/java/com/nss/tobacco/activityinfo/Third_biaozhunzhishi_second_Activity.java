package com.nss.tobacco.activityinfo;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.nss.tobacco.R;

import java.io.File;

public class Third_biaozhunzhishi_second_Activity extends AppCompatActivity implements OnPageChangeListener
        , OnLoadCompleteListener, OnDrawListener ,View.OnClickListener{
    private PDFView pdfView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_biaozhunzhishi_second);
        TextView textView = (TextView) findViewById(R.id.textView_item);
        textView.setText("烟用物资管理办法");
        ImageView iv = (ImageView) findViewById(R.id.back_item);
        iv.setOnClickListener(this);
        pdfView = (PDFView) findViewById( R.id.pdfView );
        //从assets目录读取pdf
        displayFromAssets("biaozhunku2.pdf");
       /* //从文件中读取pdf
        displayFromFile( new File( "fileName"));*/

    }
    private void displayFromAssets(String assetFileName ) {
        pdfView.fromAsset(assetFileName)   //设置pdf文件地址
                .defaultPage(1)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical( false )  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻页
                // .pages( 2 , 3 , 4 , 5  )  //把2 , 3 , 4 , 5 过滤掉
                .load();
    }
    private void displayFromFile( File file ) {
        pdfView.fromFile(file)   //设置pdf文件地址
                .defaultPage(6)         //设置默认显示第1页
                .onPageChange(this)     //设置翻页监听
                .onLoad(this)           //设置加载监听
                .onDraw(this)            //绘图监听
                .showMinimap(false)     //pdf放大的时候，是否在屏幕的右上角生成小地图
                .swipeVertical( false )  //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                .enableSwipe(true)   //是否允许翻页，默认是允许翻
                // .pages( 2 , 3 , 4 , 5  )  //把2 , 3 , 4 , 5 过滤掉
                .load();
    }

    @Override
    public void onLayerDrawn(Canvas canvas, float pageWidth, float pageHeight, int displayedPage) {
    }
    /**
     * 加载完成回调
     * @param nbPages  总共的页数
     */
    @Override
    public void loadComplete(int nbPages) {
        Toast.makeText(this ,  "加载完成" + nbPages  , Toast.LENGTH_SHORT).show();

    }
    /**
     * 翻页回调
     * @param page
     * @param pageCount
     */
    @Override
    public void onPageChanged(int page, int pageCount) {
        Toast.makeText( this , "page= " + page +
                " pageCount= " + pageCount , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            default:
                break;
        }
    }
}