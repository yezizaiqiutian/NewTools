package com.gh.newtools.activity.device;

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
import com.gh.newtools.utils.DensityUtils;
import com.gh.newtools.utils.DeviceUtils;
import com.gh.newtools.utils.SystemUtils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: gh
 * time: 2017/6/27.
 * description:获取手机设备信息
 */

public class DeviceActivity extends BaseActivity {

    @Bind(R.id.id_tv_device)
    TextView id_tv_device;

    private static final int REQUEST_CODE_PERMISSION_SD = 100;
    private static final int REQUEST_CODE_SETTING = 300;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DeviceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_device);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.id_btn_getdevice)
    public void onViewClicked() {

        getPermission();
//        getDevice();

    }

    private void getPermission() {

        // 申请单个权限。
        AndPermission.with(this)
                .requestCode(REQUEST_CODE_PERMISSION_SD)
                .permission(Manifest.permission.READ_PHONE_STATE)
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
//        id_tv_msg.setText("权限申请成功");
//        uploadeDo();
        getDevice();
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

    private void getDevice(){
        StringBuilder sb = new StringBuilder();
        sb.append("AndroidID--" + DeviceUtils.getAndroidID(mContext) + "\n");
        sb.append("getIMEI--" + DeviceUtils.getIMEI(mContext) + "\n");
        sb.append("getIMSI--" + DeviceUtils.getIMSI(mContext) + "\n");
        sb.append("getWifiMacAddr--" + DeviceUtils.getWifiMacAddr(mContext) + "\n");
        //sb.append("getIP--" + DeviceUtils.getIP(c) + "\n");
        sb.append("getSerial--" + DeviceUtils.getSerial() + "\n");
        sb.append("getSIMSerial--" + DeviceUtils.getSIMSerial(mContext) + "\n");
        sb.append("getMNC--" + DeviceUtils.getMNC(mContext) + "\n");
        sb.append("getBuildBrand--" + DeviceUtils.getBuildBrand() + "\n");
        sb.append("getBuildHost--" + DeviceUtils.getBuildHost() + "\n");
        sb.append("getBuildTags--" + DeviceUtils.getBuildTags() + "\n");
        sb.append("getBuildTime--" + DeviceUtils.getBuildTime() + "\n");
        sb.append("getBuildUser--" + DeviceUtils.getBuildUser() + "\n");
        sb.append("getBuildVersionRelease--" + DeviceUtils.getBuildVersionRelease() + "\n");
        sb.append("getBuildVersionCodename--" + DeviceUtils.getBuildVersionCodename() + "\n");
        sb.append("getBuildVersionIncremental--" + DeviceUtils.getBuildVersionIncremental() + "\n");
        sb.append("getBuildVersionSDK--" + DeviceUtils.getBuildVersionSDK() + "\n");
        sb.append("getSupportedABIS--" + DeviceUtils.getSupportedABIS()[0] + DeviceUtils.getSupportedABIS()[1] + "\n");
        sb.append("getManufacturer--" + DeviceUtils.getManufacturer() + "\n");
        sb.append("getBootloader--" + DeviceUtils.getBootloader() + "\n");
        sb.append("getScreenDisplayID--" + DeviceUtils.getScreenDisplayID(mContext) + "\n");
        sb.append("getDisplayVersion--" + DeviceUtils.getDisplayVersion() + "\n");
        sb.append("getLanguage--" + DeviceUtils.getLanguage() + "\n");
        sb.append("getCountry--" + DeviceUtils.getCountry(mContext) + "\n");
        sb.append("getOSVersion--" + DeviceUtils.getOSVersion() + "\n");
        //sb.append("getGSFID--" + DeviceUtils.getGSFID(c) + "\n");
        sb.append("getBluetoothMAC--" + DeviceUtils.getBluetoothMAC(mContext) + "\n");
        sb.append("getPsuedoUniqueID--" + DeviceUtils.getPsuedoUniqueID() + "\n");
        sb.append("getFingerprint--" + DeviceUtils.getFingerprint() + "\n");
        sb.append("getHardware--" + DeviceUtils.getHardware() + "\n");
        sb.append("getProduct--" + DeviceUtils.getProduct() + "\n");
        sb.append("getDevice--" + DeviceUtils.getDevice() + "\n");
        sb.append("getBoard--" + DeviceUtils.getBoard() + "\n");
        sb.append("getRadioVersion--" + DeviceUtils.getRadioVersion() + "\n");
        sb.append("getUA--" + DeviceUtils.getUA(mContext) + "\n");
        sb.append("getDensity--" + DeviceUtils.getDensity(mContext) + "\n");
        //sb.append("getAccounts--" + DeviceUtils.getGoogleAccounts(c)[0] + "\n");
        sb.append("isRunningOnEmulator--" + SystemUtils.isRunningOnEmulator() + "\n");
        sb.append("isRooted--" + SystemUtils.isRooted() + "\n");
        sb.append("ScreenWidth x ScreenHeight--" + DensityUtils.getScreenW(mContext) + "x" + (DensityUtils.getScreenRealH(mContext)) + "\n");
        Log.i("ghost", "StatusBar:" + DensityUtils.getStatusBarH(mContext) + ",Nav:" + DensityUtils.getNavigationBarrH(mContext));
        id_tv_device.setText(sb);
    }

}
