package com.minasmob.passageiro.ui.fragment.service_flow;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

public interface ServiceFlowIPresenter<V extends ServiceFlowIView> extends MvpPresenter<V> {

   // void updatesaque(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename);

    //void sosp(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename);

    void sospp();

    void update(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename);

    //void saque();
}
