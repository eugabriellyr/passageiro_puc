package com.minasmob.passageiro.ui.activity.login;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.ForgotResponse;
import com.minasmob.passageiro.data.network.model.Token;

public interface LoginIView extends MvpView {
    void onSuccess(Token token);

    void onSuccess(ForgotResponse object);

    void onError(Throwable e);
}
