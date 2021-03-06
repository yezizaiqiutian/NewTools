package com.gh.newtools.activity.net.file;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;
import com.gh.rxretrofitlibrary.http.HttpManager;
import com.gh.rxretrofitlibrary.listener.HttpOnNextListener;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * author: gh
 * time: 2017/6/15.
 * description:
 */

public class UpLoadActivity extends BaseActivity {

    @Bind(R.id.id_tv_msg)
    TextView id_tv_msg;

    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    private static final int REQUEST_CODE_SETTING = 300;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UpLoadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.id_btn)
    public void onViewClicked() {

        getPermission();

//        uploadeDo();

    }

    /**
     * 获取权限
     */
    private void getPermission() {
        // 申请单个权限。
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_SD)
                .permission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .permission(Manifest.permission.WRITE_CALENDAR)
                .callback(this)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
                // 这样避免用户勾选不再提示，导致以后无法申请权限。
                // 你也可以不设置。
                .rationale(new RationaleListener() {
                    @Override
                    public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(mActivity, rationale)
                                .show();
                    }
                })
                .start();
    }

    /**
     * <p>权限全部申请成功才会回调这个方法，否则回调失败的方法。</p>
     * 日历权限申请成功；使用@PermissionYes(RequestCode)注解。
     *
     * @param grantedPermissions AndPermission回调过来的申请成功的权限。
     */
    @PermissionYes(REQUEST_CODE_PERMISSION_SD)
    private void getCalendarYes(@NonNull List<String> grantedPermissions) {
        Toast.makeText(this, "有权限", Toast.LENGTH_SHORT).show();
        //有权限,进行网络请求
        id_tv_msg.setText("权限申请成功");
        uploadeDo();
    }

    /**
     * <p>只要有一个权限申请失败就会回调这个方法，并且不会回调成功的方法。</p>
     * 日历权限申请失败，使用@PermissionNo(RequestCode)注解。
     *
     * @param deniedPermissions AndPermission回调过来的申请失败的权限。
     */
    @PermissionNo(REQUEST_CODE_PERMISSION_SD)
    private void getCalendarNo(@NonNull List<String> deniedPermissions) {
        Toast.makeText(this, "没权限", Toast.LENGTH_SHORT).show();
        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            // 第一种：用默认的提示语。
            AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING).show();

            // 第二种：用自定义的提示语。
//             AndPermission.defaultSettingDialog(this, REQUEST_CODE_SETTING)
//                     .setTitle("权限申请失败")
//                     .setMessage("我们需要的一些权限被您拒绝或者系统发生错误申请失败，请您到设置页面手动授权，否则功能无法正常使用！")
//                     .setPositiveButton("好，去设置")
//                     .show();

//            第三种：自定义dialog样式。
//            SettingService settingService = AndPermission.defineSettingDialog(this, REQUEST_CODE_SETTING);
//            你的dialog点击了确定调用：
//            settingService.execute();
//            你的dialog点击了取消调用：
//            settingService.cancel();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SETTING: {
                Toast.makeText(this, "用户从设置回来了", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    private void uploadeDo(){
        File file = new File("/sdcard/#gh/111.jpg");
//      File file=new File("/storage/emulated/0/Download/11.jpg");
        RequestBody requestBody=RequestBody.create(MediaType.parse("image/jpeg"),file);
        MultipartBody.Part part= MultipartBody.Part.createFormData("file_name", file.getName(),requestBody /*new ProgressRequestBody(requestBody,
              new UploadProgressListener() {c:

          @Override
          public void onProgress(long currentBytesCount, long totalBytesCount) {
              Log.d("gh", "上传进度:" + currentBytesCount + "/" + totalBytesCount);
              id_tv_msg.setText("提示:上传中"+currentBytesCount+"/"+totalBytesCount);
//              progressBar.setMax((int) totalBytesCount);
//              progressBar.setProgress((int) currentBytesCount);
          }
      })*/);
        UploadApi uplaodApi = new UploadApi(httpOnNextListener,this);
        uplaodApi.setPart(part);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(uplaodApi);
    }

    /**
     * 上传回调
     */
    HttpOnNextListener httpOnNextListener=new HttpOnNextListener<UploadResulte>() {
        @Override
        public void onNext(UploadResulte o) {
            id_tv_msg.setText("成功");
            Log.d("gh", "上传成功");
//            Glide.with(MainActivity.this).load(o.getHeadImgUrl()).skipMemoryCache(true).into(img);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            Log.d("gh", "上传失败");
            id_tv_msg.setText("失败："+e.toString());
        }

        @Override
        public void onCacheNext(String string,int q) {
            id_tv_msg.setText("失败：");
            Log.d("gh", "上传缓存");
        }

        @Override
        public void onNext(Observable observable) {
            super.onNext(observable);
            Log.d("gh", "上传next");
        }

        @Override
        public void onCancel() {
            super.onCancel();
            id_tv_msg.setText("上传取消：");
            Log.d("gh", "上传取消");
        }
    };

}
