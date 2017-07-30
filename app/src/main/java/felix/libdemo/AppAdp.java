package felix.libdemo;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import felix.felixlib.Base.adp.BaseAdp;
import felix.felixlib.Base.adp.BaseVH;
import felix.felixlib.annotation.BindLayout;
import felix.felixlib.annotation.BindView;

/**
 * Created by huangmf on 2017/7/29.
 */
@BindLayout(R.layout.app_item)
public class AppAdp extends BaseAdp<ResolveInfo, AppAdp.AppVH> {
    public AppAdp(Context context, List<ResolveInfo> cell) {
        super(context, cell);
    }

    class AppVH extends BaseVH<ResolveInfo> {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_label)
        TextView mTvLabel;
        @BindView(R.id.tv_pkg)
        TextView mTvPkg;

        public AppVH(Context context, View view) {
            super(context, view);
        }

        @Override
        protected void setData(ResolveInfo resolveInfo, int position, int size) {
            super.setData(resolveInfo, position, size);
            ActivityInfo atyInfo = resolveInfo.activityInfo;
            mIvIcon.setImageDrawable(atyInfo.loadIcon(mContext.getPackageManager()));
            mTvLabel.setText(atyInfo.loadLabel(mContext.getPackageManager()));
            mTvPkg.setText(atyInfo.packageName);
        }
    }
}
