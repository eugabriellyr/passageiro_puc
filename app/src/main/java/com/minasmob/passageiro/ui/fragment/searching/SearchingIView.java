package com.minasmob.passageiro.ui.fragment.searching;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.DataResponse;

public interface SearchingIView extends MvpView {
    void onSuccess(DataResponse dataResponse);

    void onSuccess(Object object);

    void onError(Throwable e);
}
