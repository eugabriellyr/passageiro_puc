package com.minasmob.passageiro.ui.fragment.service_flow;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.User;

public interface ServiceFlowIView extends MvpView {

    void onSuccess(User user);

    void onUpdateSuccess(User user);
}
