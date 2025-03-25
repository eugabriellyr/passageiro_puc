package com.minasmob.passageiro.ui.fragment.cancel_ride;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.CancelResponse;

import java.util.List;

public interface CancelRideIView extends MvpView {
    void onSuccess(Object object);

    void onError(Throwable e);

    void onSuccess(List<CancelResponse> response);

    void onReasonError(Throwable e);
}
