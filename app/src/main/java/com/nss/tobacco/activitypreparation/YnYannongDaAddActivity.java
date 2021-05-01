package com.nss.tobacco.activitypreparation;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
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
import com.nss.tobacco.daos.YannongDao;
import com.nss.tobacco.entity.Yannong;
import com.nss.tobacco.utils.CommonUtil;
import com.nss.tobacco.utils.Constants;
import com.nss.tobacco.utils.DateDialog;
import com.nss.tobacco.utils.GetDate;
import com.nss.tobacco.utils.IdUtil;
import com.nss.tobacco.utils.IdcardInfoExtractor;
import com.nss.tobacco.utils.LocationUtils;
import com.nss.tobacco.utils.PermissionHelper;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_yn_yannong_da_add)
public class YnYannongDaAddActivity extends AppCompatActivity implements View.OnClickListener {
    private YannongDao yannongDao = null;

    private CharSequence temp;
    private int editStart;
    private int editEnd;
    PermissionHelper mPermissionHelper;
    //拍照
    Context context;
    private static final int CRAEMA_REQUEST_CODE = 4; //拍照请求码
    private boolean candelete = false; //是否可以删除照片
    private String defaultPhotoAddress = null; //拍照生成默认照片的绝对路径
    private String photoFolderAddress = null; //存放照片的文件夹
    private List<String> listPhotoNames = null;
    private CaremaAdapter cadapter = null;
    private int screenWidth = 0; //屏幕宽度
    IdcardInfoExtractor extractor;

    @ViewInject(R.id.textView)
    private TextView textViewtitle;
    @ViewInject(R.id.back_item)
    private ImageView ivtitleBack;
    @ViewInject(R.id.frm_first_yn_ynda_add)
    private ImageView ivPhoto;
    @ViewInject(R.id.frm_first_yn_ynda_add_caremaView)
    private GridView caremaView;

    @ViewInject(R.id.frm_first_yn_ynda_add_edittext_Name)
    private EditText editTextName;
    @ViewInject(R.id.frm_first_yn_ynda_add_edittext_IDcardNum)
    private EditText editTextIDCard;
    @ViewInject(R.id.frm_first_yn_ynda_add_edittext_phoneNum)
    private EditText editTextPhone;
    @ViewInject(R.id.frm_first_yn_ynda_add_edittext_zhongzhiAge)
    private EditText editTextzhongzhiAge;
    @ViewInject(R.id.frm_first_yn_ynda_add_edittext_zhongzhiArea)
    private EditText editTextzhongzhiArea;
    @ViewInject(R.id.frm_first_yn_ynda_add_edittext_beizhu)
    private EditText editTextbeizhu;

    @ViewInject(R.id.frm_first_yn_ynda_add_textview_age)
    private TextView textViewage;
    @ViewInject(R.id.frm_first_yn_ynda_add_textview_sex)
    private TextView textViewsex;
    @ViewInject(R.id.frm_first_yn_ynda_add_textview_wanggeFuzeren)
    private TextView textViewwanggeFuzeren;
    @ViewInject(R.id.frm_first_yn_ynda_add_textview_inputTime)
    private TextView textViewselectTime;//登记时间

    @ViewInject(R.id.frm_first_yn_ynda_add_spinner_cultureLevel)
    private Spinner spinnercultureLevel;
    @ViewInject(R.id.frm_first_yn_ynda_add_spinner_zhongzhiLevel)
    private Spinner spinnerzhongzhiLevel;
    @ViewInject(R.id.frm_first_yn_ynda_add_spinner_zhongzhiYears)
    private Spinner spinnerzhongzhiYears;
    @ViewInject(R.id.frm_first_yn_ynda_add_spinner_HomeAddress)
    private Spinner spinnerHomeAddress;
    @ViewInject(R.id.frm_first_yn_ynda_add_spinner_credit)
    private Spinner spinnercredit;
    @ViewInject(R.id.frm_first_yn_ynda_add_spinner_starPingding)
    private Spinner spinnerstarPingding;

    @ViewInject(R.id.frm_first_yn_ynda_add_btnback)
    private Button buttonback;
    @ViewInject(R.id.frm_first_yn_ynda_add_btnsave)
    private Button buttonsave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPermissionHelper = new PermissionHelper(this);
        mPermissionHelper.checkPermisson(new PermissionHelper.OnPermissionListener() {
            @Override
            public void onAgreePermission() {
                Toast.makeText(YnYannongDaAddActivity.this, "同意权限了", Toast.LENGTH_SHORT).show();
                // do something
            }

            @Override
            public void onDeniedPermission() {
                Toast.makeText(YnYannongDaAddActivity.this, "拒绝权限了", Toast.LENGTH_SHORT).show();
                finish(); // 当界面一定通过权限才能继续，就要加上这行
            }
        }, Manifest.permission.ACCESS_FINE_LOCATION);

        x.view().inject(this);
        context = this;
        yannongDao = new YannongDao(context);
        LocationUtils.initLocation(this);
        initCarema();
        initView();
        setEditListener();
        setDate();
        mySpinner();
        setListener();

    }

    private void initCarema() {
        //default photo address
        defaultPhotoAddress = CommonUtil.getSDPath() + File.separator + "default.jpg";

        //获取屏幕的分辨率
        DisplayMetrics  dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //窗口的宽度
        screenWidth = dm.widthPixels;

        //是否可删除照片
        candelete = getIntent().getBooleanExtra("candelete", true);
        //获取文件夹名称
        if(getIntent().getStringExtra("folderName") == null){
            photoFolderAddress = CommonUtil.getSDPath() + File.separator + "yannongdangan";
        }else{
            photoFolderAddress = getIntent().getStringExtra("folderName");
        }
    }

    private void setListener() {
        ivtitleBack.setOnClickListener(this);
        ivPhoto.setOnClickListener(this);
        buttonback.setOnClickListener(this);
        buttonsave.setOnClickListener(this);
    }

    private void setEditListener() {
        //烟农姓名
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = editTextName.getSelectionStart();
                editEnd = editTextName.getSelectionEnd();
                if (temp.length() > 6) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    editTextName.setText(s);
                    editTextName.setSelection(tempSelection1);
                }
            }
        });
        //烟农身份证号
        editTextIDCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = editTextIDCard.getSelectionStart();
                editEnd = editTextIDCard.getSelectionEnd();

                extractor = new IdcardInfoExtractor(s.toString());
                String age = extractor.getAge() + "";
                textViewage.setText(age);

                String sex = extractor.getGender();
                textViewsex.setText(sex);

                if (temp.length() >= 19) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的身份证号不合法！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection2 = editStart;
                    editTextIDCard.setText(s);
                    editTextIDCard.setSelection(tempSelection2);
                }
            }
        });
        //烟农电话号码
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = editTextPhone.getSelectionStart();
                editEnd = editTextPhone.getSelectionEnd();
                if (temp.length() > 11) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection3 = editStart;
                    editTextPhone.setText(s);
                    editTextPhone.setSelection(tempSelection3);
                }
            }
        });
        //烟农种植年限
        editTextzhongzhiAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = editTextzhongzhiAge.getSelectionStart();
                editEnd = editTextzhongzhiAge.getSelectionEnd();
                if (temp.length() > 4) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection4 = editStart;
                    editTextzhongzhiAge.setText(s);
                    editTextzhongzhiAge.setSelection(tempSelection4);
                }
            }
        });
        //种植面积
        editTextzhongzhiArea.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = editTextzhongzhiArea.getSelectionStart();
                editEnd = editTextzhongzhiArea.getSelectionEnd();
                if (temp.length() > 10) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection5 = editStart;
                    editTextzhongzhiArea.setText(s);
                    editTextzhongzhiArea.setSelection(tempSelection5);
                }
            }
        });
        //备注
        editTextbeizhu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                editStart = editTextbeizhu.getSelectionStart();
                editEnd = editTextbeizhu.getSelectionEnd();
                if (temp.length() > 50) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection6 = editStart;
                    editTextbeizhu.setText(s);
                    editTextbeizhu.setSelection(tempSelection6);
                }
            }
        });
    }

    private void mySpinner() {
        //文化水平
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.wenhua, R.layout.item_myspinner);
        spinnercultureLevel.setAdapter(adapter1);
        //种植水平
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.shuiping, R.layout.item_myspinner);
        spinnerzhongzhiLevel.setAdapter(adapter2);
        //种植年度
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        spinnerzhongzhiYears.setAdapter(adapter3);
        //行政区划
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this, R.array.age, R.layout.item_myspinner);
        spinnerHomeAddress.setAdapter(adapter4);
        //诚信度
        ArrayAdapter<CharSequence> adapter5 = ArrayAdapter.createFromResource(this, R.array.chengxindu, R.layout.item_myspinner);
        spinnercredit.setAdapter(adapter5);
        //星级评定
        ArrayAdapter<CharSequence> adapter6 = ArrayAdapter.createFromResource(this, R.array.pingji, R.layout.item_myspinner);
        spinnerstarPingding.setAdapter(adapter6);
    }

    private void setDate() {
        //时间选择
        textViewselectTime.setText(GetDate.lastDay());
        textViewselectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(YnYannongDaAddActivity.this);
                dialog.setDate(textViewselectTime);
            }
        });
    }

    private void initView() {
        textViewtitle.setText("烟农档案");
        //获取烟技员姓名
        //String realname = new MainActivity().getRealname();
        textViewwanggeFuzeren.setText("张大磊");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.back_item:
                    myDialog();
                break;

            case R.id.frm_first_yn_ynda_add:

                //验证sd卡是否可用
                if(CommonUtil.getSDPath() == null){
                    Toast.makeText(this, "请安装SD卡", Toast.LENGTH_SHORT).show();
                    return;
                }
                //验证是否超出限制照片数量
                if(listPhotoNames != null && listPhotoNames.size() >= Constants.MAX_PHOTO_SIZE){
                    Toast.makeText(context, "最多只允许拍摄" + Constants.MAX_PHOTO_SIZE + "张照片。", Toast.LENGTH_SHORT).show();
                    return;
                }

                //调用系统拍照
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(new File(defaultPhotoAddress)));
                startActivityForResult(intent, CRAEMA_REQUEST_CODE);

                break;
            case R.id.frm_first_yn_ynda_add_btnsave:
                saveInfo();
                Toast.makeText(YnYannongDaAddActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.frm_first_yn_ynda_add_btnback:
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

        ArrayList<Yannong> yantianguihuaList = new ArrayList<>();
        Yannong mYannong = new Yannong();

        mYannong.setId(IdUtil.getId());
        mYannong.setName(editTextName.getText().toString());
        mYannong.setIdcard(editTextIDCard.getText().toString());
        mYannong.setPhone(editTextPhone.getText().toString());
        mYannong.setSex(textViewsex.getText().toString());
        mYannong.setAge(textViewage.getText().toString());
        mYannong.setWorkyear(editTextzhongzhiAge.getText().toString());
        mYannong.setEducation(spinnercultureLevel.getSelectedItem().toString());
        mYannong.setArea(editTextzhongzhiArea.getText().toString());
        mYannong.setYear(spinnerzhongzhiYears.getSelectedItem().toString());
        mYannong.setSkill(spinnerzhongzhiLevel.getSelectedItem().toString());
        mYannong.setCredit(spinnercredit.getSelectedItem().toString());
        mYannong.setGrade(spinnerstarPingding.getSelectedItem().toString());
        mYannong.setVillage(spinnerHomeAddress.getSelectedItem().toString());
        mYannong.setCreatetime(textViewselectTime.getText().toString());
        mYannong.setTechnician(textViewwanggeFuzeren.getText().toString());
        mYannong.setDetail(editTextbeizhu.getText().toString());
        mYannong.setState(state);
        mYannong.setPhotopath(photoPath.toString());

        yantianguihuaList.add(mYannong);
        yannongDao.add(mYannong);
    }

    //返回按钮
    private void myDialog() {
        new AlertDialog.Builder(this).setTitle("系统提示")//设置对话框标题
                .setMessage("数据未保存，确认退出吗？")//设置显示的内容
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
    public void onResume() {
        super.onResume();
        mPermissionHelper.onResume(); // 当界面一定通过权限才能继续，就要加上这行
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPermissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        myDialog();
    }
}
