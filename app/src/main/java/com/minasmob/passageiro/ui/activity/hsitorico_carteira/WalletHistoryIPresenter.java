package com.minasmob.passageiro.ui.activity.hsitorico_carteira;

import com.minasmob.passageiro.base.MvpPresenter;

public interface WalletHistoryIPresenter<V extends WalletHistoryIView> extends MvpPresenter<V> {
    void wallet();
}
