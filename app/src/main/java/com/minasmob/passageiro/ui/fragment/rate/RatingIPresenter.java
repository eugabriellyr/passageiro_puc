package com.minasmob.passageiro.ui.fragment.rate;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface RatingIPresenter<V extends RatingIView> extends MvpPresenter<V> {

    void rate(HashMap<String, Object> obj);
}
