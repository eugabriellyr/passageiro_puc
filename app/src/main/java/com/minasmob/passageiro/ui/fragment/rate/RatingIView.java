package com.minasmob.passageiro.ui.fragment.rate;

import com.minasmob.passageiro.base.MvpView;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface RatingIView extends MvpView {
    void onSuccess(Object object);

    void onError(Throwable e);
}
