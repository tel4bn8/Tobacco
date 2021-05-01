package com.nss.tobacco.activitymanagement;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnDrawListener;
import com.joanzapata.pdfview.listener.OnLoadCompleteListener;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.nss.tobacco.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Sec_Pic_Biaozhun_Activity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec__pic__biaozhun);
        initView();

    }
    private void initView() {
        ImageView back = (ImageView) findViewById(R.id.back_item);
        back.setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.textView_item);
        textView.setText("消毒标准");
        ImageView iv = (ImageView) findViewById(R.id.biaozhun_detail);
        iv.setImageResource(R.drawable.xiaodubiaozhun);
       /* Bitmap bitmap = getLoacalBitmap("sdcard/Image/biaozhun.png"); //从本地取图片
        img.setImageBitmap(bitmap);
        int bmSize = 0;
        bmSize +=  bitmap.getByteCount();
        int kb = bmSize / 1024;
        int mb = kb / 1024;
        Toast.makeText(this, "bitmap size = " + mb + "MB" +  kb + "KB",
                Toast.LENGTH_LONG).show();*/
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            default:
                break;
        }
    }
}
