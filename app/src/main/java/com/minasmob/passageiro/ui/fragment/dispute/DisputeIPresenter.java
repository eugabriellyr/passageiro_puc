package com.minasmob.passageiro.ui.fragment.dispute;

import com.minasmob.passageiro.base.MvpPresenter;

import java.util.HashMap;

public interface DisputeIPresenter<V extends DisputeIView> extends MvpPresenter<V> {

    void help();

    void dispute(HashMap<String, Object> obj);

    void getDispute();
}
