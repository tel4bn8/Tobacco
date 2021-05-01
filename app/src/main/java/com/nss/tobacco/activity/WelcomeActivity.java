package com.nss.tobacco.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.nss.tobacco.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                judgeLogin();
            }
        };
        timer.schedule(task, 2500);
    }
    private void judgeLogin() {
        if (LoginActivity.isLogin){
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
/*
    private UpdateInfo updateInfo;
    private ProgressDialog progressDialog;

    private static final String BASE_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //检查app更新
*/
/*        new Thread() {
            @Override
            public void run() {
             try {
                    UpdateInfoService updateInfoService = new UpdateInfoService(WelcomeActivity.this);
                    updateInfo = updateInfoService.getUpdataInfo();

                    handler.sendEmptyMessage(0);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();*//*


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                judgeLogin();
            }
        };
        timer.schedule(task, 1500);
    }

    private void judgeLogin() {
        if (LoginActivity.isLogin){
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isNeedUpdate()){
                showUpdateDialog();
            }
        }
    };


    //显示更新对话框
    public void showUpdateDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_dialog_info);
        builder.setTitle("请更新软件至版本" + updateInfo.getVersion());
        builder.setMessage(updateInfo.getDescription());
        builder.setCancelable(false);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    downFile(updateInfo.getUrl(),BASE_PATH);
                }else {
                    Toast.makeText(WelcomeActivity.this, "SD卡不可用，请插入SD卡", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

    //判断是否需要更新
    public boolean isNeedUpdate() {

        String vs = updateInfo.getVersion();
        Log.i("update", vs);
        if (vs.equals(getVersion())) {
            return false;
        } else {
            return true;
        }
    }

    //获取当前版本名
    private String getVersion() {

        try {
            PackageManager packageManager = getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);

            return packageInfo.versionName;//返回当前版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

            return "未知版本号";
        }
    }

    //下载app
    void downFile(final String url, final String path) {

        new Thread() {
            @Override
            public void run() {

                RequestParams requestParams = new RequestParams(url);
                requestParams.setSaveFilePath(path);
                x.http().get(requestParams, new Callback.ProgressCallback<File>() {

                    @Override
                    public void onWaiting() {
                    }

                    @Override
                    public void onStarted() {
                    }


                    @Override
                    public void onLoading(long total, long current, boolean isDownloading) {

                        //进度条
                        progressDialog = new ProgressDialog(WelcomeActivity.this);
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                        progressDialog.setTitle("正在下载");
                        progressDialog.setMessage("请稍后");
                        progressDialog.show();
                        progressDialog.setMax((int) total);
                        progressDialog.setProgress((int) current);

                    }

                    @Override
                    public void onSuccess(File result) {
                        Toast.makeText(WelcomeActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        ex.printStackTrace();
                        Toast.makeText(WelcomeActivity.this, "下载失败，请检查网络和SD卡", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            }
        }.start();
    }
    */
