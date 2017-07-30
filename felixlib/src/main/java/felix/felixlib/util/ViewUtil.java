package felix.felixlib.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;

import felix.felixlib.Base.BaseApp;

/**
 * Created by huangmf on 2017/7/29.
 */

public class ViewUtil {
    private static Context sContext;
    private static Resources sResources;

    private static boolean isInit() {
        return sContext != null && sResources != null;
    }

    static void init() {
        sContext = BaseApp.getContext();
        sResources = sContext.getResources();
    }

    /**
     * 根据id获取drawable
     *
     * @param id
     * @return
     */
    public static Drawable getDrawableById(@DrawableRes int id) {
        if (!isInit()) {
            init();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return sResources.getDrawable(id, sContext.getTheme());
        } else {
            return sContext.getDrawable(id);
        }
    }
}
