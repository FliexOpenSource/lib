package felix.libdemo.Modul;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import felix.libdemo.App;
import felix.libdemo.Bean.AppBean;
import felix.libdemo.Bean.AtyBean;

/**
 * Created by huangmf on 8/16/2017.
 */

public class AppModel {
    private final static String TAG = AppModel.class.getName();

    private static List<ResolveInfo> getAllAty() {
        Intent intent = new Intent();
        List<ResolveInfo> resolveInfos = App.getContext().getPackageManager().queryIntentActivities(intent, 0);
        return resolveInfos;
    }

    public static List<AppBean> getAppBean() {
        final long time = System.currentTimeMillis();
        final PackageManager pm = App.getContext().getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        List<ResolveInfo> resolveInfos = pm.queryIntentActivities(intent, 0);
        Map<String, AppBean> map = new HashMap<>();
        Log.i(TAG, "getAppBean: resolveInfo.size=" + resolveInfos.size());
        for (ResolveInfo resolveInfo : resolveInfos) {

            ActivityInfo atyInfo = resolveInfo.activityInfo;
            final String pkgName = atyInfo.packageName;
            AppBean appBean = map.get(pkgName);
            if (appBean == null) {
                appBean = new AppBean();
                appBean.setPkgName(pkgName);
                appBean.setLabel(atyInfo.loadLabel(pm));
                appBean.setIcon(atyInfo.loadIcon(pm));
                appBean.setChildren(new ArrayList<AtyBean>());
                Log.i(TAG, "getAppBean: add pkg="+pkgName);
            }
            AtyBean atyBean = new AtyBean();
            atyBean.setName(atyInfo.name);
            appBean.getChildren().add(atyBean);
            map.put(pkgName, appBean);
        }
        List<AppBean> appBeen = new ArrayList<>();
        Set<String> keySet = map.keySet();
        for (String s : keySet) {
            appBeen.add(map.get(s));
        }
        Log.i(TAG, "getAppBean: time=" + (System.currentTimeMillis() - time) + " size=" + appBeen.size());
        return appBeen;
    }
}
