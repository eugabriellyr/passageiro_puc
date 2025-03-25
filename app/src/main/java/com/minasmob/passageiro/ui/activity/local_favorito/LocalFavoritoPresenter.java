package com.minasmob.passageiro.ui.activity.local_favorito;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LocalFavoritoPresenter<V extends LocalFavoritoIView> extends BasePresenter<V> implements LocalFavoritoIPresenter<V> {

    @Override
    public void addAddress(HashMap<String, Object> params) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .addAddress(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccessAddress, getMvpView()::onError));
    }

    @Override
    public void deleteAddress(Integer id, HashMap<String, Object> params) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .deleteAddress(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccessAddress, getMvpView()::onError));
    }

    @Override
    public void address() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .address()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

    @Override
    public void changeLanguage(String languageID) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .postChangeLanguage(languageID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onLanguageChanged, getMvpView()::onError));
    }
}