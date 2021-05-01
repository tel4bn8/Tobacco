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
import com.nss.tobacco.activity.MainActivity;
import com.nss.tobacco.adapter.AddressAdapter;
import com.nss.tobacco.adapter.CaremaAdapter;
import com.nss.tobacco.daos.JichuSheshiDao;
import com.nss.tobacco.entity.Address;
import com.nss.tobacco.entity.JichuSheshi;
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

@ContentView(R.layout.activity_wg_jichu_add)
public class WgJichuAddActivity extends AppCompatActivity implements View.OnClickListener{
    private JichuSheshiDao jichuSheshiDao = null;

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

    @ViewInject(R.id.textView)
    private TextView textViewtitle;
    @ViewInject(R.id.back_item)
    private ImageView ivtitleBack;
    @ViewInject(R.id.frm_first_wg_jichu_add_caremaView)
    private GridView caremaView;
    @ViewInject(R.id.frm_first_wg_jichu_add)
    private ImageView ivPhoto;
    @ViewInject(R.id.frm_first_wg_jichu_add_spinner_village)
    private Spinner spVillage;
    @ViewInject(R.id.frm_first_wg_jichu_add_edittext_kaofangNum)
    private EditText etKaofangNum;
    @ViewInject(R.id.frm_first_wg_jichu_add_edittext_jijingNum)
    private EditText etJijingNum;
    @ViewInject(R.id.frm_first_wg_jichu_add_spinner_yewuYear)
    private Spinner spYewuYear;
    @ViewInject(R.id.frm_first_wg_jichu_add_textview_inputTime)
    private TextView tvDengjiTime;
    @ViewInject(R.id.frm_first_wg_jichu_add_textview_yanjiyuan)
    private TextView tvYanjiyuan;
    @ViewInject(R.id.frm_first_wg_jichu_add_edittext_beizhu)
    EditText etBeizhu;

    @ViewInject(R.id.frm_first_wg_jichu_add_btnsave)
    private Button btnSave;
    @ViewInject(R.id.frm_first_wg_jichu_add_btnback)
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        context = this;
        jichuSheshiDao = new JichuSheshiDao(context);
        listPhotoNames = new ArrayList<>();

        initCarema();
        initView();
        initListener();
        setDate();
        mySpinner();
        setEditListener();

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
            photoFolderAddress = CommonUtil.getSDPath() + File.separator + "jichusheshi";
        }else{
            photoFolderAddress = getIntent().getStringExtra("folderName");
        }
    }

    private void setEditListener() {
        etKaofangNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etKaofangNum.getSelectionStart();
                editEnd = etKaofangNum.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    etKaofangNum.setText(s);
                    etKaofangNum.setSelection(tempSelection1);
                }
            }
        });
        etJijingNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = etJijingNum.getSelectionStart();
                editEnd = etJijingNum.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection2 = editStart;
                    etJijingNum.setText(s);
                    etJijingNum.setSelection(tempSelection2);
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
                    int tempSelection3 = editStart;
                    etBeizhu.setText(s);
                    etBeizhu.setSelection(tempSelection3);
                }
            }
        });
    }

    private void mySpinner() {
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(new Address("肖旗"));
        addressList.add(new Address("柳树"));
        addressList.add(new Address("大营"));
        addressList.add(new Address("小张村"));
        addressList.add(new Address("李村"));
        addressList.add(new Address("大店"));
        addressList.add(new Address("薛营"));
        AddressAdapter addressAdapter = new AddressAdapter(addressList, this);
        spVillage.setAdapter(addressAdapter);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.year_item, R.layout.item_myspinner);
        spYewuYear.setAdapter(adapter1);
    }

    private void setDate() {
        String time = GetDate.lastDay();
        tvDengjiTime.setText(time);
        tvDengjiTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateDialog dialog = new DateDialog(WgJichuAddActivity.this);
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
        textViewtitle.setText("基础设施");
        //获取烟技员
        String realname = new MainActivity().getRealname();
        //tvYanjiyuan.setText(realname);
        tvYanjiyuan.setText("张大磊");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_item:
                myDialog();
                break;
            case R.id.frm_first_wg_jichu_add:

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
            case R.id.frm_first_wg_jichu_add_btnsave:

                saveInfo();
                Toast.makeText(WgJichuAddActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.frm_first_wg_jichu_add_btnback:
                    myDialog();
                break;
            default:
                break;
        }
    }

    //添加数据
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
        ArrayList<JichuSheshi> jichuList = new ArrayList<>();
        JichuSheshi mJichuSheshi = new JichuSheshi();

        mJichuSheshi.setId(IdUtil.getId());
        mJichuSheshi.setYear(spYewuYear.getSelectedItem().toString());
        mJichuSheshi.setVillage(spVillage.getSelectedItem().toString());
        mJichuSheshi.setKaofangnum(etKaofangNum.getText().toString());
        mJichuSheshi.setJijingnum(etJijingNum.getText().toString());
        mJichuSheshi.setCreatetime(tvDengjiTime.getText().toString());
        mJichuSheshi.setTechnician(tvYanjiyuan.getText().toString());
        mJichuSheshi.setDetail(etBeizhu.getText().toString());
        mJichuSheshi.setState(state);
        mJichuSheshi.setPhotopath(photoPath.toString());

        jichuList.add(mJichuSheshi);
        jichuSheshiDao.add(mJichuSheshi);
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
