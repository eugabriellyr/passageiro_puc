package com.minasmob.passageiro.ui.activity.location_pick;

import com.minasmob.passageiro.base.MvpPresenter;

public interface LocationPickIPresenter<V extends LocationPickIView> extends MvpPresenter<V> {
    void address();
}
