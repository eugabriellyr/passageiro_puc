package com.minasmob.passageiro.ui.activity.premio_bonus;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PremioBonusPresenter<V extends PremioBonusIView> extends BasePresenter<V> implements PremioBonusIPresenter<V> {
    @Override
    public void profile() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .profile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }
}
