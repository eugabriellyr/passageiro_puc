package com.minasmob.passageiro.ui.activity.hsitorico_carteira;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.WalletResponse;

public interface WalletHistoryIView extends MvpView {
    void onSuccess(WalletResponse response);

    void onError(Throwable e);
}
