package com.minasmob.passageiro.ui.activity.convide_ganhe;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ConvidePresenter<V extends ConvideIView> extends BasePresenter<V> implements ConvideIPresenter<V> {
    @Override
    public void profile() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .profile()
                .observeOn( AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }
}
