package com.gh.rxretrofitlibrary.subscribers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import com.gh.rxretrofitlibrary.RxRetrofitApp;
import com.gh.rxretrofitlibrary.api.BaseApi;
import com.gh.rxretrofitlibrary.exception.HttpTimeException;
import com.gh.rxretrofitlibrary.http.cookie.CookieResulte;
import com.gh.rxretrofitlibrary.listener.HttpOnNextListener;
import com.gh.rxretrofitlibrary.utils.AppUtil;
import com.gh.rxretrofitlibrary.utils.CookieDbUtil;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

//import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * author: gh
 * time: 2017/6/8.
 * description:用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 */
public class ProgressSubscriber<T> implements Observer<T> {

     /*是否弹框*/
     private boolean showPorgress = true;
     /* 软引用回调接口*/
     private SoftReference<HttpOnNextListener> mSubscriberOnNextListener;
     /*软引用反正内存泄露*/
     private SoftReference<RxAppCompatActivity> mActivity;
     /*加载框可自己定义*/
     private ProgressDialog pd;
     /*请求数据*/
     private BaseApi api;

     private Disposable disposable;

     /**
      * 构造
      *
      * @param api
      */
     public ProgressSubscriber(BaseApi api) {
          this.api = api;
          this.mSubscriberOnNextListener = api.getListener();
          this.mActivity = new SoftReference<>(api.getRxAppCompatActivity());
          setShowPorgress(api.isShowProgress());
          if (api.isShowProgress()) {
               initProgressDialog(api.isCancel());
          }
     }

     /**
      * 初始化加载框
      */
     private void initProgressDialog(boolean cancel) {
          Context context = mActivity.get();
          if (pd == null && context != null) {
               pd = new ProgressDialog(context);
               pd.setMessage("加载中");
               pd.setCancelable(cancel);
               pd.setCanceledOnTouchOutside(false);
               if (cancel) {
                    pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                         @Override
                         public void onCancel(DialogInterface dialogInterface) {
                              if (mSubscriberOnNextListener.get() != null) {
                                   mSubscriberOnNextListener.get().onCancel();
                              }
                              onCancelProgress();
                         }
                    });
               }
          }
     }

     /**
      * 显示加载框
      */
     private void showProgressDialog() {
          if (!isShowPorgress()) return;
          Context context = mActivity.get();
          if (pd == null || context == null) return;
          if (!pd.isShowing()) {
               pd.show();
          }
     }

     /**
      * 隐藏加载框
      */
     private void dismissProgressDialog() {
          if (!isShowPorgress()) return;
          if (pd != null && pd.isShowing()) {
               pd.dismiss();
          }
     }

     @Override
     public void onSubscribe(@NonNull Disposable d) {
          disposable = d;
          showProgressDialog();
        /*缓存并且有网*/
          if (api.isCache() && AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
             /*获取缓存数据*/
               CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
               if (cookieResulte != null) {
                    long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                    if (time < api.getCookieNetWorkTime()) {
                         if (mSubscriberOnNextListener.get() != null) {
                              mSubscriberOnNextListener.get().onCacheNext(cookieResulte.getResulte(),HttpOnNextListener.HAVENET_TOCACHE);
                         }
                         onComplete();
                         disposable.dispose();
                    }
               }
          }
     }

     /**
      * 订阅开始时调用
      * 显示ProgressDialog
      */
//     @Override
//     public void onStart() {
//          showProgressDialog();
//        /*缓存并且有网*/
//          if (api.isCache() && AppUtil.isNetworkAvailable(RxRetrofitApp.getApplication())) {
//             /*获取缓存数据*/
//               CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(api.getUrl());
//               if (cookieResulte != null) {
//                    long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
//                    if (time < api.getCookieNetWorkTime()) {
//                         if (mSubscriberOnNextListener.get() != null) {
//                              mSubscriberOnNextListener.get().onCacheNext(cookieResulte.getResulte(),HttpOnNextListener.HAVENET_TOCACHE);
//                         }
//                         onCompleted();
//                         unsubscribe();
//                    }
//               }
//          }
//     }

     /**
      * 完成，隐藏ProgressDialog
      */
     @Override
     public void onComplete() {
          dismissProgressDialog();
     }

     /**
      * 对错误进行统一处理
      * 隐藏ProgressDialog
      *
      * @param e
      */
     @Override
     public void onError(final Throwable e) {
          dismissProgressDialog();
        /*需要緩存并且本地有缓存才返回*/
          if (api.isCache()) {
               Observable.just(api.getUrl()).subscribe(new Observer<String>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(Throwable e) {
                         errorDo(e);
                    }

                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                    /*获取缓存数据*/
                         CookieResulte cookieResulte = CookieDbUtil.getInstance().queryCookieBy(s);
                         if (cookieResulte == null) {
//                              throw new HttpTimeException("网络错误");
                              errorDo(e);
                              return;
                         }
                         long time = (System.currentTimeMillis() - cookieResulte.getTime()) / 1000;
                         if (time < api.getCookieNoNetWorkTime()) {
                              if (mSubscriberOnNextListener.get() != null) {
                                   mSubscriberOnNextListener.get().onCacheNext(cookieResulte.getResulte(),HttpOnNextListener.NONET_TOCACHE);
                              }
                         } else {
                              CookieDbUtil.getInstance().deleteCookie(cookieResulte);
//                              throw new HttpTimeException("网络错误");
                              errorDo(e);
                              return;
                         }
                    }
               });
          } else {
               errorDo(e);
          }
     }

//     @Override
//     public void onError(final Throwable e) {
//          dismissProgressDialog();
//        /*需要緩存并且本地有缓存才返回*/
//          if (api.isCache()) {
//               Observable.just(api.getUrl()).subscribe(new SafeO<String>(){});
//          } else {
//               errorDo(e);
//          }
//     }

     /*错误统一处理*/
     private void errorDo(Throwable e) {
          Context context = mActivity.get();
          if (context == null) return;
          if (e instanceof SocketTimeoutException) {
               Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
          } else if (e instanceof ConnectException) {
               Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
          } else if (e instanceof HttpTimeException){
               //自定义错误
               Toast.makeText(context, "错误" + e.getMessage(), Toast.LENGTH_SHORT).show();
          }
          else {
               //其他错误
               Toast.makeText(context, "错误" + e.getMessage(), Toast.LENGTH_SHORT).show();
          }
          if (mSubscriberOnNextListener.get() != null) {
               mSubscriberOnNextListener.get().onError(e);
          }
     }

     /**
      * 将onNext方法中的返回结果交给Activity或Fragment自己处理
      *
      * @param t 创建Subscriber时的泛型类型
      */
     @Override
     public void onNext(T t) {
          if (mSubscriberOnNextListener.get() != null) {
               mSubscriberOnNextListener.get().onNext(t);
          }
     }

     /**
      * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
      */
     public void onCancelProgress() {
//          if (!this.isUnsubscribed()) {
//               this.unsubscribe();
//          }
          if (!disposable.isDisposed())
          disposable.dispose();
     }


     public boolean isShowPorgress() {
          return showPorgress;
     }

     /**
      * 是否需要弹框设置
      *
      * @param showPorgress
      */
     public void setShowPorgress(boolean showPorgress) {
          this.showPorgress = showPorgress;
     }

}
