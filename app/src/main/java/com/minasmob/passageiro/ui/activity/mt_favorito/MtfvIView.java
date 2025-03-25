package com.minasmob.passageiro.ui.activity.mt_favorito;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.AddressResponse;

public interface MtfvIView extends MvpView {

    void onSuccessAddress(Object object);

    void onLanguageChanged(Object object);

    void onSuccess(AddressResponse address);

    void onError(Throwable e);
}
