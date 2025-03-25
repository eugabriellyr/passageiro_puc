package com.minasmob.passageiro.ui.activity.mt_favorito;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface MtfvIPresenter<V extends MtfvIView> extends MvpPresenter<V> {
    void addAddress(HashMap<String, Object> params);

    void deleteAddress(Integer id, HashMap<String, Object> params);

    void address();

    void changeLanguage(String languageID);
}
