package com.minasmob.passageiro.ui.activity.convide_ganhe;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.network.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConvideActivity extends BaseActivity implements ConvideIView {

    private ConvidePresenter<ConvideActivity> inviteFriendPresenter = new ConvidePresenter<>();

    @BindView(R.id.invite_friend)
    TextView invite_friend;
    @BindView(R.id.valor)
    TextView valor;
    @BindView(R.id.referral_code)
    TextView referral_code;
    @BindView( R.id.pg )
    TextView pg;
   /// @BindView(R.id.referral_amount)
  //  TextView referral_amount;
    int cod;
    int total = 0;

   //TODO marcio hamasaki 2021  whts 11954714740
    @Override
    public int getLayoutId() {
        return R.layout.activity_convide_ganhe;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        inviteFriendPresenter.attachView(this);


       inviteFriendPresenter.profile();
    }




    @Override
    public void onSuccess(User user) {
        float resultado = 0;

        int meta = user.getTotal_metas();
        float corridas = user.getTotal_pago();
        resultado = meta * corridas;

        pg.setText( String.format( String.valueOf( user.getTotal_pago() ) ) );
        valor.setText( String.format( String.valueOf( user.getTotal_metas() ) ) );
        referral_code.setText( String.format( String.valueOf( user.getId() ) ) );
        cod = user.getId();
        invite_friend.setText( String.format( String.valueOf( "Cotação Indique no momento \n\n"+ corridas + " x "+ meta + " = "+ resultado ) ) );

    }

    @Override
    public void onError(Throwable throwable) {
        onErrorBase(throwable);
    }


    @SuppressLint({"StringFormatInvalid", "StringFormatMatches"})
    @OnClick({R.id.share})
    public void onClickAction(View view) {
        switch (view.getId()) {
            case R.id.share:


                    String appName = getString(R.string.app_name);
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/plain");
                    i.putExtra(Intent.EXTRA_SUBJECT, appName);
                    i.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_content_referral, appName,cod ));
                    startActivity(Intent.createChooser(i, "choose one"));

                break;





        }
    }
}
