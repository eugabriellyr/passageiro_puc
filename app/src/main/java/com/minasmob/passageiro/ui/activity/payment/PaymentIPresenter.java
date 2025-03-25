package com.minasmob.passageiro.ui.activity.payment;

import com.minasmob.passageiro.base.MvpPresenter;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface PaymentIPresenter<V extends PaymentIView> extends MvpPresenter<V> {

    void card();


}
