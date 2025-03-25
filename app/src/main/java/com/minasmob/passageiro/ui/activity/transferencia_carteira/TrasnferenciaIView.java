package com.minasmob.passageiro.ui.activity.transferencia_carteira;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.User;

public interface TrasnferenciaIView extends MvpView {

    void onSuccess(User user);

   void onUpdateSuccess(User user);

    void onError(Throwable e);

  //  void onSuccessPhoneNumber(Object object);

   // void onVerifyPhoneNumberError(Throwable e);

}
