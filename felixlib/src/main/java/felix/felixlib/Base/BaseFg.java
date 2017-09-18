package felix.felixlib.Base;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import felix.felixlib.Butter.MyButter;
import felix.felixlib.util.AnnotationUtil;

/**
 * Created by huangmf on 2017/7/29.
 */

public class BaseFg extends Fragment {
    protected final String TAG = this.getClass().getName();
    protected Context mContext;
    private View mView = null;

    @Nullable
    @Override
    @Deprecated
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContext = this.getActivity();
        final int id = getLayoutId();
        if (id > 0) {
            View view = inflater.inflate(id, container, false);
            init(view);
            mView = view;
            return view;
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    private void init(View view) {
        bindView(view);
        initData(view);
    }

    protected void initData(View view) {

    }

    protected void bindView(View view) {
        MyButter.bind(this, view);
    }

    protected <T extends View> T $(@IdRes int id) {
        if (mView != null) {
            return ((T) mView.findViewById(id));
        }
        return ((T) ((Activity) mContext).findViewById(id));
    }

    protected int getLayoutId() {
        return AnnotationUtil.getAnnotationLayoutId(this);
    }

    public CharSequence getTitle() {
        return getClass().getSimpleName();
    }

    protected void startAty(Class cls) {
        startActivity(new Intent(mContext, cls));
    }
}
