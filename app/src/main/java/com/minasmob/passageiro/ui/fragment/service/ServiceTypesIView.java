package com.minasmob.passageiro.ui.fragment.service;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.Service;
import com.minasmob.passageiro.data.network.model.User;

import java.util.List;

public interface ServiceTypesIView extends MvpView {

    void onSuccess(User user);

    void onSuccess(List<Service> serviceList);

    void onError(Throwable e);

    void onSuccess(Object object);
}
