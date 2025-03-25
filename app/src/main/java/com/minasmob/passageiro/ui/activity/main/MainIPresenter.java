package com.minasmob.passageiro.ui.activity.main;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface MainIPresenter<V extends MainIView> extends MvpPresenter<V> {

    void getUserInfo();

    void logout(String id);

    void checkStatus();

    void getSavedAddress();

    void getProviders(HashMap<String, Object> params);

    void getNavigationSettings();

    void updateDestination(HashMap<String, Object> obj);
    void getBanner();


    void getAviso();

    void sos();
}
