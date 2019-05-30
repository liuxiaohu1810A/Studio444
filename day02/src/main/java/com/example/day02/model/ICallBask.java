package com.example.day02.model;

import com.example.day02.bean.DatasBean;
import com.example.day02.bean.User;

import java.util.List;

public interface ICallBask {
    void onSuccess(List<DatasBean> success);
    void onSuccessBan(List<User.DataBean> successban);
    void onPage(int page);
    void onError(String error);
}
