package com.minasmob.passageiro.ui.verifica_codigo;

import com.minasmob.passageiro.base.MvpView;

public interface VerificaIView extends MvpView{

    void onSuccess(Object object);
    void onError(Throwable e);
}
