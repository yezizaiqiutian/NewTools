package com.gh.newtools.activity.slidcloseactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: gh
 * time: 2017/6/9.
 * description:侧滑关闭Activity效果
 * 需要操作:
 * 1在Activity中调用
 * 2在style文件中配置透明
 * 3在每个布局文件中设置背景为白色
 */

public class SlidCloseActivity extends BaseActivity {

    private SlidrInterface attach;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SlidCloseActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_slidclose);
        ButterKnife.bind(this);

        //滑动关闭Activity
        attach = Slidr.attach(this);

    }

    @OnClick({R.id.id_btn_open, R.id.id_btn_close})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_btn_open:

                //解锁
                attach.unlock();
                break;
            case R.id.id_btn_close:
                //锁定手势，此时不能再拖动
                attach.lock();
                break;
        }
    }
}
