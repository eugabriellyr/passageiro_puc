package com.minasmob.passageiro.ui.activity.main;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter<V extends MainIView> extends BasePresenter<V> implements MainIPresenter<V> {

    @Override
    public void getUserInfo() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .profile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

    @Override
    public void checkStatus() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .checkStatus()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

    @Override
    public void logout(String id) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .logout(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccessLogout, getMvpView()::onError));
    }

    @Override
    public void getProviders(HashMap<String, Object> params) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .providers(params)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

    @Override
    public void getSavedAddress() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .address()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

    @Override
    public void getNavigationSettings() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .getSettings()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onSettingError));
    }

    @Override
    public void updateDestination(HashMap<String, Object> obj) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .extendTrip(obj)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::onDestinationSuccess, getMvpView()::onError));
    }

    @Override
    public void getBanner() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .profile()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::banner, getMvpView()::onError));
    }

    @Override
    public void getAviso() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .profile()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMvpView()::aviso, getMvpView()::onError));
    }

    @Override
    public void sos() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .sosp()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

}
