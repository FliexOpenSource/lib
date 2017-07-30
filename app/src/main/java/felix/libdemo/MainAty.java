package felix.libdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import felix.felixlib.Base.BaseAty;
import felix.felixlib.annotation.BindLayout;
import felix.felixlib.annotation.BindView;
import felix.felixlib.annotation.OnClick;
import felix.felixlib.util.ToastUtil;

@BindLayout(R.layout.aty_main)
public class MainAty extends BaseAty {
    @BindView(R.id.tv_update)
    TextView mTvUpdate;
    @BindView(R.id.lv_app)
    ListView mLvApp;
    private List<ResolveInfo> mResolveInfos;
    private AppAdp mAppAdp;

    @Override
    protected void initData() {
        super.initData();
        mResolveInfos = new ArrayList<>();
        mAppAdp = new AppAdp(mContext, mResolveInfos);
        mLvApp.setAdapter(mAppAdp);
//        mTvUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                update();
//            }
//        });
        mLvApp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ActivityInfo atyInfo = mAppAdp.getItem(position).activityInfo;
                Intent intent = new Intent();
                intent.setClassName(atyInfo.packageName, atyInfo.name);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.tv_update)
    private void onClick123(View view) {
        ToastUtil.showToast("hehe");
        update();
    }

    private void update() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        String time = sdf.format(new Date());
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> resolveInfos = getPackageManager().queryIntentActivities(intent, 0);
        mResolveInfos.clear();
        mResolveInfos.addAll(resolveInfos);
        mAppAdp.notifyDataSetChanged();
        mTvUpdate.setText("update in " + time + " and size is " + resolveInfos.size());
    }
}
