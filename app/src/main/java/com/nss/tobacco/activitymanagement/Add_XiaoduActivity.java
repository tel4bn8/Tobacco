package com.nss.tobacco.activitymanagement;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nss.tobacco.R;
import com.nss.tobacco.adapter.CaremaAdapter;
import com.nss.tobacco.adapter.MyAdapter;
import com.nss.tobacco.daos.DBManager;
import com.nss.tobacco.daos.Sec_MiaoChuangZhuiFeiDao;
import com.nss.tobacco.daos.Sec_XiaoduInfoDao;
import com.nss.tobacco.daos.YumiaohuDao;
import com.nss.tobacco.entity.FarmerEntity;
import com.nss.tobacco.entity.Sec_XiaoduInfoEntity;
import com.nss.tobacco.entity.Yumiaohu;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.Constants;
import com.nss.tobacco.utils.DateDialog;
import com.nss.tobacco.utils.GetDate;
import com.nss.tobacco.utils.IdUtil;
import com.nss.tobacco.utils.IdcardInfoExtractor;
import com.nss.tobacco.utils.LocationUtils;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FilenameFilter;
import java.net.IDN;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@ContentView(R.layout.activity_add__xiaodu)
public class Add_XiaoduActivity extends AppCompatActivity implements View.OnClickListener {

    private String famer;
    private String createtime;
    private String yaoming;
    private String sheshi;
    private String detail;
    private String state;
    private DBManager dbManager;
    private String imagePath;

    Context mContext;

    private static final int CAREMA_REQUEST_CODE = 1;//拍照请求码

    private String defaultPhotoPath = null;//拍照生成图片的默认绝对路径
    private String photoFolderPath = null;//存放照片的文件夹
    private int screenWidth = 0;//屏幕宽度
    private String location2 = null;//图片上的径纬度信息
    private boolean mCandelete = false; //图片是否可删除
    private List<String> mListPhotos = null;//存放拍摄图片路径
    private CaremaAdapter mCaremaAdapter = null;//存放图片的GridView的适配器

    @ViewInject(R.id.textView)
    TextView textView;
    @ViewInject(R.id.back_item)
    ImageView back_img;
    @ViewInject(R.id.frm_first_yn_ynda_add)
    ImageView ivPhoto;
    @ViewInject(R.id.caremaView)
    GridView caremaView;

    @ViewInject(R.id.spinner_zhongzhihu)
    Spinner sp_farmer;
    @ViewInject(R.id.shijian_text)
    TextView text_createtime;
    @ViewInject(R.id.spinner_yaoming)
    Spinner sp_yaoming;
    @ViewInject(R.id.spinner_sheshi)
    Spinner sp_sheshi;
    @ViewInject(R.id.biaozhun_pic)
    ImageView biaozhun;
    @ViewInject(R.id.beizhu_edit)
    EditText edit_detail;
    @ViewInject(R.id.btn_save)
    Button save;
    @ViewInject(R.id.btn_back)
    Button back_btn;

    private Sec_XiaoduInfoDao mDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);

        mContext = this;
        mDao = new Sec_XiaoduInfoDao(mContext);
        mListPhotos = new ArrayList<>();
        initView();
        setDate();

        //初始化相机
        initCareme();
        mySpinner();
        setListener();
    }


    private void initCareme() {
        defaultPhotoPath = CommonUtil.getSDPath() + File.separator + "default.jpg";
        //获取屏幕分辨率
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        screenWidth = dm.widthPixels;
        //是否可删除图片
        mCandelete = getIntent().getBooleanExtra("candelete", true);
        if (getIntent().getStringExtra("folderName") == null) {
            photoFolderPath = CommonUtil.getSDPath() + File.separator + "XiaoDu";
        } else {
            photoFolderPath = getIntent().getStringExtra("folderName");
        }
    }

    private void initView() {
        textView.setText("消毒");
        biaozhun.setImageResource(R.drawable.xiaodubiaozhun);
    }

    private void setListener() {
        back_img.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        save.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        biaozhun.setOnClickListener(this);
    }

    private void setDate() {
        //时间选择
        text_createtime.setText(GetDate.lastDay());
        text_createtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_XiaoduActivity.this);
                dialog.setDate(text_createtime);
            }
        });
    }

    //自定义spinner
    private void mySpinner() {
        YumiaohuDao mYuMiaoDao=new YumiaohuDao(this);
        ArrayList<Yumiaohu> yumiaohus = mYuMiaoDao.searchAllData();
        List<String> yumiaohu=new ArrayList<>();
        for(Yumiaohu info:yumiaohus){
            yumiaohu.add(info.getName());
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.item_myspinner,yumiaohu);
        sp_farmer.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.age, R.layout.item_myspinner);
        sp_yaoming.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.age, R.layout.item_myspinner);
        sp_sheshi.setAdapter(adapter3);
    }

    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                myDialog();
                break;
            case R.id.frm_first_yn_ynda_add:
                //判断是否安装SD卡
                if (CommonUtil.getSDPath() == null) {
                    Toast.makeText(this, "请安装SD卡", Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断图片个数是否超过最大
                if (mListPhotos != null && mListPhotos.size() >= Constants.MAX_PHOTO_SIZE) {
                    Toast.makeText(this, "最多允许拍摄" + Constants.MAX_PHOTO_SIZE, Toast.LENGTH_SHORT).show();
                    return;
                }
                //调用系统拍照
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(defaultPhotoPath)));
                startActivityForResult(intent, CAREMA_REQUEST_CODE);
                break;

            case R.id.biaozhun_pic:
                //downloadPic();
                intent.setClass(this, Sec_Pic_Biaozhun_Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_save:
                saveInfo();
                finish();
                break;
            case R.id.btn_back:
                myDialog();
                break;
            default:
                break;
        }
    }

    //下载图片
    private void downloadPic() {
        //文件下载地址
        String url = "http://192.168.1.103:8080/file/201611/1.png";
        //文件保存在本地的路径
        File file = new File("/sdcard/Image/");
        file.mkdirs();// 创建文件夹
        String path = "sdcard/Image/" + "biaozhun.png";
        XUtil.DownLoadFile(url, path, new MyCallBack<File>() {
            @Override
            public void onSuccess(File result) {
                super.onSuccess(result);
                Log.i("result", "--------xxxxxx" + result + "成功");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
                Log.i("result", "--------xxxxxx" + "失败" + ex);
            }
        });
    }

    //保存录入信息到本地数据库
    private void saveInfo() {
        famer = sp_farmer.getSelectedItem().toString();
        createtime = text_createtime.getText().toString();
        yaoming = sp_yaoming.getSelectedItem().toString();
        sheshi = sp_sheshi.getSelectedItem().toString();
        detail = edit_detail.getText().toString();
        String str = "0";
        state = str;
        StringBuffer photoPath = new StringBuffer();
        if (mListPhotos.size() > 0) {
            for (String path : mListPhotos) {
                int start = path.lastIndexOf("/");
                int end = path.lastIndexOf(".");
                photoPath.append(path.substring(start + 1, end) + " ");

            }
        }
        //创建消毒表 保存信息

        Sec_XiaoduInfoEntity mXidoduInfoEntity = new Sec_XiaoduInfoEntity();
        mXidoduInfoEntity.setId(IdUtil.getId());
        mXidoduInfoEntity.setFarmer(famer);
        mXidoduInfoEntity.setCreatetime(createtime);
        mXidoduInfoEntity.setYaopin(yaoming);
        mXidoduInfoEntity.setSheshi(sheshi);
        mXidoduInfoEntity.setDetail(detail);
        mXidoduInfoEntity.setState(state);
        mXidoduInfoEntity.setPhotopath(photoPath.toString());
        mDao.add(mXidoduInfoEntity);
        Toast.makeText(this, "已经保存成功", Toast.LENGTH_LONG).show();
    }

    //返回按钮提醒框
    private void myDialog() {
        new AlertDialog.Builder(this).setTitle("系统提示")//设置对话框标题
                .setMessage("未保存，确认退出吗？")//设置显示的内容
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        finish();
                    }
                }).setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                // TODO Auto-generated method stub
                Log.i("alertdialog", " 请保存数据！");
            }
        }).show();//在按键响应事件中显示此对话框
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                //拍照

                case CAREMA_REQUEST_CODE:

                    File folderAddr = new File(photoFolderPath);
                    //判断文件夹是否存在
                    if (!folderAddr.exists() || !folderAddr.isDirectory()) {
                        folderAddr.mkdirs();
                    }
                    //将图片压缩到指定目录
                    String targetPath = photoFolderPath + File.separator + CommonUtil.getUUID32() + ".jpg";
                    location2 = "东经: " + LocationUtils.longitude + "北纬: " + LocationUtils.latitude + "  ";
                    boolean dealFlag = CommonUtil.dealImage(defaultPhotoPath, targetPath, location2);
                    if (dealFlag){
                        //删除拍摄原图
                        File fileDefault = new File(defaultPhotoPath);
                        fileDefault.delete();
                        //保存拍摄图片指定路径
                        mListPhotos.add(targetPath);
                        if (mCaremaAdapter == null) {
                            mCaremaAdapter = new CaremaAdapter(mContext, screenWidth, mListPhotos, mCandelete);
                            caremaView.setAdapter(mCaremaAdapter);
                        } else {
                            mCaremaAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    }
    @Override
    public void onBackPressed() {
        myDialog();
    }
}
