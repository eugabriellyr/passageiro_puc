package com.minasmob.passageiro.ui.activity.past_trip_detail;

import com.minasmob.passageiro.base.MvpPresenter;

public interface PastTripDetailsIPresenter<V extends PastTripDetailsIView> extends MvpPresenter<V> {

    void getPastTripDetails(Integer requestId);
}
