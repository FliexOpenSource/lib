package felix.libdemo.View;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import felix.felixlib.Base.BaseAty;
import felix.felixlib.Base.BaseFg;
import felix.felixlib.Base.adp.FgPagerAdp;
import felix.felixlib.annotation.BindLayout;
import felix.felixlib.annotation.BindView;
import felix.libdemo.R;

@BindLayout(R.layout.aty_main)
public class MainAty extends BaseAty {
    @BindView(R.id.vp_main)
    ViewPager mVpMain;
    @BindView(R.id.tl_indictor)
    TabLayout mTlIndictor;
    FgPagerAdp mFgPagerAdp;

    @Override
    protected void initData() {
        super.initData();
        List<BaseFg> baseFgs = new ArrayList<>();
        baseFgs.add(new AppFg());
        baseFgs.add(new AtyFg());
        FragmentManager fm = getFragmentManager();
        mFgPagerAdp = new FgPagerAdp(mContext, fm, baseFgs);
        mVpMain.setAdapter(mFgPagerAdp);
        mTlIndictor.setupWithViewPager(mVpMain);
    }
}
