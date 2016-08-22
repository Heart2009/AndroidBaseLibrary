package com.licaigc;

import java.util.Map;

/**
 * Created by walfud on 2016/8/19.
 */
public class Constants {
    public static final String TAG = "Constants";

    // 平台
    public static final int OS_UNKNOWN = 0;
    public static final int OS_ANDROID = 1;
    public static final int OS_IOS     = 2;

    // 应用 Id
    public static final int APP_ID_UNKNOWN     = 0;
    public static final int APP_ID_TALICAI     = 1;
    public static final int APP_ID_GUIHUA      = 2;
    public static final int APP_ID_TIMI        = 3;
    public static final int APP_ID_JIJINDOU    = 4;
    public static int APP_ID;               // 当前应用 id

    // 网络类型
    public static final int NETWORK_NONE    = 0;
    public static final int NETWORK_WIFI    = 1;
    public static final int NETWORK_2G      = 2;
    public static final int NETWORK_3G      = 3;
    public static final int NETWORK_4G      = 4;
    public static final int NETWORK_5G      = 5;

    // internal
    private static final Map<String, Integer> PKG_ID = Transformer.asMap(
            "com.talicai.talicaiclient",        Constants.APP_ID_TALICAI,
            "com.haoguihua.app",                Constants.APP_ID_GUIHUA,
            "com.talicai.timiclient",           Constants.APP_ID_TIMI,
            "com.jijindou.android.fund",        Constants.APP_ID_JIJINDOU,

            "com.licaigc.androidbaselibrary",   Constants.APP_ID_TALICAI           // Debug
    );

    /**
     * @return 根据当前包名返回整数 id. 如果该应用不存在于 {@link #PKG_ID} 中, 则返回 0.
     */
    static int getAppId() {
        String pkgName = AndroidBaseLibrary.getContext().getPackageName();
        return PKG_ID.containsKey(pkgName) ? PKG_ID.get(pkgName) : 0;
    }
}
