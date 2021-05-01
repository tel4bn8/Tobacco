package com.nss.tobacco.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.nss.tobacco.entity.UpdateInfo;
import com.nss.tobacco.url.API_Data;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 *
 */

public class UpdateInfoService {


    Context context;
    private UpdateInfo info=new UpdateInfo();
    public UpdateInfoService(Context context) {
        this.context = context;
    }

    public UpdateInfo getUpdataInfo() {

        XUtil.Get(API_Data.APP_UPDATE, null, new MyCallBack<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                Gson gson = new Gson();
                UpdateInfo  infos = gson.fromJson(result, UpdateInfo.class);
                Log.i("TAG", "onSuccess: "+infos.getPath());

            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                super.onError(ex, isOnCallback);
            }
        });
        Log.i("TAG", "getUpdataInfo: "+info.getPath());
        return info;
    }
}
