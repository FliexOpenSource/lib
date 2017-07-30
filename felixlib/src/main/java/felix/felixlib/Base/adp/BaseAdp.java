package felix.felixlib.Base.adp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import felix.felixlib.util.AnnotationUtil;

/**
 * Created by huangmf on 2017/7/29.
 */

public class BaseAdp<T, VH extends BaseVH<T>> extends BaseAdapter {
    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected List<T> mCell;
    protected LayoutInflater mLayoutInflater;

    public BaseAdp() {
    }

    public BaseAdp(Context context, List<T> cell) {
        mContext = context;
        mCell = cell;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (mCell == null) {
            final String error = "dataSet is null,please check the data.";
            NullPointerException npe = new NullPointerException(error);
            Log.w(TAG, "getCount: dataSet is null,return 0.", npe);
            return 0;
        }
        return mCell.size();
    }

    @Override
    public T getItem(int position) {
        if (mCell == null) {
            final String error = "dataSet is null,please check the data.";
            NullPointerException exception = new NullPointerException(error);
            Log.w(TAG, "getItem: dataSet is null,return null.", exception);
            return null;
        }
        if (position < 0 || position >= mCell.size()) {
            final String format = "the size of dataSet is %d and the index is %d.";
            final String error = String.format(format, mCell.size(), position);
            IndexOutOfBoundsException e = new IndexOutOfBoundsException(error);
            Log.w(TAG, "getItem: illegal position,return null.", e);
            return null;
        }
        return mCell.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected VH createVH(Context context, View view) {
        Log.i(TAG, "createVH: getVH with Annoattion.");
        return AnnotationUtil.getTWithADPParame(this, 1, context, view);
    }

    /**
     * 获取布局id，重写提高效率
     *
     * @return
     */
    protected int getLayoutId() {
        Log.i(TAG, "getLayoutId: get layout id with Annotation.");
        return AnnotationUtil.getAnnotationLayoutId(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VH vh;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(getLayoutId(), parent, false);
            vh = createVH(mContext, convertView);
            vh.initData();
            convertView.setTag(vh);
        } else {
            vh = ((VH) convertView.getTag());
        }
        vh.setData(getItem(position), position, getCount());
        return convertView;
    }
}
