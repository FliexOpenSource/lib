package felix.felixlib.Base.adp.adp;

import android.content.Context;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;

import felix.felixlib.Butter.MyButter;

/**
 * Created by huangmf on 2017/7/29.
 */

public class BaseVH<T> {
    protected final String TAG = this.getClass().getName();
    protected Context mContext;
    private View mView;

    public BaseVH() {
    }
    public void init(Context context, View view) {
        mContext = context;
        mView = view;
        bindView(view);
    }

    protected void bindView(View view) {
        Log.i(TAG, "bindView: bindView with Annotation.");
        MyButter.bind(this, view);
    }

    protected <V extends View> V $(@IdRes int id) {
        return ((V) mView.findViewById(id));
    }

    protected void initData() {

    }

    protected void setData(T t, int position, int size) {

    }
}
