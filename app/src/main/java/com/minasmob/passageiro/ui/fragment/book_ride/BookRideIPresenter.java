package com.minasmob.passageiro.ui.fragment.book_ride;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;


public interface BookRideIPresenter<V extends BookRideIView> extends MvpPresenter<V> {
    void rideNow(HashMap<String, Object> obj);

    void getCouponList();
    void favorito();


    void add_porta_malas();
}
