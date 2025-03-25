package com.minasmob.passageiro.ui.activity.wallet;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.AddWallet;
import com.minasmob.passageiro.data.network.model.BrainTreeResponse;

public interface WalletIView extends MvpView {
    void onSuccess(AddWallet object);


    void onSuccess(BrainTreeResponse response);
    void onError(Throwable e);
}
