package com.minasmob.passageiro.ui.fragment.service;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface ServiceTypesIPresenter<V extends ServiceTypesIView> extends MvpPresenter<V> {

    void services();

    void profile();

    void rideNow(HashMap<String, Object> obj);

}
