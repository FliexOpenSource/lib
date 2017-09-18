package felix.libdemo.Adp;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

import felix.felixlib.Base.adp.eadp.BaseCVH;
import felix.felixlib.Base.adp.eadp.BaseEAdp;
import felix.felixlib.Base.adp.eadp.BaseGVH;
import felix.felixlib.annotation.BindLayout;
import felix.felixlib.annotation.BindView;
import felix.libdemo.Bean.AppBean;
import felix.libdemo.Bean.AtyBean;
import felix.libdemo.R;

/**
 * Created by huangmf on 8/16/2017.
 */
@BindLayout({R.layout.eadp_itm_app, R.layout.eadp_itm_aty})
public class AppEadp extends BaseEAdp<AtyBean, AppBean, AppEadp.AppVH, AppEadp.AtyVH> {

    class AppVH extends BaseGVH<AppBean> {
        @BindView(R.id.iv_icon)
        ImageView mIvIcon;
        @BindView(R.id.tv_pkg)
        TextView mTvPkg;

        @Override
        protected void setData(AppBean appBean, int position, int size) {
            super.setData(appBean, position, size);
            final Drawable icon = appBean.getIcon();
            if (icon != null)
                mIvIcon.setImageDrawable(icon);
            mTvPkg.setText(appBean.getLabel());
        }
    }

    class AtyVH extends BaseCVH<AtyBean> {
        @BindView(R.id.tv_aty_name)
        TextView mTvAtyName;

        @Override
        protected void setData(AtyBean atyBean, int groupPosition, int childPosition, int size) {
            super.setData(atyBean, groupPosition, childPosition, size);
            mTvAtyName.setText(atyBean.getName());
        }
    }
}
