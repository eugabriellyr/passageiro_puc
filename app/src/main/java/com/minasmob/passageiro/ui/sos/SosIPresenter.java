package com.minasmob.passageiro.ui.sos;

import com.minasmob.passageiro.base.MvpPresenter;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface SosIPresenter<V extends SosIView> extends MvpPresenter<V> {
   // void update(HashMap<String, RequestBody> obj, @Part MultipartBody.Part filename);


    //void profile();

    void sos();

    //void verifyCredentials(String number, String phoneNumber);
}
