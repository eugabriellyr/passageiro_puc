package com.minasmob.passageiro.ui.activity.indicados;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.User;

public interface IndicadosIView extends MvpView {

    void onSuccess(User user);

    void onError(Throwable e);

}
