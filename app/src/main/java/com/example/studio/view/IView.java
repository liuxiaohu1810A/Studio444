package com.example.studio.view;

import com.example.studio.User;

import java.util.List;

public interface IView {
    void onSuccess(List<User.DataBean.DatasBean> success);
    void onError(String error);
}
