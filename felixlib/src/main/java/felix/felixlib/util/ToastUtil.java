package felix.felixlib.util;

import android.content.Context;
import android.widget.Toast;

import felix.felixlib.Base.BaseApp;

/**
 * Created by huangmf on 2017/7/29.
 */

public class ToastUtil {
    private static Toast mToast = null;
    private final static int ShowTime = Toast.LENGTH_SHORT;

    static void init() {
        Context context = BaseApp.getContext();
        mToast = Toast.makeText(context, "", ShowTime);
    }

    private static boolean isInit() {
        return mToast != null;
    }

    /**
     * 显示指定字符串ID的toast
     *
     * @param contentId
     */
    public static void showToast(int contentId) {
        if (!isInit()) {
            init();
        }
        mToast.setText(contentId);
        mToast.show();
    }

    /**
     * 显示指定字符串
     *
     * @param content
     */
    public static void showToast(String content) {
        if (!isInit()) {
            init();
        }
        mToast.setText(content);
        mToast.show();
    }

    /**
     * 关闭toast
     */
    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
