package com.minasmob.passageiro.ui.activity.main;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.AddressResponse;
import com.minasmob.passageiro.data.network.model.DataResponse;
import com.minasmob.passageiro.data.network.model.Provider;
import com.minasmob.passageiro.data.network.model.SettingsResponse;
import com.minasmob.passageiro.data.network.model.User;

import java.util.List;

public interface MainIView extends MvpView {


    // TODO ELIEL LIMA BANNER E WHTS (67)999150264 SOMENTE ZAP
    void banner(User banner);

    void aviso(User aviso);

    void onSuccess(User user);

    void onSuccess(DataResponse dataResponse);

    void onDestinationSuccess(Object object);

    void onSuccessLogout(Object object);

    void onSuccess(AddressResponse response);

    void onSuccess(List<Provider> objects);

    void onError(Throwable e);

    void onSuccess(SettingsResponse response);

    void onSettingError(Throwable e);

}
