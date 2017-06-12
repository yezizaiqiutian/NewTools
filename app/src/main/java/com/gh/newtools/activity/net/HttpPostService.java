package com.gh.newtools.activity.net;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * author: gh
 * time: 2017/6/8.
 * description:
 */

public interface HttpPostService {
//    @POST("AppFiftyToneGraph/videoLink")
//    Call<RetrofitEntity> getAllVedio(@Body boolean once_no);
//
//    @POST("AppFiftyToneGraph/videoLink")
//    Observable<RetrofitEntity> getAllVedioBy(@Body boolean once_no);

    @FormUrlEncoded
    @POST("AppFiftyToneGraph/videoLink")
        //gh_联网模式选取
        //gh_模式一
        //gh_模式二
//    Observable<BaseResultEntity<List<SubjectResulte>>> getAllVedioBys(@Field("once") boolean once_no);
        //gh_模式三
    Observable<SubjectEntity> getAllVedioBys(@Field("once") boolean once_no);
}
