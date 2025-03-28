package com.minasmob.passageiro.ui.fragment.lost_item;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class LostPresenter<V extends LostIView> extends BasePresenter<V> implements LostIPresenter<V> {

    @Override
    public void postLostItem(HashMap<String, Object> obj) {

        getCompositeDisposable().add(APIClient.getAPIClient().dropItem(obj)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(disputeResponse -> getMvpView().onSuccess(disputeResponse),
                        throwable -> getMvpView().onError(throwable)));
    }

}
