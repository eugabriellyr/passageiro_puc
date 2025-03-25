package com.minasmob.passageiro.ui.fragment.book_ride;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.AddPortaMalas;
import com.minasmob.passageiro.data.network.model.Fav;
import com.minasmob.passageiro.data.network.model.PromoResponse;


public interface BookRideIView extends MvpView {


    void onSuccess(AddPortaMalas adicionais);

    void onSuccess(Object object);

    void onError(Throwable e);

    void onSuccessCoupon(PromoResponse promoResponse);
    void onSuccess(Fav fav);

}
