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
import android.text.TextUtils;
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
import com.nss.tobacco.daos.TurangJianceDao;
import com.nss.tobacco.entity.TurangJiance;
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

@ContentView(R.layout.activity_wg_turang_add)
public class WgTurangAddActivity extends AppCompatActivity implements View.OnClickListener{
    private TurangJianceDao turangJianceDao;

    //拍照
    Context context;
    private String location2;
    private static final int CRAEMA_REQUEST_CODE = 2; //拍照请求码
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
    private TextView textViewtitle;
    @ViewInject(R.id.back_item)
    private ImageView ivtitleBack;

    @ViewInject(R.id.frm_first_wg_turang_add)
    private ImageView ivPhoto;
    @ViewInject(R.id.frm_first_wg_turang_add_caremaView)
    private GridView caremaView;
    @ViewInject(R.id.frm_first_wg_turang_add_textview_wanggeID)
    private TextView tvwanggeID;
    @ViewInject(R.id.frm_first_wg_turang_add_spinner_yewuYear)
    private Spinner spYewuYear;
    @ViewInject(R.id.frm_first_wg_turang_add_edittext_youjizhi)
    private EditText etyoujizhi;
    @ViewInject(R.id.frm_first_wg_turang_add_edittext_PH)
    private EditText etPH;
    @ViewInject(R.id.frm_first_wg_turang_add_edittext_suxiaoN)
    private EditText etSuxiaoN;
    @ViewInject(R.id.frm_first_wg_turang_add_edittext_suxiaoP)
    private EditText etSuxiaoP;
    @ViewInject(R.id.frm_first_wg_turang_add_edittext_suxiaoK)
    private EditText etSuxiaoK;
    @ViewInject(R.id.frm_first_wg_turang_add_edittext_Cl)
    private EditText etCl;
    @ViewInject(R.id.frm_first_wg_turang_add_textview_inputTime)
    private TextView tvDengjiTime;
    @ViewInject(R.id.frm_first_wg_turang_add_textview_yanjiyuan)
    private TextView tvYanjiyuan;
    @ViewInject(R.id.frm_first_wg_turang_add_edittext_beizhu)
    private EditText etBeizhu;

    @ViewInject(R.id.frm_first_wg_turang_add_btnsave)
    private Button btnSave;
    @ViewInject(R.id.frm_first_wg_turang_add_btnback)
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        context = this;
        turangJianceDao = new TurangJianceDao(context);
        listPhotoNames = new ArrayList<>();


        initCarema();
        initView();
        initListener();
        mySpinner();
        setDate();
        setEditListener();
    }

    private void mySpinner() {
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        spYewuYear.setAdapter(adapter1);
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
            photoFolderAddress = CommonUtil.getSDPath() + File.separator + "turangjiance";
        }else{
            photoFolderAddress = getIntent().getStringExtra("folderName");
        }
    }

    private void setEditListener() {
        etyoujizhi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etyoujizhi.getSelectionStart();
                editEnd = etyoujizhi.getSelectionEnd();
                if (temp.length() > 10) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etyoujizhi.setText(s);
                    etyoujizhi.setSelection(tempSelection1);
                }
            }
        });
        etPH.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etPH.getSelectionStart();
                editEnd = etPH.getSelectionEnd();
                if (temp.length() > 10) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etPH.setText(s);
                    etPH.setSelection(tempSelection1);
                }
            }
        });
        etSuxiaoN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etSuxiaoN.getSelectionStart();
                editEnd = etSuxiaoN.getSelectionEnd();
                if (temp.length() > 10) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etSuxiaoN.setText(s);
                    etSuxiaoN.setSelection(tempSelection1);
                }
            }
        });
        etSuxiaoP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etSuxiaoP.getSelectionStart();
                editEnd = etSuxiaoP.getSelectionEnd();
                if (temp.length() > 10) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etSuxiaoP.setText(s);
                    etSuxiaoP.setSelection(tempSelection1);
                }
            }
        });
        etSuxiaoK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etSuxiaoK.getSelectionStart();
                editEnd = etSuxiaoK.getSelectionEnd();
                if (temp.length() > 10) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etSuxiaoK.setText(s);
                    etSuxiaoK.setSelection(tempSelection1);
                }
            }
        });
        etBeizhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etBeizhu.getSelectionStart();
                editEnd = etBeizhu.getSelectionEnd();
                if (temp.length() > 50) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etBeizhu.setText(s);
                    etBeizhu.setSelection(tempSelection1);
                }
            }
        });
        etCl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etCl.getSelectionStart();
                editEnd = etCl.getSelectionEnd();
                if (temp.length() > 10) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etCl.setText(s);
                    etCl.setSelection(tempSelection1);
                }
            }
        });
    }

    private void setDate() {
        String time = GetDate.lastDay();
        tvDengjiTime.setText(time);
        tvDengjiTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(WgTurangAddActivity.this);
                dialog.setDate(tvDengjiTime);
            }
        });
    }

    private void initListener() {
        ivPhoto.setOnClickListener(this);
        ivtitleBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initView() {
        textViewtitle.setText("土壤检测");
        //网格编号获取
        tvwanggeID.setText("85957");
        //烟技员名字
        tvYanjiyuan.setText("张大磊");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_item:
                    myDialog();
                break;
            case R.id.frm_first_wg_turang_add:

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
            case R.id.frm_first_wg_turang_add_btnsave:
                saveInfo();
                Toast.makeText(WgTurangAddActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.frm_first_wg_turang_add_btnback:
                    myDialog();
                break;
            default:
                break;
        }
    }

    private void saveInfo() {
        String state = "0";
        StringBuffer photopath = new StringBuffer();
        if (listPhotoNames.size() > 0){
            for (String path:listPhotoNames){
                int start = path.lastIndexOf("/");
                int end = path.lastIndexOf(".");
                photopath.append(path.substring(start+1,end)+ "");
            }
        }
        ArrayList<TurangJiance> turangJianceList = new ArrayList<>();
        TurangJiance mTurangJiance = new TurangJiance();
        mTurangJiance.setId(IdUtil.getId());
        mTurangJiance.setWanggecode(tvwanggeID.getText().toString());
        mTurangJiance.setYear(spYewuYear.getSelectedItem().toString());
        if (etyoujizhi.getText().toString() == null){
            Toast.makeText(WgTurangAddActivity.this, "数据不能留空", Toast.LENGTH_SHORT).show();
            return;
        }
        mTurangJiance.setYoujizhi(etyoujizhi.getText().toString());
        if(etPH.getText().toString() == null){
            Toast.makeText(WgTurangAddActivity.this, "数据不能留空", Toast.LENGTH_SHORT).show();
            return;
        }
        mTurangJiance.setPh(etPH.getText().toString());
        if(TextUtils.isEmpty(etSuxiaoN.getText().toString())){
            Toast.makeText(WgTurangAddActivity.this, "数据不能留空", Toast.LENGTH_SHORT).show();
            return;
        }

        mTurangJiance.setN(etSuxiaoN.getText().toString());
        if(etSuxiaoP.getText().toString() == null){
            Toast.makeText(WgTurangAddActivity.this, "数据不能留空", Toast.LENGTH_SHORT).show();
            return;
        }
        mTurangJiance.setP(etSuxiaoP.getText().toString());
        if(etSuxiaoK.getText().toString() == null){
            Toast.makeText(WgTurangAddActivity.this, "数据不能留空", Toast.LENGTH_SHORT).show();
            return;
        }
        mTurangJiance.setK(etSuxiaoK.getText().toString());
        if(etCl.getText().toString() == null){
            Toast.makeText(WgTurangAddActivity.this, "数据不能留空", Toast.LENGTH_SHORT).show();
            return;
        }
        mTurangJiance.setCl(etCl.getText().toString());
        mTurangJiance.setPicktime(tvDengjiTime.getText().toString());
        mTurangJiance.setTechnician(tvYanjiyuan.getText().toString());
        mTurangJiance.setDetail(etBeizhu.getText().toString());
        mTurangJiance.setState(state);
        mTurangJiance.setPhotopath(photopath.toString());

        turangJianceList.add(mTurangJiance);
        turangJianceDao.add(mTurangJiance);
    }

    //返回按钮
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
        }).show();//显示此对话框
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