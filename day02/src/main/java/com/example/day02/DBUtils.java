package com.example.day02;

import android.util.Log;

import com.example.day02.bean.DatasBean;
import com.example.xts.greendaodemo.db.DaoSession;
import com.example.xts.greendaodemo.db.DatasBeanDao;

public class DBUtils {
    private static final String TAG = "DBUtils";
    public static void insert(DatasBean datasBean){
        DaoSession daoSession = MyApplication.getDaoSession();
        DatasBean quey = quey(datasBean);
        if (quey==null){
            daoSession.insert(datasBean);
        }else{
            Log.d(TAG, "insert: 已存在");
        }
    }
    public static void delete(DatasBean datasBean){
        DaoSession daoSession = MyApplication.getDaoSession();
        DatasBean quey = quey(datasBean);
        if (quey!=null){
            daoSession.delete(datasBean);
        }else{
            Log.d(TAG, "insert: 不存在");
        }
        daoSession.delete(datasBean);
    }
    public static DatasBean quey(DatasBean datasBean){
        DaoSession daoSession = MyApplication.getDaoSession();
        return daoSession.queryBuilder(DatasBean.class)
                .where(DatasBeanDao.Properties.Title.eq(datasBean.getTitle()))
                .build()
                .unique();

    }
}
