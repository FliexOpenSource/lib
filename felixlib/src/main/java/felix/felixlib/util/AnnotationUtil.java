package felix.felixlib.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import felix.felixlib.annotation.BindLayout;

/**
 * Created by huangmf on 2017/7/29.
 */

public class AnnotationUtil {
    private static final String TAG = AnnotationUtil.class.getName();

    public static int getAnnotationLayoutId(Object obj) {
        int[] layoutIds = getAnnotationWithLayout(obj);
        if (layoutIds != null && layoutIds.length > 0) {
            return layoutIds[0];
        }
        return -1;
    }

    public static int[] getAnnotationLayoutIds(Object obj) {
        int[] layoutIds = getAnnotationWithLayout(obj);
        if (layoutIds != null && layoutIds.length == 2) {
            return layoutIds;
        }
        return new int[]{-1, -1};
    }

    private static int[] getAnnotationWithLayout(Object obj) {
        BindLayout bindLayout = obj.getClass().getAnnotation(BindLayout.class);
        if (bindLayout != null) {
            final int[] layoutId = bindLayout.value();
            return layoutId;
        }
        return null;
    }

    public static <T> T getT(Object o, int i) {
        Type[] types = ((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments();
        if (types == null || types.length <= i)
            return null;
        Object obj = null;
        try {
            Constructor constructor = ((Class) types[i]).getConstructor(o.getClass());
            obj = constructor.newInstance(o);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        if (obj == null) {
            try {
                obj = types[i].getClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
        }
        if (obj != null) {
            return ((T) obj);
        }
        return null;
    }

}
