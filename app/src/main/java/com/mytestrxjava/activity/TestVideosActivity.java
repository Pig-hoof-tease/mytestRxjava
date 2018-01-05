package com.mytestrxjava.activity;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.mytestrxjava.R;
import com.mytestrxjava.utils.LogUtils;
import com.mytestrxjava.utils.ToastUtil;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * Created by Yomoo on 2017/9/19.
 */

public class TestVideosActivity extends AppCompatActivity {
//    private VideoView videoView;
    private TextView txtAddressStateDa;
    private String url = "http://m.meijukankan.net/mm/?s=Player-Index-vid-1482-part-2/geth5.php?mp=http://pan.meijukankan.net/tianyi/down.php/800080216/wl/3139731648139452.mp4";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_test);
        txtAddressStateDa = (TextView) findViewById(R.id.txtAddressStateDa);
        //本地的视频 需要在手机SD卡根目录添加一个 fl1234.mp4 视频
//        String videoUrl1 = Environment.getExternalStorageDirectory().getPath()+"/fl1234.mp4" ;
        getAddress();
//        //网络视频
//        String videoUrl2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";
////        String videoUrl2=url;
//        Uri uri = Uri.parse(videoUrl2);
//
//        videoView = (VideoView) this.findViewById(R.id.video_test);
//
//        //设置视频控制器
//        videoView.setMediaController(new MediaController(this));
//
//        //播放完成回调
//        videoView.setOnCompletionListener(new MyPlayerOnCompletionListener());
//
//        //设置视频路径
//        videoView.setVideoURI(uri);
//
//        //开始播放视频
//        videoView.start();
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            ToastUtil.showToastLong("播放完成了");
        }
    }

    /**
     * 获取当前经纬度和地址
     */
    private void getAddress() {
        LocationManager locationManager;
        String serviceName = getApplicationContext().LOCATION_SERVICE;
        locationManager = (LocationManager) getSystemService(serviceName);
//         String provider = LocationManager.GPS_PROVIDER;
        String provider = LocationManager.NETWORK_PROVIDER;
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        criteria.setAltitudeRequired(false);
//        criteria.setBearingRequired(false);
//        criteria.setCostAllowed(true);
//        criteria.setPowerRequirement(Criteria.POWER_HIGH);
//        String provider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
        Location location = locationManager.getLastKnownLocation(provider);
        if (location == null) {
            provider = LocationManager.GPS_PROVIDER;
            locationManager.requestLocationUpdates(provider, 2000, 10, locationListener);
            location = locationManager.getLastKnownLocation(provider);
            if (location == null){
                //            location = locationManager.getLastKnownLocation(provider);
                txtAddressStateDa.setText("权限没有打开 : ");
                txtAddressStateDa.setText("无法获取地理信息");
            }else {
                LogUtils.eLog("huangzhenhui----GPS_PROVIDER");
                updateWithNewLocation(location);
                locationManager.removeUpdates(locationListener);
            }

        }else {
            LogUtils.dLog("huangzhenhui---NETWORK_PROVIDER");
            updateWithNewLocation(location);
            locationManager.removeUpdates(locationListener);
        }
    }

    private final LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }

        public void onProviderEnabled(String provider) {
            LogUtils.dLog("huangzhenhui---NETWORK_PROVIDER");
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            LogUtils.dLog("huangzhenhui---NETWORK_PROVIDER");
        }
    };

    private void updateWithNewLocation(Location location) {
        String latLongString;
        double distance = -1;
        if (location != null) {
            double lat = location.getLatitude();
            double log = location.getLongitude();
            latLongString = "纬度:" + lat + "\n经度:" + log + "\n";
            txtAddressStateDa.setText(latLongString);
//            Geocoder gc = new Geocoder(YXTAttendanceActivity.this, Locale.getDefault());
//            try {
//                // 取得地址相关的一些信息\经度、纬度
//                List<Address> addresses = gc.getFromLocation(lat, log, 1);
//                if (addresses.size() > 0) {
//                    Address address = addresses.get(0);
//                    LogUtils.dLog("huangzhenhui-----"+Build.VERSION.SDK_INT + address.getAddressLine(0) + address.getAddressLine(2));
//                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH && Build.VERSION.SDK_INT < 23){
//                        latLongString = address.getAddressLine(2);
//                    }else {
//                        latLongString = address.getAddressLine(0);
//                    }
//
//                    distance = getDistance(attendenceConfig.getLongitude(), attendenceConfig.getLatitude(), log, lat);
//
//                    if (distance == -1 || distance > 500){
//                        txtAddressStateDa.setText("不在考勤范围 : ");
//                    }else {
//                        txtAddressStateDa.setText("已进入考勤范围 : ");
//                    }
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        } else {
            latLongString = "无法获取地理信息";
        }

    }

}
