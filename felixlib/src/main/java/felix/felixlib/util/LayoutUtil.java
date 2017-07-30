package felix.felixlib.util;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by huangmf on 2017/7/29.
 */

public class LayoutUtil {
    private final static String TAG=LayoutUtil.class.getSimpleName();
    private static Context sContext;
    private static SparseArray<View> sViewCaches;

    static void init() {
        Log.i(TAG, "init: ");
        sViewCaches = new SparseArray<>();
    }

    private static boolean isInit() {
        return sViewCaches != null;
    }

    public static void put(int layoutId, View view) {
        if (!isInit()) {
            init();
        }
        sViewCaches.put(layoutId, view);
    }

    public static View get(int layoutId, ViewGroup parent, boolean attachView) {
        if (!isInit()) {
            init();
        }
        View view = sViewCaches.get(layoutId);
        if (view == null) {
            view = LayoutInflater.from(sContext).inflate(layoutId, parent, attachView);
            sViewCaches.put(layoutId, view);
        }
        return view;
    }

    public static View get(int layoutId, ViewGroup parent) {
        return get(layoutId, parent, false);
    }

    public static View get(int layoutId) {
        return get(layoutId, null, false);
    }
}
