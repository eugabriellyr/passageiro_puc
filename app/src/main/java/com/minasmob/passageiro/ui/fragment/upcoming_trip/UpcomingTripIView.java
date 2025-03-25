package com.minasmob.passageiro.ui.fragment.upcoming_trip;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.Datum;

import java.util.List;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface UpcomingTripIView extends MvpView {
    void onSuccess(List<Datum> datumList);

    void onSuccess(Object object);

    void onError(Throwable e);
}
