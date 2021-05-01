package com.nss.tobacco.activitymanagement;

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
import com.nss.tobacco.daos.Sec_HongkaoshiDao;
import com.nss.tobacco.entity.Sec_HongkaoshiEntity;
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
import java.util.List;

@ContentView(R.layout.activity_add__hongkaoshi)
public class Add_HongkaoshiActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_HongkaoshiDao sec_hongkaoshiDao = null;

    //拍照
    Context context;
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

    @ViewInject(R.id.textView)
    private TextView textView;
    @ViewInject(R.id.back_item)
    private ImageView back_img;
    @ViewInject(R.id.second_hks_add_camera)
    private ImageView ivPhoto;
    @ViewInject(R.id.second_hks_add_caremaView)
    private GridView caremaView;

    @ViewInject(R.id.second_hks_add_xingming)
    private EditText name;
    @ViewInject(R.id.second_hks_add_tel)
    private EditText tel;
    @ViewInject(R.id.second_hks_add_spinner_xiaoguo)
    private Spinner spxiaoguo;
    @ViewInject(R.id.second_hks_add_shijian_text)
    private TextView tvtime;
    @ViewInject(R.id.second_hks_add_yanjiyuan_text)
    private TextView tvyanjiyuan;
    @ViewInject(R.id.second_hks_add_beizhu_edit)
    private EditText edit_detail;
    @ViewInject(R.id.second_hks_add_btn_save)
    private Button save;
    @ViewInject(R.id.second_hks_add_btn_back)
    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        context = this;
        sec_hongkaoshiDao = new Sec_HongkaoshiDao(context);
        listPhotoNames = new ArrayList<>();

        initCarema();
        initView();
        setListener();
        setDate();
        mySpinner();
        setEditListener();
    }

    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.chengxindu, R.layout.item_myspinner);
        spxiaoguo.setAdapter(adapter1);
    }

    private void setEditListener() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = name.getSelectionStart();
                editEnd = name.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    name.setText(s);
                    name.setSelection(tempSelection1);
                }
            }
        });
        tel.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = tel.getSelectionStart();
                editEnd = tel.getSelectionEnd();
                if (temp.length() > 11) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection2 = editStart;
                    tel.setText(s);
                    tel.setSelection(tempSelection2);
                }
            }
        });
        edit_detail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = edit_detail.getSelectionStart();
                editEnd = edit_detail.getSelectionEnd();
                if (temp.length() > 50) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection3 = editStart;
                    edit_detail.setText(s);
                    edit_detail.setSelection(tempSelection3);
                }
            }
        });
    }

    private void setDate() {
        tvtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(Add_HongkaoshiActivity.this);
                dialog.setDate(tvtime);
            }
        });
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
            photoFolderAddress = CommonUtil.getSDPath() + File.separator + "hongkaoshi";
        }else{
            photoFolderAddress = getIntent().getStringExtra("folderName");
        }
    }

    private void initView() {
        textView.setText("烘烤师");
        tvyanjiyuan.setText("张大磊");
    }

    private void setListener() {
        back_img.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        save.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_item:
                myDialog();
                break;
            case R.id.second_hks_add_camera:
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
            case R.id.second_hks_add_btn_save:
                saveInfo();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.second_hks_add_btn_back:
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
        ArrayList<Sec_HongkaoshiEntity> hongkaoshiList = new ArrayList<>();
        Sec_HongkaoshiEntity mSec_HongkaoshiEntity = new Sec_HongkaoshiEntity();

        mSec_HongkaoshiEntity.setId(IdUtil.getId());
        mSec_HongkaoshiEntity.setName(name.getText().toString());
        mSec_HongkaoshiEntity.setTel(tel.getText().toString());
        mSec_HongkaoshiEntity.setXiaoguo(spxiaoguo.getSelectedItem().toString());
        mSec_HongkaoshiEntity.setCreatetime(tvtime.getText().toString());
        mSec_HongkaoshiEntity.setDetail(edit_detail.getText().toString());
        mSec_HongkaoshiEntity.setTechnician(tvyanjiyuan.getText().toString());
        mSec_HongkaoshiEntity.setState(state);
        mSec_HongkaoshiEntity.setPhotopath(photoPath.toString());

        hongkaoshiList.add(mSec_HongkaoshiEntity);
        sec_hongkaoshiDao.add(mSec_HongkaoshiEntity);
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
                    String location2 = "东经：" + LocationUtils.longitude + "北纬：" + LocationUtils.latitude + "   ";
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
