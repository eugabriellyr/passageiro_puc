package com.minasmob.passageiro.ui.activity.coupon;

import com.minasmob.passageiro.base.MvpPresenter;

public interface CouponIPresenter<V extends CouponIView> extends MvpPresenter<V> {
    void coupon();
}
