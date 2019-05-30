package com.example.day02;

import com.example.day02.bean.User;
import com.example.day02.bean.Userlist;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServce {
     String urlban="http://www.wanandroid.com/";
     @GET("banner/json")
     Observable<User> BanRxJava();


     String url="http://www.wanandroid.com/";
     @GET("article/list/{page}/json")
     Observable<Userlist> textRxJava(@Path("page")int page);
}
