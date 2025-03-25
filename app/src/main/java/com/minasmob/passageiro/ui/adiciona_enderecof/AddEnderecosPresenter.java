package com.minasmob.passageiro.ui.adiciona_enderecof;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddEnderecosPresenter<V extends AddEnderecosIView> extends BasePresenter<V> implements AddEnderecosIPresenter<V> {

    @Override
    public void address() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .address()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }
}
