package com.minasmob.passageiro.ui.activity.past_trip_detail;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.Datum;

import java.util.List;

public interface PastTripDetailsIView extends MvpView {

    void onSuccess(List<Datum> pastTripDetails);

    void onError(Throwable e);
}
