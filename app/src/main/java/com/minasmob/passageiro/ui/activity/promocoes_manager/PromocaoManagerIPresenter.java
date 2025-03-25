package com.minasmob.passageiro.ui.activity.promocoes_manager;

import com.minasmob.passageiro.base.MvpPresenter;

public interface PromocaoManagerIPresenter<V extends PromocaoManagerIView> extends MvpPresenter<V> {
    void getPromocaoManager();
}
