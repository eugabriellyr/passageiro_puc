package com.minasmob.passageiro.ui.sos;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SosPresenter<V extends SosIView> extends BasePresenter<V> implements SosIPresenter<V> {



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
