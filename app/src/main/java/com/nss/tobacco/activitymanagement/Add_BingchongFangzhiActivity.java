package com.nss.tobacco.activitymanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.nss.tobacco.daos.Sec_BingchongFzDao;
import com.nss.tobacco.daos.Sec_MiaoChuangZhuiFeiDao;
import com.nss.tobacco.daos.YumiaohuDao;
import com.nss.tobacco.entity.Sec_BingchongFzEntity;
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
import com.nss.tobacco.entity.Yumiaohu;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.Constants;
import com.nss.tobacco.utils.DateDialog;
import com.nss.tobacco.utils.GetDate;
import com.nss.tobacco.utils.IdUtil;
import com.nss.tobacco.utils.LocationUtils;
import com.nss.tobacco.utils.MyCallBack;
import com.nss.tobacco.utils.PhotoUtil;
import com.nss.tobacco.utils.XUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_add__bingchonghai)
public class Add_BingchongFangzhiActivity extends AppCompatActivity implements View.OnClickListener{
    private CharSequence temp;
    private int editStart;
    private int editEnd;
    private Map<String, Object> map;

    Context mContext;
    private Sec_BingchongFzDao mBingchongFzDao = null;
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

    @ViewInject(R.id.second_ym_bcfz_imageview)
    ImageView imageViewPhoto;
    @ViewInject(R.id.second_ym_bcfz_gridView)
    GridView  photoGridView;//拍摄图片的位置


    @ViewInject(R.id.second_ym_bcfz_farmer)
    Spinner farmer;//种植户
    @ViewInject(R.id.second_ym_bcfz_yongyaomingcheng)
    EditText yaoming;//用药名称
    @ViewInject(R.id.second_ym_bcfz_fangzhifangshi)
    EditText fangzhifangshi;//防止方式

    @ViewInject(R.id.second_ym_bcfz_nongdu)
    EditText nongdu;//浓度
    @ViewInject(R.id.second_ym_bcfz_bingchonghaileixing)
    Spinner leixing;//病虫害类型

    @ViewInject(R.id.second_ym_bcfz_fabinglv)
    EditText fabinglv;//发病率

    @ViewInject(R.id.second_ym_bcfz_area)
    EditText mianji;//发病面积

    @ViewInject(R.id.second_ym_bcfz_yuguliang)
    EditText yugu;//预估损失量

    @ViewInject(R.id.second_ym_bcfz_fangzhiTime)
    TextView shijian;//防止时间

    @ViewInject(R.id.biaozhun_pic)
    ImageView biaozhun;//参考标准

    @ViewInject(R.id.second_ym_bcfz_detail)
    EditText beizhu;//备注

    @ViewInject(R.id.second_ym_bcfz_save)
    Button save;
    @ViewInject(R.id.second_ym_bcfz_back)
    Button back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        map = new HashMap<>();
        mContext = this;
        mBingchongFzDao = new Sec_BingchongFzDao(mContext);
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
            photoFolderPath = CommonUtil.getSDPath() + File.separator + "BingChongHaiFangZhi";
        } else {
            photoFolderPath = getIntent().getStringExtra("folderName");
        }
    }


    private void initView() {
        textView.setText("病虫防治");
    }

    private void setListener() {
        back_img.setOnClickListener(this);
        imageViewPhoto.setOnClickListener(this);
        save.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        getMianjiEditListener();
        setDate();
    }

    private void setDate() {

        //时间选择
        shijian.setText(GetDate.lastDay());
        shijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_BingchongFangzhiActivity.this);
                dialog.setDate(shijian);
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
        farmer.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.bingchongleixing, R.layout.item_myspinner);
        leixing.setAdapter(adapter2);
    }
  //onClick事件
    @Override
    public void onClick(View v) {
        //添加点击事件
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                myDialog();
                break;
            //调用系统拍照
            case R.id.second_ym_bcfz_imageview:
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
            case R.id.second_ym_bcfz_save:
                //录入信息保存到数据库
                saveInfo();
                finish();
                break;
            case R.id.second_ym_bcfz_back:
                myDialog();
                break;
            default:
                break;

        }
    }
    //保存数据到本地数据库
    private void saveInfo() {
        //state代表是否上传 0代表未上传,1代表已上传
        String state = "0";
        StringBuffer photoPath=new StringBuffer();
        if(mListPhotos.size()>0){
            for(String path:mListPhotos){
                int start= path.lastIndexOf("/");
                int end= path.lastIndexOf(".");
                photoPath.append(path.substring(start+1,end)+" ");

            }
        }
        List<Sec_BingchongFzEntity> list = new ArrayList<>();
        Sec_BingchongFzEntity info = new Sec_BingchongFzEntity();
        info.setId(IdUtil.getId());
        info.setFarmer(farmer.getSelectedItem().toString());
        info.setYaopin(yaoming.getText().toString());
        info.setWay(fangzhifangshi.getText().toString());
        info.setNongdu(nongdu.getText().toString());
        info.setType(leixing.getSelectedItem().toString());

        info.setRate(fabinglv.getText().toString());
        info.setArea(mianji.getText().toString());
        info.setCreatetime(shijian.getText().toString());
        info.setDetail(beizhu.getText().toString());
        info.setLoss(yugu.getText().toString());

        info.setState(state);
        info.setPhotopath(photoPath.toString());
        list.add(info);
        mBingchongFzDao.add(info);
        Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
    }
    private void post() {
        String url="http://192.168.0.108:8080/tobacco-app/yannong/queryAllYannongBytech";
        Map<String,Object> map=new HashMap<>();
        map.put("username", "admin");
        map.put("password", "123");
        XUtil.Post(url, map, new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Log.e("result", result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
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
                            photoGridView.setAdapter(mCaremaAdapter);
                        } else {
                            mCaremaAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    }

    private void uploadfile() {
        //图片上传地址
        String url="http://192.168.0.108:8080/tobacco-app/yantianguanli/addBingchongfangzhi";
        //传入自己的相应参数map
        XUtil.UpLoadFile(url, map, new MyCallBack<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
    }
    public void getMianjiEditListener(){
        mianji.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {}
        @Override
        public void afterTextChanged(Editable s) {
            map.put("area",s);
            editStart = mianji.getSelectionStart();
            editEnd = mianji.getSelectionEnd();
            if (temp.length() > 10) {//限制长度
                Toast.makeText(Add_BingchongFangzhiActivity.this,
                        "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                        .show();
                s.delete(editStart - 1, editEnd);
                int tempSelection = editStart;
                mianji.setText(s);
                mianji.setSelection(tempSelection);
            }
        }
    });
    }
    //返回按钮提醒框
    private void myDialog() {
        new AlertDialog.Builder(Add_BingchongFangzhiActivity.this).setTitle("系统提示")//设置对话框标题
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
   /* //从网络获取数据
    private void getData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String json = "http://192.168.0.108:8080/tobacco-app/yannong/queryAllYannongBytech";
                String jsonString2 = HttpUtils.getJsonContent(json);//从网络获取数据
                List<ZhongzhihuInfoBean> list = GsonTools.getPersons(jsonString2,ZhongzhihuInfoBean.class);//解析json数据
                Log.i("Tag", "-------------"+list.toString());

            }
        });
        thread.start();

    }*/
   /* @SuppressLint("SdCardPath")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

            FileOutputStream b = null;
            File file = new File("/sdcard/Image/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/Image/"+name;
            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try
            {
                photo1.setImageBitmap(bitmap);// 将图片显示在ImageView里
            }catch(Exception e)
            {
                Log.e("error", e.getMessage());
            }

        }
    }*/

    @Override
    public void onBackPressed() {
        myDialog();
    }
}
