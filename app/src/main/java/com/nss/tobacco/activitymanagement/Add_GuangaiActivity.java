package com.nss.tobacco.activitymanagement;

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
import com.nss.tobacco.daos.Sec_GuangaiDao;
import com.nss.tobacco.daos.Sec_MiaoChuangZhuiFeiDao;
import com.nss.tobacco.daos.YannongDao;
import com.nss.tobacco.entity.Sec_GuangaiEntity;
import com.nss.tobacco.entity.Sec_MiaoChuangZhuiFeiEntity;
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

@ContentView(R.layout.activity_add__guangai)
public class Add_GuangaiActivity extends AppCompatActivity implements View.OnClickListener{
    private CharSequence temp;
    private int editStart;
    private int editEnd;
    private Map<String,Object> map;

    @ViewInject(R.id.textView)
    TextView textView;
    @ViewInject(R.id.back_item)
    ImageView back_img;

    @ViewInject(R.id.second_tjgl_guangai_add_imageView)
    ImageView take_photo;
    @ViewInject(R.id.second_tjgl_guangai_add_gridView)
    GridView gridView;

    @ViewInject(R.id.spinner_zhongzhihu)
    Spinner name;
    @ViewInject(R.id.mianji_edit)
    EditText mianji;
    @ViewInject(R.id.shijian_text)
    TextView dateText1;
    @ViewInject(R.id.spinner_fangshi)
    Spinner fangshi;
    @ViewInject(R.id.biaozhun_pic)
    ImageView biaozhun;
    @ViewInject(R.id.beizhu_edit)
    EditText beizhu;
    @ViewInject(R.id.btn_save)
    Button save;
    @ViewInject(R.id.btn_back)
    Button back_btn;

    Context mContext;
    private Sec_GuangaiDao mDao = null;
    private static final int CAREMA_REQUEST_CODE = 1;//???????????????

    private String defaultPhotoPath = null;//???????????????????????????????????????
    private String photoFolderPath = null;//????????????????????????
    private int screenWidth = 0;//????????????
    private String location2 = null;//???????????????????????????
    private boolean mCandelete = false; //?????????????????????
    private List<String> mListPhotos = null;//????????????????????????
    private CaremaAdapter mCaremaAdapter = null;//???????????????GridView????????????
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        mContext=this;
        mDao=new Sec_GuangaiDao(this);
        map=new HashMap<>();
        mListPhotos=new ArrayList<>();
        initView();
        setDate();
        //???????????????
        initCareme();
        mySpinner();
        setListener();
    }
    private void initView() {
        textView.setText("??????");
    }
    private void initCareme() {
        defaultPhotoPath = CommonUtil.getSDPath() + File.separator + "default.jpg";
        //?????????????????????
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //???????????????
        screenWidth = dm.widthPixels;
        //?????????????????????
        mCandelete = getIntent().getBooleanExtra("candelete", true);
        if (getIntent().getStringExtra("folderName") == null) {
            photoFolderPath = CommonUtil.getSDPath() + File.separator + "GuanGai";
        } else {
            photoFolderPath = getIntent().getStringExtra("folderName");
        }
    }
    private void setListener() {
        back_img.setOnClickListener(this);
        take_photo.setOnClickListener(this);
        save.setOnClickListener(this);
        back_btn.setOnClickListener(this);
        getMianjiEditListener();
    }

    private void setDate() {
        mySpinner();
        //????????????
        dateText1.setText(GetDate.lastDay());
        dateText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_GuangaiActivity.this);
                dialog.setDate(dateText1);
            }
        });
    }

    //?????????spinner
    private void mySpinner() {
        YannongDao mYanNongDao=new YannongDao(this);
        ArrayList<Yannong> yumiaohus = mYanNongDao.searchAllData();
        List<String> yannong=new ArrayList<>();
        for(Yannong info:yumiaohus){
            yannong.add(info.getName());
        }
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this,R.layout.item_myspinner,yannong);
        name.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.guangai, R.layout.item_myspinner);
        fangshi.setAdapter(adapter2);
    }

    @Override
    public void onClick(View v) {
        //??????????????????
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            case R.id.second_tjgl_guangai_add_imageView:
                //??????????????????SD???
                if (CommonUtil.getSDPath() == null) {
                    Toast.makeText(this, "?????????SD???", Toast.LENGTH_SHORT).show();
                    return;
                }
                //????????????????????????????????????
                if (mListPhotos != null && mListPhotos.size() >= Constants.MAX_PHOTO_SIZE) {
                    Toast.makeText(this, "??????????????????" + Constants.MAX_PHOTO_SIZE, Toast.LENGTH_SHORT).show();
                    return;
                }
                //??????????????????
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
    //??????????????????????????????
    private void saveInfo() {
        //state?????????????????? 0???????????????,1???????????????
        String state = "0";
        StringBuffer photoPath=new StringBuffer();
        if(mListPhotos.size()>0){
            for(String path:mListPhotos){
                int start= path.lastIndexOf("/");
                int end= path.lastIndexOf(".");
                photoPath.append(path.substring(start+1,end)+" ");

            }
        }

        Sec_GuangaiEntity info = new Sec_GuangaiEntity();
        info.setId(IdUtil.getId());
        info.setFarmer(name.getSelectedItem().toString());
        info.setWay(fangshi.getSelectedItem().toString());
        info.setArea(mianji.getText().toString());
        info.setWorktime(dateText1.getText().toString());
        info.setDetail(beizhu.getText().toString());

        info.setState(state);
        info.setPhotopath(photoPath.toString());
        Log.i("TAG", "saveInfo: "+IdUtil.getId()+name.getSelectedItem().toString()+fangshi.getSelectedItem().toString()+mianji.getText().toString()
        +dateText1.getText().toString()+beizhu.getText().toString());
        mDao.add(info);
        Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
    }
    public void getMianjiEditListener(){
        mianji.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                temp = s;
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                map.put("area",s);
                editStart = mianji.getSelectionStart();
                editEnd = mianji.getSelectionEnd();
                if (temp.length() > 10) {//????????????
                    Toast.makeText(getApplicationContext(),
                            "???????????????????????????????????????", Toast.LENGTH_SHORT)
                            .show();
                    s.delete(editStart - 1, editEnd);
                    int tempSelection = editStart;
                    mianji.setText(s);
                    mianji.setSelection(tempSelection);
                }
            }
        });

    }
    //?????????????????????
    private void myDialog() {
        new AlertDialog.Builder(this).setTitle("????????????")//?????????????????????
                .setMessage("??????????????????????????????")//?????????????????????
                .setPositiveButton("??????",new DialogInterface.OnClickListener() {//??????????????????
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//???????????????????????????
                        // TODO Auto-generated method stub
                        finish();
                    }
                }).setNegativeButton("??????",new DialogInterface.OnClickListener() {//??????????????????
            @Override
            public void onClick(DialogInterface dialog, int which) {//????????????
                // TODO Auto-generated method stub
                Log.i("alertdialog"," ??????????????????");
            }
        }).show();//??????????????????????????????????????????
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAREMA_REQUEST_CODE:

                    File folderAddr = new File(photoFolderPath);
                    //???????????????????????????
                    if (!folderAddr.exists() || !folderAddr.isDirectory()) {
                        folderAddr.mkdirs();
                    }
                    //??????????????????????????????
                    String targetPath = photoFolderPath+File.separator+ CommonUtil.getUUID32() + ".jpg";
                    location2 = "??????: " + LocationUtils.longitude + "??????: " + LocationUtils.latitude + "  ";
                    boolean dealFlag= CommonUtil.dealImage(defaultPhotoPath, targetPath, location2);
                    if (dealFlag) {
                        //??????????????????
                        File fileDefault = new File(defaultPhotoPath);
                        fileDefault.delete();
                        //??????????????????????????????
                        mListPhotos.add(targetPath);
                        if (mCaremaAdapter == null) {
                            mCaremaAdapter = new CaremaAdapter(this, screenWidth, mListPhotos, mCandelete);
                            gridView.setAdapter(mCaremaAdapter);
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

