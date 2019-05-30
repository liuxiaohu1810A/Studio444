package com.example.studio.model;

import com.example.studio.User;

import java.util.List;

public interface ICallBask {
    void onSuccess(List<User.DataBean.DatasBean> success);
    void onError(String error);
}
