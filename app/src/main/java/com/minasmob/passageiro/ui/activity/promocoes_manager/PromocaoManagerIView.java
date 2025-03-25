package com.minasmob.passageiro.ui.activity.promocoes_manager;

import com.minasmob.passageiro.base.MvpView;
//import com.setemix.passageiro.data.network.model.NotificationManager;
import com.minasmob.passageiro.data.network.model.PromocaoManager;

import java.util.List;

public interface PromocaoManagerIView extends MvpView {





    void onSuccess(List<PromocaoManager> notificationManager);

    int getItemCount();

    void onError(Throwable e);

}