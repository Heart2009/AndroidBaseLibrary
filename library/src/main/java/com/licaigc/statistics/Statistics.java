package com.licaigc.statistics;

import android.os.Build;

import com.licaigc.DeviceInfo;
import com.licaigc.ManifestUtils;
import com.licaigc.PackageUtils;
import com.licaigc.Transformer;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by walfud on 2016/7/7.
 */
public class Statistics {
    public static final String TAG = "Statistics";

    public static final int OS_ANDROID = 1;
    public static final int OS_IOS = 2;
    public static final int OS_UNKNOWN = 3;

    public static final int APP_ID_TALICAI = 1;
    public static final int APP_ID_GUIHUA = 2;
    public static final int APP_ID_TIMI = 3;
    public static final int APP_ID_JIJINDOU = 4;

    public static void reportStartupInfo(int appId) {
        final String REPORT_URL = "http://click.licaigc.cn/r/app";
        Map<String, String> params = Transformer.asMap(
                "mac", DeviceInfo.getMacAddress(),
                "imei", DeviceInfo.getImei(),
                "model", Build.MANUFACTURER,
                "androidid", DeviceInfo.getAndroidId(),
                "os", String.valueOf(OS_ANDROID),
                "version", PackageUtils.getVersionName(),
                "versioncode", String.valueOf(PackageUtils.getVersionCode()),
                "channel", ManifestUtils.getMeta("UMENG_CHANNEL"),
                "ip", DeviceInfo.getIpAddress(),
                "site", String.valueOf(APP_ID_TIMI)
        );
        final String param = Transformer.map2HttpGetParam(params, true, Transformer.MAP2HTTPGETPARAM_SKIP_EMPTY);
        Observable.<Void>just(null)
                .observeOn(Schedulers.io())
                .map(new Func1<Void, Void>() {
                    @Override
                    public Void call(Void aVoid) {
                        HttpURLConnection urlConnection = null;
                        try {
                            urlConnection = (HttpURLConnection) new URL(String.format("%s/?%s", REPORT_URL, param)).openConnection();
                            urlConnection.setRequestMethod("GET");
                            urlConnection.getResponseCode();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (urlConnection != null) {
                                urlConnection.disconnect();
                            }
                        }
                        return null;
                    }
                })
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }
                });
    }
}
