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
    private static final int CAREMA_REQUEST_CODE = 1;//???????????????

    private String defaultPhotoPath = null;//???????????????????????????????????????
    private String photoFolderPath = null;//????????????????????????
    private int screenWidth = 0;//????????????
    private String location2 = null;//???????????????????????????
    private boolean mCandelete = false; //?????????????????????
    private List<String> mListPhotos = null;//????????????????????????
    private CaremaAdapter mCaremaAdapter = null;//???????????????GridView????????????


    @ViewInject(R.id.textView)
    TextView textView;

    @ViewInject(R.id.back_item)
    ImageView back_img;

    @ViewInject(R.id.second_ym_bcfz_imageview)
    ImageView imageViewPhoto;
    @ViewInject(R.id.second_ym_bcfz_gridView)
    GridView  photoGridView;//?????????????????????


    @ViewInject(R.id.second_ym_bcfz_farmer)
    Spinner farmer;//?????????
    @ViewInject(R.id.second_ym_bcfz_yongyaomingcheng)
    EditText yaoming;//????????????
    @ViewInject(R.id.second_ym_bcfz_fangzhifangshi)
    EditText fangzhifangshi;//????????????

    @ViewInject(R.id.second_ym_bcfz_nongdu)
    EditText nongdu;//??????
    @ViewInject(R.id.second_ym_bcfz_bingchonghaileixing)
    Spinner leixing;//???????????????

    @ViewInject(R.id.second_ym_bcfz_fabinglv)
    EditText fabinglv;//?????????

    @ViewInject(R.id.second_ym_bcfz_area)
    EditText mianji;//????????????

    @ViewInject(R.id.second_ym_bcfz_yuguliang)
    EditText yugu;//???????????????

    @ViewInject(R.id.second_ym_bcfz_fangzhiTime)
    TextView shijian;//????????????

    @ViewInject(R.id.biaozhun_pic)
    ImageView biaozhun;//????????????

    @ViewInject(R.id.second_ym_bcfz_detail)
    EditText beizhu;//??????

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
            photoFolderPath = CommonUtil.getSDPath() + File.separator + "BingChongHaiFangZhi";
        } else {
            photoFolderPath = getIntent().getStringExtra("folderName");
        }
    }


    private void initView() {
        textView.setText("????????????");
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

        //????????????
        shijian.setText(GetDate.lastDay());
        shijian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                DateDialog dialog = new DateDialog(Add_BingchongFangzhiActivity.this);
                dialog.setDate(shijian);
            }
        });


    }

    //?????????spinner
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
  //onClick??????
    @Override
    public void onClick(View v) {
        //??????????????????
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.back_item:
                myDialog();
                break;
            //??????????????????
            case R.id.second_ym_bcfz_imageview:
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
            case R.id.second_ym_bcfz_save:
                //??????????????????????????????
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
        Toast.makeText(this, "????????????", Toast.LENGTH_SHORT).show();
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
        //??????????????????
        String url="http://192.168.0.108:8080/tobacco-app/yantianguanli/addBingchongfangzhi";
        //???????????????????????????map
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
            if (temp.length() > 10) {//????????????
                Toast.makeText(Add_BingchongFangzhiActivity.this,
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
        new AlertDialog.Builder(Add_BingchongFangzhiActivity.this).setTitle("????????????")//?????????????????????
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
   /* //?????????????????????
    private void getData() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String json = "http://192.168.0.108:8080/tobacco-app/yannong/queryAllYannongBytech";
                String jsonString2 = HttpUtils.getJsonContent(json);//?????????????????????
                List<ZhongzhihuInfoBean> list = GsonTools.getPersons(jsonString2,ZhongzhihuInfoBean.class);//??????json??????
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
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ??????sd????????????
                Log.i("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }
            new DateFormat();
            String name = DateFormat.format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
            Toast.makeText(this, name, Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");// ??????????????????????????????????????????Bitmap????????????

            FileOutputStream b = null;
            File file = new File("/sdcard/Image/");
            file.mkdirs();// ???????????????
            String fileName = "/sdcard/Image/"+name;
            try {
                b = new FileOutputStream(fileName);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ?????????????????????
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
                photo1.setImageBitmap(bitmap);// ??????????????????ImageView???
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
