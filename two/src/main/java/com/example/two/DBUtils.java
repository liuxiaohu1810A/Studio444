package com.example.two;

import com.example.xts.greendaodemo.db.DaoSession;

import java.util.List;

public class DBUtils {
    public static void insert(User user){
        DaoSession daoSession = MyApplication.getDaoSession();
        daoSession.insert(user);
    }
    public static List<User> select(){
        DaoSession daoSession = MyApplication.getDaoSession();
        return daoSession.loadAll(User.class);
    }
}
