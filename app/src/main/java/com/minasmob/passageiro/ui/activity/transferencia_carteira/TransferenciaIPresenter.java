package com.minasmob.passageiro.ui.activity.transferencia_carteira;

import com.minasmob.passageiro.base.MvpPresenter;
import com.minasmob.passageiro.base.MvpView;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public interface TransferenciaIPresenter<V extends TrasnferenciaIView & MvpView> extends MvpPresenter<V> {

    void updatesaque(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename);

    void saque();
    void profile();

   // void verifyCredentials(String number, String phoneNumber);

}
