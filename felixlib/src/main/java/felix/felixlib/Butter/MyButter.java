package felix.felixlib.Butter;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by huangmf on 2017/7/29.
 */

public class MyButter {
    static final Map<Class<?>, ViewBinder<Object>> BINDERS =
            new LinkedHashMap<Class<?>, ViewBinder<Object>>();

    public static void bind(Activity target) {
        bind(target, target, Finder.ACTIVITY);
    }

    public static void bind(Object object, View view) {
        bind(object, view, Finder.VIEW);
    }

    public static void bind(Object object, Dialog dialog) {
        bind(object, dialog, Finder.VIEW);
    }

    public static void bind(Object target, Object source, Finder finder) {
        try {
            ViewBinder viewBinder = findViewBinderForClass(target.getClass());
            viewBinder.bind(finder, target, source);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static ViewBinder<Object> findViewBinderForClass(Class<?> cls)
            throws IllegalAccessException, InstantiationException {
        ViewBinder<Object> viewBinder = BINDERS.get(cls);
        if (viewBinder != null) {
            return viewBinder;
        }
        viewBinder = new MyViewBinder();
        BINDERS.put(cls, viewBinder);
        return viewBinder;
    }


}
