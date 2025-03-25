package com.minasmob.passageiro.ui.activity.register;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.RegisterResponse;
import com.minasmob.passageiro.data.network.model.SettingsResponse;

public interface RegisterIView extends MvpView {

    void onSuccess(SettingsResponse response);

    void onSuccess(RegisterResponse object);

    void onSuccess(Object object);

    void onSuccessPhoneNumber(Object object);

    void onVerifyPhoneNumberError(Throwable e);

    void onError(Throwable e);

    void onVerifyEmailError(Throwable e);
}
