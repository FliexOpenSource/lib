package felix.felixlib.util;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import felix.felixlib.Base.BaseApp;

/**
 * Created by huangmf on 2017/7/29.
 */

public class SystemUtil {
    private static Context sContext;
    private static int mNavigationBarHeight = -1;

    private static boolean isInit() {
        return sContext != null;
    }

    static void init() {
        sContext = BaseApp.getContext();
    }

    /**
     * 获取导航栏高度
     *
     * @param activity
     * @return
     */
    public static int getNavigationBarHeight(Activity activity) {
        if (mNavigationBarHeight != -1) {
            return mNavigationBarHeight;
        }
        SystemBarConfig systemBarConfig = new SystemBarConfig(activity);
        mNavigationBarHeight = systemBarConfig.getNavigationBarHeight(activity);
        return mNavigationBarHeight;
    }

    /**
     * 获得版本号
     *
     * @return
     */
    public static String getVersionNum() {
        if (!isInit()) {
            init();
        }
        PackageManager pm = sContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(sContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pi.versionName;
    }
}
