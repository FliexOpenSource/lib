package felix.libdemo.Bean;

import android.graphics.drawable.Drawable;

import felix.felixlib.Base.adp.eadp.BaseEBean;

/**
 * Created by huangmf on 8/16/2017.
 */

public class AppBean extends BaseEBean<AtyBean> {
    private CharSequence mLabel;
    private String mPkgName;
    private Drawable mIcon;

    public CharSequence getLabel() {
        return mLabel;
    }

    public void setLabel(CharSequence label) {
        mLabel = label;
    }

    public String getPkgName() {
        return mPkgName;
    }

    public void setPkgName(String pkgName) {
        mPkgName = pkgName;
    }

    public Drawable getIcon() {
        return mIcon;
    }

    public void setIcon(Drawable icon) {
        mIcon = icon;
    }
}
