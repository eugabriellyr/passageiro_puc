package com.minasmob.passageiro.ui.activity.premio_bonus;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.User;

public interface PremioBonusIView extends MvpView {

    void onSuccess(User user);

    void onError(Throwable e);

}
