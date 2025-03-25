package com.minasmob.passageiro.ui.favorito;

import com.minasmob.passageiro.base.BasePresenter;
import com.minasmob.passageiro.data.network.APIClient;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public class FavPresenter<V extends FavIView> extends BasePresenter<V> implements FavIPresenter<V> {

    @Override
    public void updatefav(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename) {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .editFav(obj, filename)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onUpdateSuccess, getMvpView()::onError));
    }

    @Override
    public void favorito() {
        getCompositeDisposable().add(APIClient
                .getAPIClient()
                .favorito()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getMvpView()::onSuccess, getMvpView()::onError));
    }

   


}
