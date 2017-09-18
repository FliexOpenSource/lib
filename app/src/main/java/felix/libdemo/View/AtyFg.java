package felix.libdemo.View;

import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import felix.felixlib.Base.BaseFg;
import felix.felixlib.Base.adp.AdpFactory;
import felix.felixlib.annotation.BindLayout;
import felix.felixlib.annotation.BindView;
import felix.libdemo.Adp.AppEadp;
import felix.libdemo.Bean.AppBean;
import felix.libdemo.Modul.AppModel;
import felix.libdemo.R;

/**
 * Created by huangmf on 8/16/2017.
 */
@BindLayout(R.layout.fg_aty)
public class AtyFg extends BaseFg {
    @BindView(R.id.elv_app)
    ExpandableListView mElvApp;
    private AppEadp mAppEadp;
    private List<AppBean> mAppBeen;

    @Override
    protected void initData(View view) {
        super.initData(view);
        mAppBeen = new ArrayList<>();
        mAppEadp = AdpFactory.createEADP(AppEadp.class, mContext, mAppBeen);
        mElvApp.setAdapter(mAppEadp);
        updateData();
    }

    private void updateData() {
        mAppBeen.clear();
        mAppBeen.addAll(AppModel.getAppBean());
        mAppEadp.notifyDataSetChanged();
    }
}
