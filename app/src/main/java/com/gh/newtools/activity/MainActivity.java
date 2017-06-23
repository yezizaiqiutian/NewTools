package com.gh.newtools.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.gh.newtools.R;
import com.gh.newtools.activity.apkupload.ApkUploadActivity;
import com.gh.newtools.activity.exit.ExitActivity;
import com.gh.newtools.activity.glide.GlideActivity;
import com.gh.newtools.activity.glide.GlideDownLoadActivity;
import com.gh.newtools.activity.luban.AdvancedLubanActivity;
import com.gh.newtools.activity.net.NetActivity;
import com.gh.newtools.activity.net.file.UpLoadActivity;
import com.gh.newtools.activity.saveview.SaveViewActivity;
import com.gh.newtools.activity.select.SelectDateActivity;
import com.gh.newtools.activity.slidcloseactivity.SlidCloseActivity;
import com.gh.newtools.activity.tab.Tab2Activity;
import com.gh.newtools.activity.tab.TabActivity;
import com.gh.newtools.base.BaseActivity;
import com.gh.newtools.utils.T;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.id_btn1_net,
            R.id.id_btn2_exit,
            R.id.id_btn3_glide,
            R.id.id_btn4_glidedownload,
            R.id.id_btn5_select_date,
            R.id.id_btn6_saveview,
            R.id.id_btn7_luban,
            R.id.id_btn8_slidclose,
            R.id.id_btn9_apkupload,
            R.id.id_btn10_tab,
            R.id.id_btn11_tab2,
            R.id.id_btn12_fileup
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_btn1_net:
                NetActivity.actionStart(mContext);
                break;
            case R.id.id_btn2_exit:
                ExitActivity.actionStart(mContext);
                break;
            case R.id.id_btn3_glide:
                GlideActivity.actionStart(mContext);
                break;
            case R.id.id_btn4_glidedownload:
                GlideDownLoadActivity.actionStart(mContext);
                break;
            case R.id.id_btn5_select_date:
                SelectDateActivity.actionStart(mContext);
                break;
            case R.id.id_btn6_saveview:
                SaveViewActivity.actionStart(mContext);
                break;
            case R.id.id_btn7_luban:
                AdvancedLubanActivity.actionStart(mContext);
                break;
            case R.id.id_btn8_slidclose:
                SlidCloseActivity.actionStart(mContext);
                break;
            case R.id.id_btn9_apkupload:
                ApkUploadActivity.actionStart(mContext);
                break;
            case R.id.id_btn10_tab:
                TabActivity.actionStart(mContext);
                break;
            case R.id.id_btn11_tab2:
                Tab2Activity.actionStart(mContext);
                break;
            case R.id.id_btn12_fileup:
                UpLoadActivity.actionStart(mContext);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        T.S(mActivity,"MainActivityonDestroy");
    }

    /**
     * 返回不退出
     * 类似qq
     * @param keyCode
     * @param event
     * @return
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            moveTaskToBack(false);
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * 二次返回退出
     */
    private static Boolean isExit = false;
    private Boolean hasTask = false;
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isExit == false) {
                isExit = true;
                Toast.makeText(this, "再次按返回键退出程序", Toast.LENGTH_SHORT).show();
                if (!hasTask) {
                    tExit.schedule(task, 3000);
                }
            } else {
                isExit = false;
                finish();
            }
        }
        return false;
    }
}
