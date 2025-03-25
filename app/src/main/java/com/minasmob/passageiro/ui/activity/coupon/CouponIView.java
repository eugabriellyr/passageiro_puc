package com.minasmob.passageiro.ui.activity.coupon;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.PromoResponse;

public interface CouponIView extends MvpView {
    void onSuccess(PromoResponse object);

    void onError(Throwable e);
}
