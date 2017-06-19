package com.gh.newtools.activity.net.file;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.gh.newtools.R;
import com.gh.newtools.activity.tab.UploadApi;
import com.gh.newtools.base.BaseActivity;
import com.gh.rxretrofitlibrary.http.HttpManager;
import com.gh.rxretrofitlibrary.listener.HttpOnNextListener;

import java.io.File;

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

        uploadeDo();

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
