package com.minasmob.passageiro.ui.activity.transferencia_carteira;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.network.model.User;
import com.minasmob.passageiro.ui.activity.main.MainActivity;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class TransferenciaActivity extends BaseActivity implements TrasnferenciaIView {

    private TransferenciaPresenter mPresenter = new TransferenciaPresenter();


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.Valor)
    EditText valor ;
  ///  @BindView(R.id.rvRequestedData)
   // RecyclerView rvRequestedData;
    @BindView(R.id.aviso)
    LinearLayout aviso;
    @BindView( R.id.btn )
    LinearLayout btn;
  //  @BindView(R.id.tvHistoryPlaceholder)
   // TextView tvHistoryPlaceholder;
   // @BindView( R.id.teste )
   // TextView test;

    private Double walletAmt;
    private TransferenciaPresenter<TransferenciaActivity> transferenciaPresenter = new TransferenciaPresenter<>();


    @Override
    public int getLayoutId() {
        return R.layout.transferencia_carteira;
    }

    @SuppressLint("ResourceType")
    @Override
    public void initView() {


        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.transaction));
        transferenciaPresenter.attachView(this);
        setSupportActionBar(toolbar);

        showLoading();
        transferenciaPresenter.saque();
        transferenciaPresenter.profile();




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
    public void onSuccess(User user) {

       // test.setText( String.format( String.valueOf( user.getEmail()) ) );
        hideLoading();
        if (user.getTotal_metas() > user.getTotal_corridas()) {

            btn.setVisibility( View.GONE );
            aviso.setVisibility( View.VISIBLE );
            //loadAdapter(response.getPendingList());
        } else {

            btn.setVisibility( View.VISIBLE );
            aviso.setVisibility( View.GONE );
        }




    }

    @Override
    public void onUpdateSuccess(User user) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        finish();
        Toasty.success(this, getText(R.string.saque), Toast.LENGTH_SHORT).show();
        showLoading();
        loop();
    }

    @OnClick(R.id.tvSubmits)
    public void onViewClicked() {

        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.confirm_request_amt))
                .setPositiveButton(getString(R.string.confirm), (dialogInterface, i) -> {
                    showLoading();
                    updateDetails();
                    Toast.makeText(this, "Solicitação enviada!", Toast.LENGTH_SHORT).show();
                }).setNegativeButton(getString(R.string.cancel), (dialogInterface, i) -> valor.requestFocus())
                .show();






    }

    private void updateDetails() {
        HashMap<String, RequestBody> map = new HashMap<>();

        map.put("saque", RequestBody.create(MediaType.parse("text/plain"), ""));


        MultipartBody.Part filePart = null;

        showLoading();
        transferenciaPresenter.updatesaque(map, filePart);
    }

    private void loop(){

        startActivity(new Intent(this, MainActivity.class));

    }




    @Override
    public void onError(Throwable e) {
        hideLoading();
        if(e!= null)
            onErrorBase(e);
    }



}
