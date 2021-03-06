package com.nss.tobacco.activitymanagement;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.nss.tobacco.daos.Sec_DadingDao;
import com.nss.tobacco.daos.Sec_JiemoDao;
import com.nss.tobacco.daos.YannongDao;
import com.nss.tobacco.entity.Sec_DadingEntity;
import com.nss.tobacco.entity.Sec_JiemoEntity;
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

@ContentView(R.layout.activity_add__dading)
public class Add_DadingActivity extends AppCompatActivity implements View.OnClickListener{
    private CharSequence temp;
    private int editStart;
    private int editEnd;
    private Map<String,Object> map;

    @ViewInject(R.id.textView)
    TextView textView;
    @ViewInject(R.id.back_item)
    ImageView back_img;

    @ViewInject(R.id.second_tjgl_dading_add_img)
    ImageView take_photo;
    @ViewInject(R.id.second_tjgl_dading_add_gridView)
    GridView  gridView;

    @ViewInject(R.id.second_tjgl_dading_add_farmer)
    Spinner spFarmer;

    @ViewInject(R.id.second_tjgl_dading_add_zhangshi)
    Spinner spZhangshi;//????????????

    @ViewInject(R.id.second_tjgl_dading_add_yiyaji)
    Spinner spYiyaji;//???????????????



    @ViewInject(R.id.second_tjgl_dading_add_xianleitime)
    TextView dateText1;//????????????

    @ViewInject(R.id.second_tjgl_dading_add_dadingtime)
    TextView dateText2;//????????????

    @ViewInject(R.id.second_tjgl_dading_add_youhuatime)
    TextView dateText3;//????????????
    @ViewInject(R.id.second_tjgl_dading_add_youhuashu)
    Spinner youhuapianshu;//?????????

    @ViewInject(R.id.biaozhun_pic)
    ImageView biaozhun;
    @ViewInject(R.id.second_tjgl_dading_add_detail)
    EditText beizhu;

    @ViewInject(R.id.second_tjgl_dading_add_btnsave)
    Button save;
    @ViewInject(R.id.second_tjgl_dading_add_btnback)
    Button back_btn;

    Context mContext;
    private Sec_DadingDao mDao = null;
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
        map = new HashMap<>();
        mContext = this;
        mDao = new Sec_DadingDao(mContext);
        mListPhotos = new ArrayList<>();
        initView();
        setDate();
        //???????????????
        initCareme();
        mySpinner();
        setListener();

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
            photoFolderPath = CommonUtil.getSDPath() + File.separator + "DaDing";
        } else {
            photoFolderPath = getIntent().getStringExtra("folderName");
        }
    }
    private void initView() {
        textView.setText("????????????");
    }

    private void setListener(){
        back_img.setOnClickListener(this);
        take_photo.setOnClickListener(this);
        save.setOnClickListener(this);
        back_btn.setOnClickListener(this);
    }
    private void setDate() {
        //????????????
        dateText1.setText(GetDate.lastDay());
        dateText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_DadingActivity.this);
                dialog.setDate(dateText1);
            }
        });
        dateText2.setText(GetDate.lastDay());
        dateText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_DadingActivity.this);
                dialog.setDate(dateText2);
            }
        });
        dateText3.setText(GetDate.lastDay());
        dateText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_DadingActivity.this);
                dialog.setDate(dateText3);
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
        spFarmer.setAdapter(adapter);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.youhuapianshu, R.layout.item_myspinner);
        youhuapianshu.setAdapter(adapter2);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.age, R.layout.item_myspinner);
        spYiyaji.setAdapter(adapter3);
        ArrayAdapter<CharSequence> adapter4 = ArrayAdapter.createFromResource(this,R.array.zhangshi, R.layout.item_myspinner);
        spZhangshi.setAdapter(adapter4);
    }
    @Override
    public void onClick(View v) {
        //??????????????????
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                finish();
                break;
            case R.id.second_tjgl_dading_add_img:
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
            case R.id.second_tjgl_dading_add_btnsave:
                //??????????????????????????????
                saveInfo();
                finish();
                break;
            case R.id.second_tjgl_dading_add_btnback:
                myDialog();
                break;
            default:
                break;
        }
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
        Sec_DadingEntity info = new Sec_DadingEntity();
        info.setId(IdUtil.getId());
        info.setFarmer(spFarmer.getSelectedItem().toString());
        info.setZhangshi(spZhangshi.getSelectedItem().toString());
        info.setYiyaji(spYiyaji.getSelectedItem().toString());
        info.setXianleitime(dateText1.getText().toString());
        info.setDadingtime(dateText2.getText().toString());
        info.setYouhuatime(dateText3.getText().toString());
        info.setYouhuashu(youhuapianshu.getSelectedItem().toString());
        info.setDetail(beizhu.getText().toString());
        info.setPhotopath(photoPath.toString());
        info.setState(state);
        mDao.add(info);
        Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
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
                            mCaremaAdapter = new CaremaAdapter(mContext, screenWidth, mListPhotos, mCandelete);
                            gridView.setAdapter(mCaremaAdapter);
                        } else {
                            mCaremaAdapter.notifyDataSetChanged();
                        }
                    }
                    break;
            }
        }
    }
}
