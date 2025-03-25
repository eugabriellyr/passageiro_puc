package com.minasmob.passageiro.ui.adiciona_enderecof;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.AddressResponse;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface AddEnderecosIView extends MvpView {

    void onSuccess(AddressResponse address);

    void onError(Throwable e);
}
