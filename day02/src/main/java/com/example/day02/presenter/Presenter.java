package com.example.day02.presenter;

import com.example.day02.bean.DatasBean;
import com.example.day02.bean.User;
import com.example.day02.model.ICallBask;
import com.example.day02.model.Modelmap;
import com.example.day02.view.IView;

import java.util.List;

public class Presenter implements IPresenter {
    private final Modelmap modelmap;
    private IView iView;

    public Presenter(IView iView) {
        modelmap = new Modelmap();
        this.iView = iView;
    }

    @Override
    public void onDataP() {
        if (modelmap != null) {
            modelmap.onDataM(new ICallBask() {
                @Override
                public void onSuccess(List<DatasBean> success) {
                    iView.onSuccess(success);
                }

                @Override
                public void onSuccessBan(List<User.DataBean> successban) {
                    iView.onSuccessBan(successban);
                }

                @Override
                public void onPage(int page) {
                    iView.onPage(page);
                }

                @Override
                public void onError(String error) {
                    iView.onError(error);
                }
            });
        }
    }
}
