package com.example.day02.view;

import com.example.day02.bean.DatasBean;
import com.example.day02.bean.User;

import java.util.List;

public interface IView {
    void onSuccess(List<DatasBean> success);
    void onSuccessBan(List<User.DataBean> successban);
    void onPage(int page);
    void onError(String error);
}
