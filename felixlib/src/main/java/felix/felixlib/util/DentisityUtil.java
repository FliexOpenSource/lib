package felix.felixlib.util;

import android.content.Context;
import android.util.Log;

import felix.felixlib.Base.BaseApp;

/**
 * Created by huangmf on 2017/7/29.
 */

public class DentisityUtil {
    private final static String TAG = DentisityUtil.class.getSimpleName();
    private static float sDensity = -1;
    private static float sScaledDensity = -1;

    static void init() {
        Log.i(TAG, "init: ");
        Context context = BaseApp.getContext();
        sDensity = context.getResources().getDisplayMetrics().density;
        sScaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
    }

    private static boolean isInit() {
        return sDensity != -1 || sScaledDensity != -1;
    }

    /**
     * 将dp值转换为px值，保证尺寸大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2dp(float pxValue) {
        if (!isInit()) {
            init();
        }
        return (int) (pxValue / sDensity + 0.5f);
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * @return
     */
    public static int dp2px(float dipValue) {
        if (!isInit()) {
            init();
        }
        return (int) (dipValue * sDensity + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(float pxValue) {
        if (!isInit()) {
            init();
        }
        return (int) (pxValue / sScaledDensity + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(float spValue) {
        if (!isInit()) {
            init();
        }
        return (int) (spValue * sScaledDensity + 0.5f);
    }
}
