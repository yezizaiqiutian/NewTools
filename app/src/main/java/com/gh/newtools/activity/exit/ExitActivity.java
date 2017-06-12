package com.gh.newtools.activity.exit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;
import com.gh.newtools.utils.AppManager;
import com.gh.newtools.utils.T;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: gh
 * time: 2017/6/8.
 * description:退出应用工具使用
 */

public class ExitActivity extends BaseActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, ExitActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exit);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.id_btn1_exit, R.id.id_btn2_canclelogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_btn1_exit:
                AppManager.getAppManager().appExit(mContext);
                break;
            case R.id.id_btn2_canclelogin:
                AppManager.getAppManager().cancleLogin(mContext);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.S(mActivity,"ExitActivityonDestroy");
    }

}
