package com.gh.newtools.activity.net;

import com.gh.rxretrofitlibrary.api.BaseApi;
import com.gh.rxretrofitlibrary.api.BaseEntity;
import com.gh.rxretrofitlibrary.listener.HttpOnNextListener;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import io.reactivex.Observable;
import retrofit2.Retrofit;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public class SubjectPostApi extends BaseApi<BaseEntity> {

    //    接口需要传入的参数 可自定义不同类型
    private boolean all;
    /*任何你先要传递的参数*/
//    String xxxxx;
//    String xxxxx;
//    String xxxxx;
//    String xxxxx;

    public SubjectPostApi(HttpOnNextListener listener, RxAppCompatActivity rxAppCompatActivity) {
        super(listener, rxAppCompatActivity);
        setShowProgress(true);
        setCancel(true);
        setCache(true);
        setMothed("AppFiftyToneGraph/videoLink");
        setCookieNetWorkTime(60);
        setCookieNoNetWorkTime(24*60*60);
    }

    public boolean isAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    @Override
    public Observable getObservable(Retrofit retrofit) {
        HttpPostService service = retrofit.create(HttpPostService.class);
        return service.getAllVedioBys(isAll());
    }

}
