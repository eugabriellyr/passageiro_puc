package com.minasmob.passageiro.ui.activity.help;

import com.minasmob.passageiro.base.MvpView;
import com.minasmob.passageiro.data.network.model.Help;

public interface HelpIView extends MvpView {

    void onSuccess(Help help);

    void onError(Throwable e);
}
