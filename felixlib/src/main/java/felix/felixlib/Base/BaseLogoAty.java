package felix.felixlib.Base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import felix.felixlib.util.AInitUtil;

/**
 * Created by huangmf on 2017/7/29.
 */

public abstract class BaseLogoAty extends Activity {
    protected static Handler sHandler;
    protected Context mContext;
    //public static Activity sActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId());
        initData();
//        AInitUtil.init(mContext);
//        afterInitCompelete();
//        if (true)
//            return;
        sHandler = new Handler(Looper.myLooper());
        sHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                final long time = System.currentTimeMillis();
                initData();
                final long dtime = System.currentTimeMillis() - time;
                final int waitTime = getTime();
                if (dtime < waitTime) {
                    try {
                        Thread.sleep(waitTime - dtime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                afterInitCompelete();
            }
        }, 0);
    }

    protected void initData() {
        AInitUtil.init();
    }

    protected abstract int getLayoutId();

    protected abstract Class getClsAty();

    protected void afterInitCompelete() {
        startActivity(new Intent(mContext, getClsAty()));
        finish();
    }

    protected int getTime() {
        return 3000;
    }
}
