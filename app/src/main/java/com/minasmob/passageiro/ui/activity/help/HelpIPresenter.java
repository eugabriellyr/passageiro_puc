package com.minasmob.passageiro.ui.activity.help;


import com.minasmob.passageiro.base.MvpPresenter;

/**
 * Created by santhosh@appoets.com on 19-05-2018.
 */
public interface HelpIPresenter<V extends HelpIView> extends MvpPresenter<V> {
    void help();
}
