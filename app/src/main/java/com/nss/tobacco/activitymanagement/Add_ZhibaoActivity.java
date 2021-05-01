package com.nss.tobacco.activitymanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.nss.tobacco.daos.Sec_XiaoduInfoDao;
import com.nss.tobacco.daos.Sec_ZhibaoDao;
import com.nss.tobacco.daos.YannongDao;
import com.nss.tobacco.entity.Sec_XiaoduInfoEntity;
import com.nss.tobacco.entity.Sec_ZhibaoEntity;
import com.nss.tobacco.entity.Yannong;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.Constants;
import com.nss.tobacco.utils.DateDialog;
import com.nss.tobacco.utils.GetDate;
import com.nss.tobacco.utils.IdUtil;
import com.nss.tobacco.utils.LocationUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.nss.tobacco.R.id.yaoming;

@ContentView(R.layout.activity_add__zhibao)
public class Add_ZhibaoActivity extends AppCompatActivity implements View.OnClickListener{
    private CharSequence temp;
    private int editStart;
    private int editEnd;
    private Map<String,Object> map;

    Context mContext;

    private static final int CAREMA_REQUEST_CODE = 1;//拍照请求码

    private String defaultPhotoPath = null;//拍照生成图片的默认绝对路径
    private String photoFolderPath = null;//存放照片的文件夹
    private int screenWidth = 0;//屏幕宽度
    private String location2 = null;//图片上的径纬度信息
    private boolean mCandelete = false; //图片是否可删除
    private List<String> mListPhotos = null;//存放拍摄图片路径
    private CaremaAdapter mCaremaAdapter = null;//存放图片的GridView的适配器
    private Sec_ZhibaoDao mDao;

    @ViewInject(R.id.textView)
    TextView textView;
    @ViewInject(R.id.back_item)
    ImageView back_img;

    @ViewInject(R.id.second_tjgl_zhibao_add_photo)
    ImageView take_photo;

    @ViewInject(R.id.spinner_zhongzhihu)
    Spinner name;
    @ViewInject(R.id.zhibao_text)
    TextView dateText1;
    @ViewInject(R.id.spinner_bingchongleixing)
    Spinner bingchong;
    @ViewInject(R.id.spinner_yaopinming)
    Spinner yaopinming;
    @ViewInject(R.id.nongdu_edit)
    EditText nongdu;
    @ViewInject(R.id.biaozhun_pic)
    ImageView biaozhun;
    @ViewInject(R.id.beizhu_edit)
    EditText beizhu;
    @ViewInject(R.id.btn_save)
    Button save;
    @ViewInject(R.id.btn_back)
    Button back_btn;

    @ViewInject(R.id.second_tjgl_zhibao_add_gridView)
    GridView gvGridView;
    private Sec_ZhibaoEntity mInfoEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mContext = this;
        mDao = new Sec_ZhibaoDao(mContext);
        mListPhotos = new ArrayList<>();
        initView();
        setDate();

        //初始化相机
        initCareme();
        mySpinner();
        setListener();


        map=new HashMap<>();
        initView();
        setDate();
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
            photoFolderPath = CommonUtil.getSDPath() + File.separator + "ZhiBao";
        } else {
            photoFolderPath = getIntent().getStringExtra("folderName");
        }
    }
    private void initView() {
        textView.setText("植保");
    }

    private void setListener() {
        back_img.setOnClickListener(this);
        take_photo.setOnClickListener(this);
        save.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }
    private void setDate() {
        //时间选择
        dateText1.setText(GetDate.lastDay());
        dateText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_ZhibaoActivity.this);
                dialog.setDate(dateText1);
            }
        });
    }
    //自定义spinner
    private void mySpinner() {
        YannongDao mYanNongDao=new YannongDao(this);
        ArrayList<Yannong> yumiaohus = mYanNongDao.searchAllData();
        List<String> yannong=new ArrayList<>();
        for(Yannong info:yumiaohus){
            yannong.add(info.getName());
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.item_myspinner,yannong);
        name.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.bingchongleixing, R.layout.item_myspinner);
        bingchong.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.age, R.layout.item_myspinner);
        yaopinming.setAdapter(adapter3);
    }
    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent;
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            case R.id.second_tjgl_zhibao_add_photo:
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
    //保存录入信息到本地数据库
    private void saveInfo() {
        String str = "0";
        String state = str;
        StringBuffer photoPath = new StringBuffer();
        if (mListPhotos.size() > 0) {
            for (String path : mListPhotos) {
                int start = path.lastIndexOf("/");
                int end = path.lastIndexOf(".");
                photoPath.append(path.substring(start + 1, end) + " ");

            }
        }
        //创建消毒表 保存信息

        Sec_ZhibaoEntity mInfoEntity =  new Sec_ZhibaoEntity();
        String id = IdUtil.getId();
        String farmerContent = name.getSelectedItem().toString();

        String timeContent = dateText1.getText().toString();
        String bingChongTypeContent = bingchong.getSelectedItem().toString();
        String yaoPinContent = yaopinming.getSelectedItem().toString();
        String nongduContent = nongdu.getText().toString();
        String beizhuContent = beizhu.getText().toString();

        mInfoEntity.setId(IdUtil.getId());
        mInfoEntity.setFarmer(name.getSelectedItem().toString());
        mInfoEntity.setCreatetime(dateText1.getText().toString());
        mInfoEntity.setType(bingchong.getSelectedItem().toString());
        mInfoEntity.setYaominag(yaopinming.getSelectedItem().toString());
        mInfoEntity.setNongdu(nongdu.getText().toString());
        mInfoEntity.setDetail(beizhu.getText().toString());
        mInfoEntity.setState(state);
        mInfoEntity.setPhotopath(photoPath.toString());
        mDao.add(mInfoEntity);
        Toast.makeText(this, "已经保存成功", Toast.LENGTH_LONG).show();
    }
    //返回按钮提醒框
    private void myDialog() {
        new AlertDialog.Builder(this).setTitle("系统提示")//设置对话框标题
                .setMessage("未保存，确认退出吗？")//设置显示的内容
                .setPositiveButton("确定",new DialogInterface.OnClickListener() {//添加确定按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                        // TODO Auto-generated method stub
                        finish();
                    }
                }).setNegativeButton("返回",new DialogInterface.OnClickListener() {//添加返回按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {//响应事件
                // TODO Auto-generated method stub
                Log.i("alertdialog"," 请保存数据！");
            }
        }).show();//在按键响应事件中显示此对话框
    }
    @Override
    public void onBackPressed() {
        myDialog();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAREMA_REQUEST_CODE:

                    File folderAddr = new File(photoFolderPath);
                    //判断文件夹是否存在
                    if (!folderAddr.exists() || !folderAddr.isDirectory()) {
                        folderAddr.mkdirs();
                    }
                    //将图片压缩到指定目录
                    String targetPath = photoFolderPath+File.separator+ CommonUtil.getUUID32() + ".jpg";
                    location2 = "东经: " + LocationUtils.longitude + "北纬: " + LocationUtils.latitude + "  ";
                    boolean dealFlag= CommonUtil.dealImage(defaultPhotoPath, targetPath, location2);
                    if (dealFlag) {
                        //删除拍摄原图
                        File fileDefault = new File(defaultPhotoPath);
                        fileDefault.delete();
                        //保存拍摄图片指定路径
                        mListPhotos.add(targetPath);
                        if (mCaremaAdapter == null) {
                            mCaremaAdapter = new CaremaAdapter(mContext, screenWidth, mListPhotos, mCandelete);
                            gvGridView.setAdapter(mCaremaAdapter);
                        } else {
                            mCaremaAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }

    }
}
