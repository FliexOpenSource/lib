package felix.felixlib.util;

/**
 * Created by huangmf on 2017/7/29.
 */

public class AInitUtil {
    /**
     * 预初始化
     */
    public static void init() {
        DentisityUtil.init();
        LayoutUtil.init();
        SPUtil.init();
        ViewUtil.init();
        ToastUtil.init();
        SystemUtil.init();
    }
}
