package com.example.day02.model;

import android.util.Log;

import com.example.day02.ApiServce;
import com.example.day02.bean.DatasBean;
import com.example.day02.bean.User;
import com.example.day02.bean.Userlist;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Modelmap implements IModel{
    private static final String TAG = "Modelmap";
    private int page=0;

    @Override
    public void onDataM(final ICallBask iCallBask) {
        //Banner
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiServce.urlban)
                .build();
        ApiServce apiServce = retrofit.create(ApiServce.class);
        Observable<User> rxJava = apiServce.BanRxJava();
        rxJava.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        List<User.DataBean> data = user.getData();
                        iCallBask.onSuccessBan(data);
                        Log.d(TAG, "onNext: "+data);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallBask.onError(e.getMessage());
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        //数据列表
        Retrofit retrofit2 = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(ApiServce.url)
                .build();
        ApiServce apiServce2 = retrofit2.create(ApiServce.class);
        Observable<Userlist> rxJava2 = apiServce2.textRxJava(page);
        rxJava2.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Userlist>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Userlist userlist) {
                        List<DatasBean> datas = userlist.getData().getDatas();
                        iCallBask.onSuccess(datas);
                        iCallBask.onPage(page);
                        Log.d(TAG, "onNext: "+datas);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iCallBask.onError(e.getMessage());
                        Log.d(TAG, "onError: "+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
