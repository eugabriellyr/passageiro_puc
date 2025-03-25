package com.minasmob.passageiro.ui.adiciona_favorito;

import androidx.annotation.NonNull;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.AddFav;

public interface AddFavIView extends MvpView {

   // void onSuccess(User user);

    //void onUpdateSuccess(User user);

   // void onUpdateSuccess(Fav fav);

    void onSuccess(@NonNull AddFav user);

    void onUpdateSuccess(AddFav addFav);

    void onError(Throwable e);



    //void onSuccess(Fav fav);

  //  void onUpdateSuccess(Fav user);

    //void onSuccess(AddFav addFav);

    //void onUpdateSuccess(Fav fav);


    //void onSuccess(List<Provider> providers);


    // void onSuccessPhoneNumber(Object object);

   // void onVerifyPhoneNumberError(Throwable e);
}
