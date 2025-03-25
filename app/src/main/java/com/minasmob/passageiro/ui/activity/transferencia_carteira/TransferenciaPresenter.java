package com.minasmob.passageiro.ui.activity.transferencia_carteira;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public class TransferenciaPresenter<V extends TrasnferenciaIView> extends BasePresenter<V> implements TransferenciaIPresenter<V> {


    @Override
    public void updatesaque(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .Saque(obj, filename)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onUpdateSuccess, getMvpView()::onError));
    }

    @Override
    public void saque() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .saque()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

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
