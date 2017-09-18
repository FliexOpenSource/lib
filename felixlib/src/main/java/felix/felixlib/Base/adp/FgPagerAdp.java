package felix.felixlib.Base.adp;

import android.app.FragmentManager;
import android.content.Context;
import android.util.Log;

import java.util.List;

import felix.felixlib.Base.BaseFg;

/**
 * Created by huangmf on 2017/7/29.
 */

public class FgPagerAdp extends FragmentPagerAdapter {
    protected final String TAG = this.getClass().getName();
    protected Context mContext;
    private List<BaseFg> mBaseFgs;

    public FgPagerAdp(Context context, FragmentManager fm, List<BaseFg> fgs) {
        super(fm);
        mContext = context;
        mBaseFgs = fgs;
    }

    @Override
    public BaseFg getItem(int position) {
        if (mBaseFgs == null) {
            final String error = "dataSet is null,please check the data.";
            NullPointerException exception = new NullPointerException(error);
            Log.w(TAG, "getItem: dataSet is null,return null.", exception);
            return null;
        }
        if (position < 0 || position >= mBaseFgs.size()) {
            final String format = "the size of dataSet is %d and the index is %d.";
            final String error = String.format(format, mBaseFgs.size(), position);
            IndexOutOfBoundsException e = new IndexOutOfBoundsException(error);
            Log.w(TAG, "getItem: dataSet is null,return null.", e);
            return null;
        }
        return mBaseFgs.get(position);
    }


    @Override
    public int getCount() {
        if (mBaseFgs == null) {
            final String error = "dataSet is null,please check the data.";
            NullPointerException npe = new NullPointerException(error);
            Log.w(TAG, "getCount: dataSet is null,return 0.", npe);
            return 0;
        }
        return mBaseFgs.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        final BaseFg baseFg = getItem(position);
        if (baseFg == null) {
            final String error = "the index of " + position + " is null.";
            NullPointerException npe = new NullPointerException(error);
            Log.w(TAG, "getCount: dataSet is null,return null.", npe);
            return "";
        }
        return baseFg.getTitle();
    }
}
