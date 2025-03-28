package com.minasmob.passageiro.ui.activity.splash;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface SplashIPresenter<V extends SplashIView> extends MvpPresenter<V> {

    void services();

    void profile();

    void checkVersion(HashMap<String, Object> map);
}
