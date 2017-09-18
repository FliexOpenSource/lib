package felix.felixlib.Base.adp.eadp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.List;

import felix.felixlib.util.AnnotationUtil;

/**
 * Created by huangmf on 8/16/2017.
 */

public class BaseEAdp<C, T extends BaseEBean<C>, VHG extends BaseGVH<T>, VHC extends BaseCVH<C>> extends BaseExpandableListAdapter {
    private static final int VH_GROUP_INDEX = 2;
    private static final int CHILD_COUNT = 10000;
    private static final int VH_CHILD_INDEX = 3;
    protected final String TAG = this.getClass().getName();
    protected Context mContext;
    protected List<T> mCell;
    protected LayoutInflater mLayoutInflater;

    public BaseEAdp() {

    }

    public void init(Context context, List<T> cell) {
        mContext = context;
        mCell = cell;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        VHC vhg;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(getChildLayoutId(), parent, false);
            vhg = createChildVH();
            vhg.init(mContext, convertView);
            vhg.initData();
            convertView.setTag(vhg);
        } else {
            vhg = ((VHC) convertView.getTag());
        }
        final C child = getChild(groupPosition, childPosition);
        final int childCount = getChildrenCount(groupPosition);
        vhg.setData(child, groupPosition, childPosition, childCount);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        VHG vhg;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(getGroupLayoutId(), parent, false);
            vhg = createGroupVH();
            vhg.init(mContext, convertView);
            vhg.initData();
            convertView.setTag(vhg);
        } else {
            vhg = ((VHG) convertView.getTag());
        }
        vhg.setData(getGroup(groupPosition), groupPosition, getGroupCount());
        return convertView;
    }

    protected VHG createGroupVH() {
        Log.i(TAG, "createGroupVH: getVH with Annoattion.");
        return AnnotationUtil.getT(this, VH_GROUP_INDEX);
    }

    protected VHC createChildVH() {
        Log.i(TAG, "createChildVH: getVH with Annoattion.");
        return AnnotationUtil.getT(this, VH_CHILD_INDEX);
    }

    protected int getGroupLayoutId() {
        Log.i(TAG, "getGroupLayoutId: get layout id with Annotation.");
        return AnnotationUtil.getAnnotationLayoutIds(this)[0];
    }

    protected int getChildLayoutId() {
        Log.i(TAG, "getChildLayoutId: get layout id with Annotation.");
        return AnnotationUtil.getAnnotationLayoutIds(this)[1];
    }

    @Override
    public int getGroupCount() {
        if (mCell == null) {
            final String error = "dataSet is null,please check the data.";
            NullPointerException npe = new NullPointerException(error);
            Log.w(TAG, "getGroupCount: dataSet is null,return 0.", npe);
            return 0;
        }
        return mCell.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        T t = getGroup(groupPosition);
        if (t == null) {
            Log.w(TAG, "getChildrenCount: group is null.");
            return 0;
        }
        List<C> children = t.getChildren();
        if (children == null) {
            final String error = "children dataSet is null,please check the data.";
            NullPointerException npe = new NullPointerException(error);
            Log.w(TAG, "getChildrenCount: dataSet is null,return 0.", npe);
            return 0;
        }
        return children.size();
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition * CHILD_COUNT + childPosition;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition * CHILD_COUNT;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public C getChild(int groupPosition, int childPosition) {
        T t = getGroup(groupPosition);
        if (t == null) {
            Log.w(TAG, "getChild: group is null.");
            return null;
        }
        List<C> children = t.getChildren();
        if (children == null) {
            final String error = "children dataset is null,please check the data.";
            NullPointerException exception = new NullPointerException(error);
            Log.w(TAG, "getChild:children dataSet is null,return null.", exception);
            return null;
        }
        if (childPosition < 0 || childPosition >= children.size()) {
            final String format = "the size of dataSet is %d and the index is %d.";
            final String error = String.format(format, mCell.size(), groupPosition);
            IndexOutOfBoundsException e = new IndexOutOfBoundsException(error);
            Log.w(TAG, "getChild: illegal childPosition,return null.", e);
            return null;
        }
        return children.get(childPosition);
    }

    @Override
    public T getGroup(int groupPosition) {
        if (mCell == null) {
            final String error = "dataSet is null,please check the data.";
            NullPointerException exception = new NullPointerException(error);
            Log.w(TAG, "getGroup: dataSet is null,return null.", exception);
            return null;
        }
        if (groupPosition < 0 || groupPosition >= mCell.size()) {
            final String format = "the size of dataSet is %d and the index is %d.";
            final String error = String.format(format, mCell.size(), groupPosition);
            IndexOutOfBoundsException e = new IndexOutOfBoundsException(error);
            Log.w(TAG, "getGroup: illegal groupPosition,return null.", e);
            return null;
        }
        return mCell.get(groupPosition);
    }
}
