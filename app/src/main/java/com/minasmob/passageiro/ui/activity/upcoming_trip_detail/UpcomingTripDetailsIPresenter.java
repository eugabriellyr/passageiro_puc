package com.minasmob.passageiro.ui.activity.upcoming_trip_detail;

import com.minasmob.passageiro.base.MvpPresenter;

public interface UpcomingTripDetailsIPresenter<V extends UpcomingTripDetailsIView> extends MvpPresenter<V> {

    void getUpcomingTripDetails(Integer requestId);
}
