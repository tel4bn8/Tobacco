package com.nss.tobacco.activitypreparation;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.nss.tobacco.daos.WuziFafangDao;
import com.nss.tobacco.entity.WuziFafang;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.Constants;
import com.nss.tobacco.utils.DateDialog;
import com.nss.tobacco.utils.IdUtil;
import com.nss.tobacco.utils.LocationUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_wz_wuzi_ff_add)
public class WzWuziFfAddActivity extends AppCompatActivity implements View.OnClickListener{
    private WuziFafangDao wuziFafangDao = null;

    //拍照
    Context context;
    private String location2;
    private static final int CRAEMA_REQUEST_CODE = 0; //拍照请求码
    private boolean candelete = false; //是否可以删除照片
    private String defaultPhotoAddress = null; //拍照生成默认照片的绝对路径
    private String photoFolderAddress = null; //存放照片的文件夹
    private List<String> listPhotoNames = null;
    private CaremaAdapter cadapter = null;
    private int screenWidth = 0; //屏幕宽度

    private CharSequence temp;
    private int editStart;
    private int editEnd;

    @ViewInject(R.id.back_item)
    private ImageView ivback;
    @ViewInject(R.id.textView)
    private TextView tvtitle;
    @ViewInject(R.id.first_wz_wuzi_ff_add)
    private ImageView ivPhoto;
    @ViewInject(R.id.first_wz_wuzi_ff_add_caremaView)
    private GridView caremaView;
    @ViewInject(R.id.first_wz_wuzi_ff_add_edittext_name)
    private EditText etname;
    @ViewInject(R.id.first_wz_wuzi_ff_add_spinner_farmer)
    private Spinner spFarmer;
    @ViewInject(R.id.first_wz_wuzi_ff_add_spinner_danwei)
    private Spinner spdanwei;
    @ViewInject(R.id.first_wz_wuzi_ff_add_edittext_num)
    private EditText etnum;
    @ViewInject(R.id.first_wz_wuzi_ff_add_edittext_money)
    private EditText etmoney;
    @ViewInject(R.id.first_wz_wuzi_ff_add_textview_inputTime)
    private TextView tvtime;
    @ViewInject(R.id.first_wz_wuzi_ff_add_edittext_beizhu)
    private EditText etbeizhu;

    @ViewInject(R.id.first_wz_wuzi_ff_add_btnsave)
    private Button btnsave;
    @ViewInject(R.id.first_wz_wuzi_ff_add_btnback)
    private Button btnback;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        x.view().inject(this);
        context = this;
        wuziFafangDao = new WuziFafangDao(context);
        listPhotoNames = new ArrayList<>();

        initCarema();
        initView();
        initListener();
        setDate();
        mySpinner();
        setEditListener();

    }

    private void setEditListener() {
        etname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etname.getSelectionStart();
                editEnd = etname.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etname.setText(s);
                    etname.setSelection(tempSelection1);
                }
            }
        });
        etnum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etnum.getSelectionStart();
                editEnd = etnum.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection2 = editStart;
                    etnum.setText(s);
                    etnum.setSelection(tempSelection2);
                }
            }
        });
        etbeizhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etbeizhu.getSelectionStart();
                editEnd = etbeizhu.getSelectionEnd();
                if (temp.length() > 50) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection3 = editStart;
                    etbeizhu.setText(s);
                    etbeizhu.setSelection(tempSelection3);
                }
            }
        });
        etmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etmoney.getSelectionStart();
                editEnd = etmoney.getSelectionEnd();
                if (temp.length() > 50) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection4 = editStart;
                    etmoney.setText(s);
                    etmoney.setSelection(tempSelection4);
                }
            }
        });
    }

    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.yannongName, R.layout.item_myspinner);
        spFarmer.setAdapter(adapter1);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.danwei, R.layout.item_myspinner);
        spdanwei.setAdapter(adapter2);
    }

    private void setDate() {
        tvtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(WzWuziFfAddActivity.this);
                dialog.setDate(tvtime);
            }
        });
    }

    private void initListener() {
        ivPhoto.setOnClickListener(this);
        ivback.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnback.setOnClickListener(this);
    }

    private void initView() {
        tvtitle.setText("物资发放");
    }

    private void initCarema() {
        //default photo address
        defaultPhotoAddress = CommonUtil.getSDPath() + File.separator + "default.jpg";
        //获取屏幕的分辨率
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        screenWidth = dm.widthPixels;
        //是否可删除照片
        candelete = getIntent().getBooleanExtra("candelete", true);
        //获取文件夹名称
        if(getIntent().getStringExtra("folderName") == null){
            photoFolderAddress = CommonUtil.getSDPath() + File.separator + "wuzifafang";
        }else{
            photoFolderAddress = getIntent().getStringExtra("folderName");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_item:
                myDialog();
                break;
            case R.id.first_wz_wuzi_ff_add:

                //验证sd卡是否可用
                if(CommonUtil.getSDPath() == null){
                    Toast.makeText(this, "请安装SD卡", Toast.LENGTH_SHORT).show();
                    return;
                }
                //验证是否超出限制照片数量
                if(listPhotoNames != null && listPhotoNames.size() >= Constants.MAX_PHOTO_SIZE){
                    Toast.makeText(context, "最多只允许拍摄" + Constants.MAX_PHOTO_SIZE + "张照片。", Toast.LENGTH_SHORT).show();
                    return;
                } //调用系统拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(defaultPhotoAddress)));
                startActivityForResult(intent, CRAEMA_REQUEST_CODE);

                break;
            case R.id.first_wz_wuzi_ff_add_btnsave:

                saveInfo();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.first_wz_wuzi_ff_add_btnback:
                myDialog();
                break;
            default:
                break;
        }
    }

    private void saveInfo() {
        String state = "0";
        StringBuffer photoPath=new StringBuffer();
        if(listPhotoNames.size()>0){
            for(String path:listPhotoNames){
                int start= path.lastIndexOf("/");
                int end= path.lastIndexOf(".");
                photoPath.append(path.substring(start+1,end)+" ");
            }
        }
        ArrayList<WuziFafang> wuziList = new ArrayList<>();
        WuziFafang mWuziFafang = new WuziFafang();

        mWuziFafang.setId(IdUtil.getId());
        mWuziFafang.setFarmer(spFarmer.getSelectedItem().toString());
        mWuziFafang.setName(etname.getText().toString());
        mWuziFafang.setNum(etnum.getText().toString());
        mWuziFafang.setDanwei(spdanwei.getSelectedItem().toString());
        mWuziFafang.setMoney(etmoney.getText().toString());
        mWuziFafang.setCreatetime(tvtime.getText().toString());
        mWuziFafang.setDetail(etbeizhu.getText().toString());
        mWuziFafang.setState(state);
        mWuziFafang.setPhotopath(photoPath.toString());

        wuziList.add(mWuziFafang);
        wuziFafangDao.add(mWuziFafang);
    }
    //返回按钮
    private void myDialog() {
        new AlertDialog.Builder(this).setTitle("系统提示")//设置对话框标题
                .setMessage("未保存，确认退出吗？")//设置显示的内容·
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
        }).show();//显示此对话框
        //基础设施
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            switch (requestCode) {
                //拍照
                case CRAEMA_REQUEST_CODE:
                    //文件夹目录是否存在
                    File folderAddr = new File(photoFolderAddress);
                    if(!folderAddr.exists() || !folderAddr.isDirectory()){
                        folderAddr.mkdirs();
                    }
                    //将原图片压缩拷贝到指定目录
                    String targetPath = photoFolderAddress + File.separator + CommonUtil.getUUID32() + ".jpg";
                    location2 = "东经：" + LocationUtils.longitude + "北纬：" + LocationUtils.latitude + "   ";
                    CommonUtil.dealImage(defaultPhotoAddress, targetPath, location2);
                    //删除原图
                    new File(defaultPhotoAddress).delete();
                    //保存照片的绝对路径
                    if(listPhotoNames == null){
                        listPhotoNames = new ArrayList<String>();
                    }
                    listPhotoNames.add(targetPath);

                    if(cadapter == null){
                        cadapter = new CaremaAdapter(context, screenWidth, listPhotoNames, candelete);
                        caremaView.setAdapter(cadapter);
                    }else{
                        cadapter.notifyDataSetChanged();
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
