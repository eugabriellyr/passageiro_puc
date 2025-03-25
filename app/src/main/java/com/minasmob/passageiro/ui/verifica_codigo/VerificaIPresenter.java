package com.minasmob.passageiro.ui.verifica_codigo;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface VerificaIPresenter<V extends VerificaIView> extends MvpPresenter<V> {



    void verificarf(HashMap<String, Object> obj);


    void verificarfm(HashMap<String, Object> obj);


}
