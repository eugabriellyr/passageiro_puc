package com.minasmob.passageiro.ui.activity.forgot_password;


import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface ForgotPasswordIPresenter<V extends ForgotPasswordIView> extends MvpPresenter<V> {
    void resetPassword(HashMap<String, Object> parms);
}
