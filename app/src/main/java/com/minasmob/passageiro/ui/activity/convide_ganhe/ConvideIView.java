package com.minasmob.passageiro.ui.activity.convide_ganhe;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.User;

public interface ConvideIView extends MvpView {
    void onSuccess(User user);

    void onError(Throwable e);

}
