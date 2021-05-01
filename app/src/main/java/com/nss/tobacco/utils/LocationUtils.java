package com.nss.tobacco.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.text.DecimalFormat;

/**
 * 经纬度获取
 */

public class LocationUtils {
    // 纬度  平顶山
    public static String latitude = "33.431655";
    // 经度
    public static String longitude = "113.180513";

    /**
     * 初始化位置信息
     *
     * @param context
     */

    public static void initLocation(Context context) {
        DecimalFormat df = new DecimalFormat(".000000");
        LocationManager locationManager = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                latitude = df.format(location.getLatitude());
                Log.i("aaa", "-------------------aaaaaaaaaaaaaaaaaaaa"+latitude);
                longitude = df.format(location.getLongitude());
            }
        } else {
            LocationListener locationListener = new LocationListener(){

                // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
                @Override
                public void onStatusChanged(String provider, int status,
                                            Bundle extras) {

                }
                // Provider被enable时触发此函数，比如GPS被打开
                @Override
                public void onProviderEnabled(String provider) {

                }
                // Provider被disable时触发此函数，比如GPS被关闭
                @Override
                public void onProviderDisabled(String provider) {

                }
                // 当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
                @Override
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        DecimalFormat df = new DecimalFormat(".000000");
                        latitude = df.format(location.getLatitude());
                        Log.i("aaa", "-------------------aaaaaaaaaaaaaaaaaaaa"+latitude);
                        longitude = df.format(location.getLongitude());
                    }
                }
            };
        }
    }
}
