package com.minasmob.passageiro.ui.activity.login;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface LoginIPresenter<V extends LoginIView> extends MvpPresenter<V> {
    void login(HashMap<String, Object> obj);

    void forgotPassword(String email);
}
