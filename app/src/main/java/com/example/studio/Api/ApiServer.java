package com.example.studio.Api;

import com.example.studio.User;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServer {
    String url="https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Observable<User> textRxJava();
}
