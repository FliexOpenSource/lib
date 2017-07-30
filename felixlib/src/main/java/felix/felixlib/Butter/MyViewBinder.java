package felix.felixlib.Butter;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import felix.felixlib.annotation.BindView;
import felix.felixlib.annotation.OnClick;

/**
 * Created by huangmf on 2017/7/29.
 */

public class MyViewBinder<T> implements ViewBinder<T> {
    protected final String TAG = this.getClass().getSimpleName();
    private Map<Class, Map<Method, ClickMethod>> mCache = new HashMap<>();

    @Override
    public void bind(Finder finder, T target, Object source) {
        bindField(finder, target, source);
        bindMethod(finder, target, source);
    }

    private void bindMethod(Finder finder, final T target, Object source) {
        Map<Method, ClickMethod> methodClickMethodMap = mCache.get(target.getClass());
        if (methodClickMethodMap == null) {
            methodClickMethodMap = new HashMap<>();
            Method[] methods = target.getClass().getDeclaredMethods();
            for (final Method method : methods) {
                OnClick onClick = method.getAnnotation(OnClick.class);
                if (onClick == null) {
                    continue;
                }
                final int[] id = onClick.value();
                if (id == null || id.length < 0) {
                    continue;
                }
                methodClickMethodMap.put(method, new ClickMethod(method, id));
            }
        }
        mCache.put(target.getClass(), methodClickMethodMap);
        for (Method method : methodClickMethodMap.keySet()) {
            ClickMethod clickMethod = methodClickMethodMap.get(method);
            clickMethod.bind(target, source, finder);
        }

    }

    private void bindField(Finder finder, T target, Object source) {
        Field[] fields = target.getClass().getFields();
        for (Field field : fields) {
            BindView bindView = field.getAnnotation(BindView.class);
            if (bindView == null) {
                continue;
            }
            final int id = bindView.value();
            field.setAccessible(true);
            final View view = finder.findView(source, id);

            try {
                field.set(target, view);
                final Class<?> viewCls = view.getClass();
                final Class<?> fieldCls = field.getType();
                if (!viewCls.equals(fieldCls)) {
                    final Resources rs = finder.getContext(source).getResources();
                    final String idName = rs.getResourceEntryName(id);
                    final String format = "field %s has type %s.%s , got %s from id %s.";
                    String error = String.format(format, field.getDeclaringClass(), field.getName(), fieldCls, viewCls, idName);
                    Log.w(TAG, "bind: ", new IllegalArgumentException(error));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "bind: Annotitation has single id,and assign the view to " + field.getName());
        }
    }

    @Override
    public void unbind(T target) {

    }
}
