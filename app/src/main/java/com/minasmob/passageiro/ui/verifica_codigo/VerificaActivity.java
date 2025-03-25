package com.minasmob.passageiro.ui.verifica_codigo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.ui.activity.register.RegisterActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class VerificaActivity extends BaseActivity implements VerificaIView {

    @BindView(R.id.txtCf)
    TextInputEditText txtCf;
    @BindView(R.id.btnDonemotorista)
    FloatingActionButton btnDonemotorista;

    VerificaPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_verificarf;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        presenter = new VerificaPresenter();
        presenter.attachView(this);

    }

    //@OnClick({R.id.back, R.id.btnDone,R.id.btnDonemotorista})
    @OnClick({R.id.back, R.id.btnDonemotorista})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                onBackPressed();
                break;
            case R.id.btnDonemotorista:
                if (txtCf.getText().toString().isEmpty()) {
                    Toasty.error(this, getString(R.string.campovazio), Toast.LENGTH_LONG, true).show();
                } else {

                    showLoading();
                    HashMap<String, Object> map = new HashMap<>();
                    map.put("codigorf", txtCf.getText().toString());
                    presenter.verificarf(map);


                }
                showLoading();
                HashMap<String, Object> maps = new HashMap<>();
                maps.put("codigorfm", txtCf.getText().toString());
                presenter.verificarfm(maps);
                break;

        }
    }

    @Override
    public void onSuccess(Object object) {
        hideLoading();
        Toasty.success(this, getString(R.string.codigo_valido), Toast.LENGTH_SHORT, true).show();

        String codigoreferencia = txtCf.getText().toString();
        Intent intentenvia = new Intent(getApplicationContext(), RegisterActivity.class);
        Bundle parametro = new Bundle();
        parametro.putString("chave_valor",codigoreferencia);
        intentenvia.putExtras(parametro);
          startActivity(intentenvia);

    }

   @Override
    public void onError(Throwable e) {
        hideLoading();
       // if(e!= null)

            Toasty.info(this, getString(R.string.codigo_invalido), Toast.LENGTH_SHORT, true).show();
      //  onErrorBase(e);
    }


}
