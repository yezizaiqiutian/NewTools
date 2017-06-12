package com.gh.newtools.activity.luban;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.gh.advancedlubanlibrary.Luban;
import com.gh.advancedlubanlibrary.OnCompressListener;
import com.gh.advancedlubanlibrary.OnMultiCompressListener;
import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

//import java.util.function.Consumer;
//import me.shaohui.advancedluban.Luban;
//import me.shaohui.advancedluban.OnCompressListener;
//import me.shaohui.advancedluban.OnMultiCompressListener;

//import io.reactivex.functions.Consumer;

//import com.gh.advancedlibrary.Luban;
//import com.gh.advancedlibrary.OnCompressListener;
//import com.gh.advancedlibrary.OnMultiCompressListener;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public class AdvancedLubanActivity extends BaseActivity{
    private static final String TAG = "LubanExample";

    private static final int REQUEST_CODE = 1;

    private List<File> mFileList;

    private List<ImageView> mImageViews;

    private RadioGroup mMethodGroup;

    private RadioGroup mGearGroup;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AdvancedLubanActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luban);

        mFileList = new ArrayList<>();

        mImageViews = new ArrayList<>();
        mImageViews.add((ImageView) findViewById(R.id.image_1));
        mImageViews.add((ImageView) findViewById(R.id.image_2));
        mImageViews.add((ImageView) findViewById(R.id.image_3));
        mImageViews.add((ImageView) findViewById(R.id.image_4));
        mImageViews.add((ImageView) findViewById(R.id.image_5));
        mImageViews.add((ImageView) findViewById(R.id.image_6));
        mImageViews.add((ImageView) findViewById(R.id.image_7));
        mImageViews.add((ImageView) findViewById(R.id.image_8));
        mImageViews.add((ImageView) findViewById(R.id.image_9));

        mMethodGroup = (RadioGroup) findViewById(R.id.method_group);
        mGearGroup = (RadioGroup) findViewById(R.id.gear_group);

        findViewById(R.id.select_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiImageSelector.create().start(AdvancedLubanActivity.this, REQUEST_CODE);
            }
        });
        findViewById(R.id.compress_image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compressImage();
            }
        });
    }

    private void compressImage() {
        int gear;
        switch (mGearGroup.getCheckedRadioButtonId()) {
            case R.id.custom_gear:
                gear = Luban.THIRD_GEAR;
                break;
            case R.id.third_gear:
                gear = Luban.THIRD_GEAR;
                break;
            case R.id.first_gear:
                gear = Luban.FIRST_GEAR;
                break;
            default:
                gear = Luban.THIRD_GEAR;
        }
        switch (mMethodGroup.getCheckedRadioButtonId()) {
            case R.id.method_listener:
                if (mFileList.size() == 1) {
                    compressSingleListener(gear);
                } else {
                    compressMultiListener(gear);
                }
                break;
            case R.id.method_rxjava:
                if (mFileList.size() == 1) {
                    compressSingleRxJava(gear);
                } else {
                    compressMultiRxJava(gear);
                }
                break;
            default:
        }
    }


    private void compressSingleRxJava(int gear) {
        if (mFileList.isEmpty()) {
            return;
        }

        Luban.compress(mFileList.get(0), getFilesDir())
                .putGear(gear)
                .asObservable()
                .subscribe(new Consumer<File>() {
                    @Override
                    public void accept(File file) throws Exception {
                        Log.i("TAG", file.getAbsolutePath());
                        mImageViews.get(0).setImageURI(Uri.fromFile(file));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private void compressMultiRxJava(int gear) {
        if (mFileList.isEmpty()) {
            return;
        }
        Luban.compress(this, mFileList)
                .putGear(gear)
                .asListObservable()
                .subscribe(new Consumer<List<File>>() {
                    @Override
                    public void accept(List<File> files) throws Exception {
                        int size = files.size();
                        while (size-- > 0) {
                            mImageViews.get(size).setImageURI(Uri.fromFile(files.get(size)));
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    private void compressSingleListener(int gear) {
        if (mFileList.isEmpty()) {
            return;
        }
        Luban.compress(mFileList.get(0), getFilesDir())
                .putGear(gear)
                .launch(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "start");
                    }

                    @Override
                    public void onSuccess(File file) {
                        Log.i("TAG", file.getAbsolutePath());
                        mImageViews.get(0).setImageURI(Uri.fromFile(file));
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    private void compressMultiListener(int gear) {
        if (mFileList.isEmpty()) {
            return;
        }
        Luban.compress(this, mFileList)
                .putGear(gear)
                .launch(new OnMultiCompressListener() {
                    @Override
                    public void onStart() {
                        Log.i(TAG, "start");
                    }

                    @Override
                    public void onSuccess(List<File> fileList) {
                        int size = fileList.size();
                        while (size-- > 0) {
                            mImageViews.get(size).setImageURI(Uri.fromFile(fileList.get(size)));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && data != null) {
            mFileList.clear();
            List<String> path = data
                    .getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            for (String str : path) {
                mFileList.add(new File(str));
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
