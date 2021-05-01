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
import com.nss.tobacco.adapter.FarmerAdapter;
import com.nss.tobacco.daos.Sec_HongkaoDao;
import com.nss.tobacco.daos.YannongDao;
import com.nss.tobacco.entity.FarmerEntity;
import com.nss.tobacco.entity.Sec_HongkaoEntity;
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

@ContentView(R.layout.activity_add__hongkao)
public class Add_HongkaoActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_HongkaoDao sec_hongkaoDao = null;

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
    @ViewInject(R.id.second_hk_add_camera)
    private ImageView take_photo;
    @ViewInject(R.id.second_hk_add_caremaView)
    private GridView caremaView;

    @ViewInject(R.id.second_hk_add_spinner_zhongzhihu)
    private Spinner name;
    @ViewInject(R.id.second_hk_add_mianji_edit)
    private EditText mianji;
    @ViewInject(R.id.second_hk_add_shijian_text)
    private TextView dateText1;
    @ViewInject(R.id.second_hk_add_spinner_zhuanyehua)
    private Spinner zhuanyehua;
    @ViewInject(R.id.second_hk_add_spinner_quqingquza)
    private Spinner quqingquza;
    @ViewInject(R.id.second_hk_add_xianyan_edit)
    private EditText xianyan;
    @ViewInject(R.id.second_hk_add_honggan_edit)
    private EditText ganyan;
    @ViewInject(R.id.second_hk_add_haodian_edit)
    private EditText haodian;
    @ViewInject(R.id.second_hk_add_haomei_edit)
    private EditText haomei;
    @ViewInject(R.id.second_hk_add_chengben_edit)
    private EditText chengben;
    @ViewInject(R.id.second_hk_add_biaozhun_pic)
    private ImageView biaozhun;
    @ViewInject(R.id.second_hk_add_beizhu_edit)
    private EditText beizhu;
    @ViewInject(R.id.second_hk_add_btn_save)
    private Button save;
    @ViewInject(R.id.second_hk_add_btn_back)
    private Button back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__hongkao);
        x.view().inject(this);
        context = this;
        sec_hongkaoDao = new Sec_HongkaoDao(this);
        listPhotoNames = new ArrayList<>();

        initCarema();
        initView();
        setDate();
        mySpinner();
        setListener();
        setEditListener();
    }

    private void setEditListener() {
        xianyan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = xianyan.getSelectionStart();
                editEnd = xianyan.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    xianyan.setText(s);
                    xianyan.setSelection(tempSelection1);
                }
            }
        });
        ganyan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = ganyan.getSelectionStart();
                editEnd = ganyan.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection2 = editStart;
                    ganyan.setText(s);
                    ganyan.setSelection(tempSelection2);
                }
            }
        });
        haodian.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = haodian.getSelectionStart();
                editEnd = haodian.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection3 = editStart;
                    haodian.setText(s);
                    haodian.setSelection(tempSelection3);
                }
            }
        });
        haomei.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = haomei.getSelectionStart();
                editEnd = haomei.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection4 = editStart;
                    haomei.setText(s);
                    haomei.setSelection(tempSelection4);
                }
            }
        });
        chengben.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = chengben.getSelectionStart();
                editEnd = chengben.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection5 = editStart;
                    chengben.setText(s);
                    chengben.setSelection(tempSelection5);
                }
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
            photoFolderAddress = CommonUtil.getSDPath() + File.separator + "hongkaoguanli";
        }else{
            photoFolderAddress = getIntent().getStringExtra("folderName");
        }
    }

    private void initView() {
        textView.setText("烘烤");
    }

    private void setListener() {
        back_img.setOnClickListener(this);
        take_photo.setOnClickListener(this);
        save.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }

    private void setDate() {
        mySpinner();
        //时间选择
        dateText1.setText(GetDate.lastDay());
        dateText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_HongkaoActivity.this);
                dialog.setDate(dateText1);
            }
        });
    }

    //自定义spinner
    private void mySpinner() {
        List<FarmerEntity> yannongfarmer = new ArrayList<>();
        List<Yannong> yannong = new ArrayList<>();
        YannongDao yannongDao = new YannongDao(this);
        yannong = yannongDao.searchAllData();

        for (Yannong info:yannong){
            yannongfarmer.add(new FarmerEntity(info.getName()));
        }
        FarmerAdapter myadapter = new FarmerAdapter(yannongfarmer, context);
        name.setAdapter(myadapter);

        /*ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.age, R.layout.item_myspinner);
        name.setAdapter(adapter1);*/
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.shifou, R.layout.item_myspinner);
        zhuanyehua.setAdapter(adapter2);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.shifou, R.layout.item_myspinner);
        quqingquza.setAdapter(adapter3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            case R.id.second_hk_add_camera:

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
            case R.id.second_hk_add_btn_save:
                saveInfo();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.second_hk_add_btn_back:
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

        ArrayList<Sec_HongkaoEntity> hongkaoList = new ArrayList<>();
        Sec_HongkaoEntity hongkaoEntity = new Sec_HongkaoEntity();

        hongkaoEntity.setId(IdUtil.getId());
        hongkaoEntity.setFarmer(name.getSelectedItem().toString());
        hongkaoEntity.setArea(mianji.getText().toString());
        hongkaoEntity.setBeforeweight(xianyan.getText().toString());
        hongkaoEntity.setAfterweight(ganyan.getText().toString());
        hongkaoEntity.setElectric(haodian.getText().toString());
        hongkaoEntity.setCoal(haomei.getText().toString());
        hongkaoEntity.setZhuanyehongkao(zhuanyehua.getSelectedItem().toString());
        hongkaoEntity.setQuqingquza(quqingquza.getSelectedItem().toString());
        hongkaoEntity.setMoney(chengben.getText().toString());
        hongkaoEntity.setCreatetime(dateText1.getText().toString());
        hongkaoEntity.setDetail(beizhu.getText().toString());
        hongkaoEntity.setState(state);
        hongkaoEntity.setPhotopath(photoPath.toString());

        hongkaoList.add(hongkaoEntity);
        sec_hongkaoDao.add(hongkaoEntity);
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
