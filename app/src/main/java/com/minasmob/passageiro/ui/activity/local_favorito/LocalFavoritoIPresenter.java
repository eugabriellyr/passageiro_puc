package com.minasmob.passageiro.ui.activity.local_favorito;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface LocalFavoritoIPresenter<V extends LocalFavoritoIView> extends MvpPresenter<V> {
    void addAddress(HashMap<String, Object> params);

    void deleteAddress(Integer id, HashMap<String, Object> params);

    void address();

    void changeLanguage(String languageID);
}
