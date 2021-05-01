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
import com.nss.tobacco.adapter.FarmerAdapter;
import com.nss.tobacco.daos.Sec_TianjianpingguDao;
import com.nss.tobacco.daos.YannongDao;
import com.nss.tobacco.entity.FarmerEntity;
import com.nss.tobacco.entity.Sec_TianjianpingguEntity;
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
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

@ContentView(R.layout.activity_add__tianjianpinggu)
public class Add_TianjianpingguActivity extends AppCompatActivity implements View.OnClickListener{
    private Sec_TianjianpingguDao sec_tianjianpingguDao = null;

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
    TextView textView;
    @ViewInject(R.id.back_item)
    ImageView back_img;
    @ViewInject(R.id.second_tjpg_add_camera)
    ImageView take_photo;
    @ViewInject(R.id.second_tjpg_add_caremaView)
    private GridView caremaView;

    @ViewInject(R.id.second_tjpg_add_spinner_zhongzhihu)
    Spinner name;
    @ViewInject(R.id.second_tjpg_add_shijian_text)
    TextView dateText1;
    @ViewInject(R.id.second_tjpg_add_mianji_edit)
    EditText pinggumianji;
    @ViewInject(R.id.second_tjpg_add_zhushu_edit)
    EditText zhushu;
    @ViewInject(R.id.second_tjpg_add_youxiaoye_edit)
    EditText youxiaoye;
    @ViewInject(R.id.second_tjpg_add_danye_edit)
    EditText danye;
    @ViewInject(R.id.second_tjpg_add_zongzhong_edit)
    EditText zongzhong;
    @ViewInject(R.id.second_tjpg_add_biaozhun_pic)
    ImageView biaozhun;
    @ViewInject(R.id.second_tjpg_add_beizhu_edit)
    EditText beizhu;
    @ViewInject(R.id.second_tjpg_add_btn_save)
    Button save;
    @ViewInject(R.id.second_tjpg_add_btn_back)
    Button back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        context = this;
        sec_tianjianpingguDao = new Sec_TianjianpingguDao(this);
        listPhotoNames = new ArrayList<>();

        initCarema();
        initView();
        setDate();
        mySpinner();
        setListener();
        setEditListener();
    }

    private void setEditListener() {
        pinggumianji.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = pinggumianji.getSelectionStart();
                editEnd = pinggumianji.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection1 = editStart;
                    pinggumianji.setText(s);
                    pinggumianji.setSelection(tempSelection1);
                }
            }
        });
        zhushu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = zhushu.getSelectionStart();
                editEnd = zhushu.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection2 = editStart;
                    zhushu.setText(s);
                    zhushu.setSelection(tempSelection2);
                }
            }
        });
        youxiaoye.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = youxiaoye.getSelectionStart();
                editEnd = youxiaoye.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection3 = editStart;
                    youxiaoye.setText(s);
                    youxiaoye.setSelection(tempSelection3);
                }
            }
        });
        danye.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = danye.getSelectionStart();
                editEnd = danye.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection4 = editStart;
                    danye.setText(s);
                    danye.setSelection(tempSelection4);
                }
            }
        });
        zongzhong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editStart = zongzhong.getSelectionStart();
                editEnd = zongzhong.getSelectionEnd();
                if (temp.length() > 5) {//限制长度
                    Toast.makeText(getApplicationContext(),
                            "输入的字数已经超过了限制！", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection5 = editStart;
                    zongzhong.setText(s);
                    zongzhong.setSelection(tempSelection5);
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
            photoFolderAddress = CommonUtil.getSDPath() + File.separator + "tianjianpinggu";
        }else{
            photoFolderAddress = getIntent().getStringExtra("folderName");
        }
    }

    private void initView() {
        textView.setText("田间评估");
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
                DateDialog dialog = new DateDialog(Add_TianjianpingguActivity.this);
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
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_item:
                myDialog();
                break;
            case R.id.second_tjpg_add_camera:

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
            case R.id.second_tjpg_add_btn_save:
                saveInfo();
                Toast.makeText(this, "保存成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.second_tjpg_add_btn_back:
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

        ArrayList<Sec_TianjianpingguEntity> tianjianpingguList = new ArrayList<>();
        Sec_TianjianpingguEntity tianjianpingguEntity = new Sec_TianjianpingguEntity();

        tianjianpingguEntity.setId(IdUtil.getId());
        if (TextUtils.isEmpty(name.getSelectedItem().toString())){
            tianjianpingguEntity.setFarmer("请先录入烟农");
        }else {
            tianjianpingguEntity.setFarmer(name.getSelectedItem().toString());
        }

        //String yucezhongliang = zongzhong.getText().toString();

        double a = Double.parseDouble(pinggumianji.getText().toString());
        BigDecimal mj = new BigDecimal(a);
        double aa = Double.parseDouble(danye.getText().toString());
        BigDecimal dyzl = new BigDecimal(aa);

        BigDecimal result1 = mj.multiply(dyzl, new MathContext(2));
        int mmzs = Integer.parseInt(zhushu.getText().toString());
        int yxys = Integer.parseInt(youxiaoye.getText().toString());




        tianjianpingguEntity.setArea(pinggumianji.getText().toString());
        tianjianpingguEntity.setZhunum(zhushu.getText().toString());
        tianjianpingguEntity.setLeafnum(youxiaoye.getText().toString());
        tianjianpingguEntity.setOne(danye.getText().toString());
        tianjianpingguEntity.setSum(zongzhong.getText().toString());
        tianjianpingguEntity.setCreatetime(dateText1.getText().toString());
        tianjianpingguEntity.setDetail(beizhu.getText().toString());
        tianjianpingguEntity.setState(state);
        tianjianpingguEntity.setPhotopath(photoPath.toString());

        tianjianpingguList.add(tianjianpingguEntity);
        sec_tianjianpingguDao.add(tianjianpingguEntity);
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
