package felix.felixlib.Butter;

import android.view.View;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by huangmf on 2017/7/30.
 */

public class ClickMethod {
    private final static String TAG = ClickMethod.class.getSimpleName();
    private Method mMethod;
    private boolean mHasParam;
    private int[] mIds;
    private Finder mFinder;

    public ClickMethod(Method method, int[] ids) {
        checkIds(ids);
        mHasParam = checkMethod(method);
        mIds = new int[ids.length];
        for (int i = 0; i < ids.length; i++) {
            mIds[i] = ids[i];
        }
        mMethod = method;
        mMethod.setAccessible(true);
    }

    private void checkIds(int[] ids) {
        if (ids == null || ids.length <= 0) {
            final String error = "ids can't not be null or empty.";
            throw new IllegalArgumentException(error);
        }
    }

    private boolean checkMethod(Method method) {
        boolean hasParam;
        if (method == null) {
            final String error = "method can't be null.";
            throw new NullPointerException(error);
        }
        Type[] types = method.getParameterTypes();
        if (types == null || types.length == 0) {
            hasParam = false;
        } else if (types.length != 1) {
            final String error = "@OnClick methods can have at most 1 parameter.";
            throw new IllegalArgumentException(error);
        } else {
            if (!View.class.equals(types[0])) {
                final String error = "@OnClick methods can only has parameter with View.";
                throw new IllegalArgumentException(error);
            }
            hasParam = true;
        }
        Type returnType = method.getGenericReturnType();
        if (!returnType.equals(void.class)) {
            throw new IllegalStateException("return type should be void.");
        }
        return hasParam;
    }

    public void bind(final Object target, Object source, Finder finder) {
        View[] views = new View[mIds.length];
        for (int i = 0; i < mIds.length; i++) {
            views[i] = finder.findView(source, mIds[i]);
        }
        View.OnClickListener onClickListener;
        if (mHasParam) {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mMethod.invoke(target, v);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            };
        } else {
            onClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        mMethod.invoke(target);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            };
        }
        for (View view : views) {
            view.setOnClickListener(onClickListener);
        }

    }
}
