package com.minasmob.passageiro.ui.fragment.invoice;

import com.minasmob.passageiro.base.MvpView;

public interface InvoiceIView extends MvpView {
    //void onSuccess(Message message);

    void onSuccess(Object o);

    void onSuccessPayment(Object o);

    void onError(Throwable e);

    //void onSuccess(BrainTreeResponse response);

  //  void onPayumoneyCheckSumSucess(CheckSumData checkSumData);

}
