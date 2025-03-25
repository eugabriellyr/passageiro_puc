package com.minasmob.passageiro.ui.fragment.dispute;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.DisputeResponse;
import com.minasmob.passageiro.data.network.model.Help;

import java.util.List;

public interface DisputeIView extends MvpView {

    void onSuccess(Object object);

    void onSuccessDispute(List<DisputeResponse> responseList);

    void onError(Throwable e);

    void onSuccess(Help help);
}
