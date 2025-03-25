package com.minasmob.passageiro.ui.activity.promocoes_manager;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PromocaoManagerPresenter<V extends PromocaoManagerIView> extends BasePresenter<V> implements PromocaoManagerIPresenter<V> {

    @Override
    public void getPromocaoManager() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .getPromocaoManager()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }
}
