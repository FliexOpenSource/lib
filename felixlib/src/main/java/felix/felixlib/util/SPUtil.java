package felix.felixlib.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import felix.felixlib.Base.BaseApp;

/**
 * Created by huangmf on 2017/7/29.
 */

public class SPUtil {
    private final static String TAG = SPUtil.class.getName();
    private static Context sContext;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor editor;

    private static boolean isInit() {
        return sp != null && editor != null;
    }

    public static void init() {
        Log.i(TAG, "init: ");
        if (sp == null) {
            Context context = BaseApp.getContext();
            final String name = context.getPackageName().replace(".", "_");
            sp = context.getSharedPreferences(name, 0);
            editor = sp.edit();
        }
    }

    public static void destory() {
        sp = null;
        editor = null;
    }

    /**
     * 保存整形到本地
     *
     * @param name
     * @param value
     */
    public static void putIntValue(String name, int value) {
        if (!isInit()) {
            init();
        }
        editor.putInt(name, value);
        editor.commit();
    }

    /**
     * 从本地读取整形数据
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static int getIntValue(String name, int defaultValue) {
        if (!isInit()) {
            return defaultValue;
        }
        return sp.getInt(name, defaultValue);
    }

    /**
     * 保存字符串到本地
     *
     * @param name
     * @param value
     */
    public static void putStringValue(String name, String value) {
        if (!isInit()) {
            init();
        }
        editor.putString(name, value);
        editor.commit();
    }

    /**
     * 从本地读取字符串
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static String getStringValue(String name, String defaultValue) {
        if (!isInit()) {
            return defaultValue;
        }
        return sp.getString(name, defaultValue);
    }

    /**
     * 保留布尔型到本地
     *
     * @param name
     * @param value
     */
    public static void putBooleanValue(String name, boolean value) {
        if (!isInit()) {
            init();
        }
        editor.putBoolean(name, value);
        editor.commit();
    }

    /**
     * 从本地读取布尔型数据
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static boolean getBooleanValue(String name, boolean defaultValue) {
        if (!isInit()) {
            return defaultValue;
        }
        return sp.getBoolean(name, defaultValue);
    }

    /**
     * 保存长整形到本地
     *
     * @param name
     * @param value
     */
    public static void putLongValue(String name, long value) {
        if (!isInit()) {
            init();
        }
        editor.putLong(name, value);
        editor.commit();
    }

    /**
     * 从本地读取长整型数据
     *
     * @param name
     * @param defaultValue
     * @return
     */
    public static Long getLongValue(String name, long defaultValue) {
        if (!isInit()) {
            return defaultValue;
        }
        return Long.valueOf(sp.getLong(name, defaultValue));
    }

    /**
     * 清除指定key值的数据
     *
     * @param context
     * @param name
     */
    public static void clearValue(Context context, String... name) {
        if (!isInit()) {
            init();
        }
        for (int i = 0; i < name.length; ++i) {
            editor.remove(name[i]);
        }
        editor.commit();
    }
}
