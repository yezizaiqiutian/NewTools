package com.gh.newtools.activity.appmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;
import com.gh.newtools.utils.L;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: gh
 * time: 2017/6/27.
 * Github:
 * description:
 */

public class AppManagerActivity extends BaseActivity implements AppContract.View {

    @Bind(R.id.id_rv_applist)
    RecyclerView id_rv_applist;

    private AppContract.Presenter mPresenter;
    private ProgressDialog mDialog;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AppManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_manager);
        ButterKnife.bind(this);
        new AppPresenter(this);
    }

    @Override
    protected void initView() {
        id_rv_applist.setLayoutManager(new GridLayoutManager(this, 4));
        id_rv_applist.setHasFixedSize(true);
        initProgress();
    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mPresenter.getData();
            }
        }).start();
    }

    private void initProgress() {
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("加载中");
        mDialog.setCancelable(true);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
    }

    @Override
    public void setPresenter(AppContract.Presenter presenter) {
//        mPresenter = checkNotNull(presenter);如果为空会报异常方便定位错误
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.cancel();
    }

    @Override
    public void referData(List<AppBean> list) {
        for (AppBean bean : list) {
            L.i("gh", "app: " + bean.toString());
        }
        id_rv_applist.setAdapter(new AppRecyAdapter(this, list));
    }
}
