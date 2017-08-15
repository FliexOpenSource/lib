package felix.libdemo;

import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.widget.ImageView;
import android.widget.TextView;

import felix.felixlib.Base.adp.BaseAdp;
import felix.felixlib.Base.adp.BaseVH;
import felix.felixlib.annotation.BindLayout;
import felix.felixlib.annotation.BindView;

/**
 * Created by huangmf on 2017/7/29.
 */
@BindLayout(R.layout.app_item)
public class AppAdp extends BaseAdp<ResolveInfo, AppAdp.AppVH> {
    public class AppVH extends BaseVH<ResolveInfo> {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_label)
        TextView mTvLabel;
        @BindView(R.id.tv_pkg)
        TextView mTvPkg;

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
