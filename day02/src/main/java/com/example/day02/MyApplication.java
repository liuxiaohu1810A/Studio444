package com.example.day02;

import android.app.Application;

import com.example.xts.greendaodemo.db.DaoMaster;
import com.example.xts.greendaodemo.db.DaoSession;

public class MyApplication extends Application {
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lxh.db");
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }
    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
