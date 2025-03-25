package com.minasmob.passageiro.ui.activity.splash;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.CheckVersion;
import com.minasmob.passageiro.data.network.model.Service;
import com.minasmob.passageiro.data.network.model.User;

import java.util.List;

public interface SplashIView extends MvpView {

    void onSuccess(List<Service> serviceList);

    void onSuccess(User user);

    void onError(Throwable e);

    void onSuccess(CheckVersion checkVersion);
}
