package felix.felixlib.Base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import felix.felixlib.Butter.MyButter;
import felix.felixlib.util.AnnotationUtil;

/**
 * Created by huangmf on 2017/7/29.
 */

public class BaseCompatAty extends AppCompatActivity {
    protected final String TAG = this.getClass().getName();
    protected Context mContext;

    @Override
    @Deprecated
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        init();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        init();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        init();
    }

    private void init() {
        bindView();
        initData();
    }

    protected void initData() {

    }

    protected void bindView() {
        MyButter.bind(this);
    }

    protected <T extends View> T $(@IdRes int id) {
        return ((T) findViewById(id));
    }

    protected int getLayoutId() {
        return AnnotationUtil.getAnnotationLayoutId(this);
    }

    protected void startAty(Class cls) {
        startActivity(new Intent(mContext, cls));
    }
}
