package com.gh.newtools.activity.net;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gh.newtools.R;
import com.gh.newtools.base.BaseActivity;
import com.gh.rxretrofitlibrary.http.HttpManager;
import com.gh.rxretrofitlibrary.listener.HttpOnNextListener;
import com.gh.rxretrofitlibrary.utils.CookieDbUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 * 实用rxretrofitlibrary库
 * 联网请求
 */

public class NetActivity extends BaseActivity {

    @Bind(R.id.id_tv_msg)
    TextView id_tv_msg;

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, NetActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        ButterKnife.bind(this);

        CookieDbUtil.getInstance().deleteAll();
    }

    @OnClick(R.id.id_btn)
    public void onViewClicked() {

        SubjectPostApi postEntity = new SubjectPostApi(simpleOnNextListener, this);
        postEntity.setAll(true);
        HttpManager manager = HttpManager.getInstance();
        manager.doHttpDeal(postEntity);

    }

    //gh_联网模式选取
    //gh_模式一
//    HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<List<SubjectResulte>>() {
    //gh_模式二
//    HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<BaseResultEntity<List<SubjectResulte>>>() {
    //gh_模式三
    HttpOnNextListener simpleOnNextListener = new HttpOnNextListener<SubjectEntity>() {
        @Override
        //gh_联网模式选取
        //gh_模式一
//        public void onNext(List<SubjectResulte> subjects) {
        //gh_模式二
//        public void onNext(BaseResultEntity<List<SubjectResulte>> subjects) {
        //gh_模式三
        public void onNext(SubjectEntity subjects) {
            //gh_联网模式选取
            //gh_模式一
//            id_tv_msg.setText("网络返回：\n" + subjects.toString());
            //gh_模式二
            //gh_模式三
            id_tv_msg.setText("网络返回：\n" + subjects.getData().toString());
        }

        @Override
        public void onCacheNext(String cache, int cacheType) {
            /*缓存回调*/
            Gson gson = new Gson();
            //gh_联网模式选取
            //gh_模式一
            //gh_模式二
//            Type type = new TypeToken<BaseResultEntity<List<SubjectResulte>>>() {}.getType();
//            BaseResultEntity resultEntity = gson.fromJson(cache, type);
            //gh_模式三
            Type type = new TypeToken<SubjectEntity>() {}.getType();
            SubjectEntity resultEntity = gson.fromJson(cache, type);
            id_tv_msg.setText((cacheType == HAVENET_TOCACHE? "没有请求网络":"请求网络失败")+"缓存返回：\n" + resultEntity.getData().toString());
        }

        /*用户主动调用，默认是不需要覆写该方法*/
        @Override
        public void onError(Throwable e) {
            super.onError(e);
            id_tv_msg.setText("失败：\n" + e.toString());
        }

        /*用户主动调用，默认是不需要覆写该方法*/
        @Override
        public void onCancel() {
            super.onCancel();
            id_tv_msg.setText("取消请求");
        }
    };
}
