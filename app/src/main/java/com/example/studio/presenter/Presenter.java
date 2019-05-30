package com.example.studio.presenter;

import com.example.studio.User;
import com.example.studio.model.ICallBask;
import com.example.studio.model.IModel;
import com.example.studio.model.Modelmap;
import com.example.studio.view.IView;

import java.util.List;

public class Presenter implements IPresenter{
    private final Modelmap modelmap;
    private IView iView;

    public Presenter(IView iView) {
        modelmap = new Modelmap();
        this.iView = iView;
    }

    @Override
    public void getDataP() {
        if (modelmap!=null){
            modelmap.getDataM(new ICallBask() {
                @Override
                public void onSuccess(List<User.DataBean.DatasBean> success) {
                    iView.onSuccess(success);
                }

                @Override
                public void onError(String error) {
                       iView.onError(error);
                }
            });
        }
    }
}
