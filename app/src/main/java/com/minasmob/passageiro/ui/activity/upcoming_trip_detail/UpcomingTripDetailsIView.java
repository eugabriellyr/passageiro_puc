package com.minasmob.passageiro.ui.activity.upcoming_trip_detail;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.Datum;

import java.util.List;

public interface UpcomingTripDetailsIView extends MvpView {

    void onSuccess(List<Datum> upcomingTripDetails);

    void onError(Throwable e);
}
