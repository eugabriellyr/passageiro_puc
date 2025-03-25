package com.minasmob.passageiro.ui.activity.libera;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public class LiberaPresenter<V extends LiberaIView> extends BasePresenter<V> implements LiberaIPresenter<V> {

    @Override
    public void update(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .editFrente(obj, filename)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onUpdateSuccess, getMvpView()::onError));
    }

    @Override
    public void update_verso(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .editVerso(obj, filename)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onUpdateSuccess, getMvpView()::onError));
    }

    @Override
    public void profile() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .educador()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

   /* @Override
    public void verifyCredentials(String number, String countryCode) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .verifyCredentials(number, countryCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccessPhoneNumber, getMvpView()::onVerifyPhoneNumberError));
    }*/
}
