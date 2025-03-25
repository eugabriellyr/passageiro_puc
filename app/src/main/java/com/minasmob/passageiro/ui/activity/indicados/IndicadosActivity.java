package com.minasmob.passageiro.ui.activity.indicados;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.minasmob.passageiro.MvpApplication;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.common.Constants;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.User;
import com.minasmob.passageiro.ui.activity.transferencia_carteira.TransferenciaActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndicadosActivity extends BaseActivity implements IndicadosIView {
 //TODO função Marcio hamasaki
    private static final String TAG = "Indicados";
    @BindView(R.id.Total_corridas)
    TextView total_corridas;
    @BindView(R.id.txt_d)
    TextView txt_d;
    @BindView(R.id.Total_amigos)
    TextView total_amigos;
    @BindView(R.id.Total_ganho)
    TextView total_ganhos;
    @BindView(R.id.id_user)
    TextView id_user;

    @BindView(R.id.pago)
    TextView pago;
    @BindView(R.id.metas)
    TextView total_metas;
    @BindView(R.id.btn_libera)
    RelativeLayout btn_libera;
    @BindView(R.id.txt_p)
    TextView txtp;
    @BindView(R.id.corridas)
    TextView corridas;
    @BindView(R.id.banner_aviso)
    LinearLayout banner_aviso;
    @BindView( R.id.lblcarteira_saldo )
    LinearLayout lblcarteira_saldo;
    @BindView( R.id.valor_pago )
    TextView valor_pg;
    int cod;
    private IndicadosPresenter<IndicadosActivity> indicadosPresenter = new IndicadosPresenter<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_indicados;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        indicadosPresenter.attachView(this);
        indicadosPresenter.profile();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSuccess(User user) {

       if (user.getTotal_corridas() >= user.getTotal_metas()){

           btn_libera.setVisibility( View.VISIBLE);
           lblcarteira_saldo.setVisibility( View.VISIBLE);
           banner_aviso.setVisibility( View.GONE);

       }else{

           btn_libera.setVisibility( View.GONE);
           banner_aviso.setVisibility( View.VISIBLE);
           lblcarteira_saldo.setVisibility( View.VISIBLE);

       }


      ///  t = SharedHelper.getKey(this, "total_ganhos");
        //TODO MARCIO HAMASAKI /28/01/2021  19:40:45 AM
        float resultado = 0;
        int a = user.getTotal_metas();
        float b = user.getTotal_pago();
        resultado = a * b;
        id_user.setText(String.valueOf(user.getId()));
        cod = user.getId();
        total_corridas.setText(String.format( String.valueOf( user.getTotal_corridas() ) ));
        corridas.setText(String.format( String.valueOf( user.getTotal_corridas() ) ));
        total_amigos.setText(String.format( String.valueOf( user.getTotal_amigos() ) ));
        total_metas.setText(String.format( String.valueOf( user.getTotal_metas() ) ));
        txtp.setText( String.format( String.valueOf( user.getTotal_amigos()) ) );
        valor_pg.setText( String.format( String.valueOf( user.getTotal_pago() ) ) );
        txt_d.setText( String.format( String.valueOf(  user.getTxt_indica() ) ) );
       // pago.setText( String.format( String.valueOf( user.getTotal_pago()  ) ) );
        pago.setText(getNumberFormat().format(Double.parseDouble(String.valueOf( user.getTotal_pago()))));
        animateTextViewFloat(0.0f, Float.parseFloat( String.valueOf(user.getTotal_ganhos()  ) ), total_ganhos);
       // animateTextViewFloat(0.0f, Float.parseFloat( String.valueOf(user.getTotal_ganhos()+ "0"  ) ), pago);


        //  total_ganhos.setText(Html.fromHtml(SharedHelper.getKey(this, "total_ganhos")));
        //total_ganhos.setText(Html.fromHtml(SharedHelper.getKey(this, "total_ganhos")));
       // btn_libera.setText( "ola mundo" );
       // total_metas.setText(Html.fromHtml(SharedHelper.getKey(this, "total_metas")));




    }

    @SuppressLint("SetTextI18n")
    public void animateTextViewFloat(float initialValue, float finalValue, final TextView textview) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(initialValue, finalValue);
        valueAnimator.setDuration(700);
        valueAnimator.addUpdateListener(valueAnimator1 -> {
            if (textview.getId() == R.id.Total_ganho) {
                textview.setText(Constants.Currency + MvpApplication.getInstance().getNewNumberFormat(Double.parseDouble
                        (valueAnimator1.getAnimatedValue().toString())));
            }else if (textview.getId() == R.id.Total_ganho) {
                textview.setText(Constants.Currency + MvpApplication.getInstance().getNewNumberFormat(Double.parseDouble
                        (valueAnimator1.getAnimatedValue().toString())));
            }else {
                textview.setText(valueAnimator1.getAnimatedValue().toString());
            }
        });
        valueAnimator.start();

    }
    @OnClick({ R.id.btn_libera})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btn_libera:
               startActivity(new Intent(this, TransferenciaActivity.class));
                break;
        }
    }

    @Override
    public void onError(Throwable throwable) {
        handleError(throwable);
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
