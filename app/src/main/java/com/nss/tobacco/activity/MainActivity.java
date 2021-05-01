package com.nss.tobacco.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.MyFragmentPagerAdapter;
import com.nss.tobacco.utils.PermissionHelper;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        ViewPager.OnPageChangeListener{
    private PermissionHelper mPermissionHelper;
    private String realname;
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButtonl;
    private RadioButton mRadioButton2;
    private RadioButton mRadioButton3;
    private RadioButton mRadioButton4;
    private ViewPager vpager;

    private MyFragmentPagerAdapter mAdapter;
    private static Boolean isExit = false;
    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;
    private RadioButton[] brs = new RadioButton[4];
    private Drawable[] drawables;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPermissionHelper = new PermissionHelper(this);
        mPermissionHelper.checkPermisson(new PermissionHelper.OnPermissionListener() {
            @Override
            public void onAgreePermission() {
                Toast.makeText(MainActivity.this, "同意权限了", Toast.LENGTH_SHORT).show();
                // do something
            }

            @Override
            public void onDeniedPermission(){
                Toast.makeText(MainActivity.this, "拒绝权限了", Toast.LENGTH_SHORT).show();
                finish(); // 当界面一定通过权限才能继续，就要加上这行
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        initView();
        initButton();
    }

    private void initView() {
        mRadioGroup = (RadioGroup) findViewById(R.id.tab_menu);
        mRadioButtonl = (RadioButton) findViewById(R.id.rb_chanqianzhunbei);
        mRadioButton2 = (RadioButton) findViewById(R.id.rb_shengchanguanli);
        mRadioButton3 = (RadioButton) findViewById(R.id.rb_zonghexinxi);
        mRadioButton4 = (RadioButton) findViewById(R.id.rb_setting);
        mRadioGroup.setOnCheckedChangeListener(this);
        vpager = (ViewPager) findViewById(R.id.vpager);
        vpager.setAdapter(mAdapter);
        vpager.setCurrentItem(0);
        vpager.addOnPageChangeListener(this);
    }

    /**
     * 设置底部按钮
     */
    public void initButton(){
        brs[0] = (RadioButton) findViewById(R.id.rb_chanqianzhunbei);
        brs[1] = (RadioButton) findViewById(R.id.rb_shengchanguanli);
        brs[2] = (RadioButton) findViewById(R.id.rb_zonghexinxi);
        brs[3] = (RadioButton) findViewById(R.id.rb_setting);
        for (int i = 0; i < brs.length; i++) {//循环
            drawables = brs[i].getCompoundDrawables();//通过RadioButton的getCompoundDrawables()方法，拿到图片的drawables,分别是左上右下的图片
            drawables[1].setBounds(0, 0, getResources().getDimensionPixelSize(R.dimen.x100),
                    getResources().getDimensionPixelSize(R.dimen.x100));
            brs[i].setCompoundDrawables(drawables[0], drawables[1], drawables[2],
                    drawables[3]);//将改变了属性的drawable再重新设置回去
        }
       brs[0].setChecked(true);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }
    @Override
    public void onPageSelected(int position) {
    }
    @Override
    public void onPageScrollStateChanged(int state){
        //state的状态有三个，0表示什么都没做，1正在滑动，2滑动完毕
        if (state == 2) {
            switch (vpager.getCurrentItem()) {
                case PAGE_ONE:
                    mRadioButtonl.setChecked(true);
                    break;
                case PAGE_TWO:
                    mRadioButton2.setChecked(true);
                    break;
                case PAGE_THREE:
                    mRadioButton3.setChecked(true);
                    break;
                case PAGE_FOUR:
                    mRadioButton4.setChecked(true);
                    break;
            }
        }
    }
    //点击选择页面
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_chanqianzhunbei:
                vpager.setCurrentItem(PAGE_ONE);
                break;
            case R.id.rb_shengchanguanli:
                vpager.setCurrentItem(PAGE_TWO);
                break;
            case R.id.rb_zonghexinxi:
                vpager.setCurrentItem(PAGE_THREE);
                break;
            case R.id.rb_setting:
                vpager.setCurrentItem(PAGE_FOUR);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    private void exitBy2Click() {
        Timer tExit  = null;

        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出烟叶生产标准化系统", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPermissionHelper.onResume(); // 当界面一定通过权限才能继续，就要加上这行
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void getRealName(){
        Intent intent = getIntent();
        realname = intent.getStringExtra("realname");
    }
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
