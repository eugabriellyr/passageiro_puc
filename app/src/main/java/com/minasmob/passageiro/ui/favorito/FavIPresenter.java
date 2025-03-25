package com.minasmob.passageiro.ui.favorito;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Part;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface FavIPresenter<V extends FavIView> extends MvpPresenter<V> {
   // void update(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename);


   // void profile();

    void updatefav(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename);

    void favorito();




    //  void verifyCredentials(String number, String phoneNumber);
}
