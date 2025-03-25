package com.minasmob.passageiro.ui.activity.local_favorito;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.AddressResponse;

public interface LocalFavoritoIView extends MvpView {

    void onSuccessAddress(Object object);

    void onLanguageChanged(Object object);

    void onSuccess(AddressResponse address);

    void onError(Throwable e);
}
