package com.example.studio.model;

import android.util.Log;

import com.example.studio.Api.ApiServer;
import com.example.studio.User;


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
    @Override
    public void getDataM(final ICallBask iCallBask) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiServer.url)
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<User> rxJava = apiServer.textRxJava();
        rxJava.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        List<User.DataBean.DatasBean> results = user.getData().getDatas();
                        iCallBask.onSuccess(results);
                        Log.d(TAG, "onNext: "+results);
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
