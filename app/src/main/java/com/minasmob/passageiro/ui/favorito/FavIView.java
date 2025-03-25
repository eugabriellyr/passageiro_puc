package com.minasmob.passageiro.ui.favorito;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.Fav;

public interface FavIView extends MvpView {

   // void onSuccess(User user);

    //void onUpdateSuccess(User user);

   // void onUpdateSuccess(Fav fav);

    void onError(Throwable e);



    void onSuccess(Fav fav);

    void onUpdateSuccess(Fav user);



    //void onSuccess(AddFav addFav);


    //void onSuccess(List<Provider> providers);


    // void onSuccessPhoneNumber(Object object);

   // void onVerifyPhoneNumberError(Throwable e);
}
