package com.minasmob.passageiro.ui.activity.wallet;

import static com.minasmob.passageiro.BuildConfig.CONTA_PIX;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.common.Constants;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.AddWallet;
import com.minasmob.passageiro.data.network.model.BrainTreeResponse;
import com.minasmob.passageiro.data.network.model.DataResponse;
import com.minasmob.passageiro.data.network.model.Payment;
import com.minasmob.passageiro.data.network.model.Provider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;




public class WalletActivity extends BaseActivity implements WalletIView {

    @BindView(R.id.wallet_balance)
    TextView walletBalance;
    @BindView(R.id.amount)
    EditText amount;
    @BindView(R.id._30)
    Button _30;
    @BindView(R.id._50)
    Button _50;
    @BindView(R.id._100)
    Button _100;
    @BindView(R.id.add_amount)
    Button addAmount;
    @BindView(R.id.cvAddMoneyContainer)
    CardView cvAddMoneyContainer;
    @BindView( R.id.copias )
    ImageView copy;
    @BindView(R.id.txt_pix)
    TextView txt_pix;
    String regexNumber = "^(\\d{0,9}\\.\\d{1,4}|\\d{1,9})$";
    private WalletPresenter<WalletActivity> presenter = new WalletPresenter<>();
    private Payment preco;
    private Provider namep;
    private Provider mp;
    private Constants p;
    private Provider fleet;
    private DataResponse checkStatusResponse = new DataResponse();


    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {

        ButterKnife.bind(this);
        presenter.attachView(this);
        // Activity title will be updated after the locale has changed in Runtime
        setTitle(getString(R.string.wallet));
        txt_pix.setText(String.valueOf(CONTA_PIX));
        _30.setText(SharedHelper.getKey(this, "currency") + " " + getString(R.string._30));
        _50.setText(SharedHelper.getKey(this, "currency") + " " + getString(R.string._50));
        _100.setText(SharedHelper.getKey(this, "currency") + " " + getString(R.string._100));
        amount.setTag(SharedHelper.getKey(this, "currency"));

        walletBalance.setText(getNumberFormat().format(Double.parseDouble(SharedHelper.getKey(this, "walletBalance", "0"))));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick({R.id._30, R.id._50, R.id._100, R.id.add_amount, R.id.amount})
   // @OnClick({ R.id.add_amount})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id._30:
                amount.setText(getString(R.string._30));
                break;
            case R.id._50:
                amount.setText(getString(R.string._50));
                break;
            case R.id._100:
                amount.setText(getString(R.string._100));
                break;
            case R.id.add_amount:
             /*  if (!amount.getText().toString().trim().matches(regexNumber)) {
                    Toast.makeText(baseActivity(), getString(R.string.invalid_amount), Toast.LENGTH_SHORT).show();
                    return;
                }*/
               // SharedHelper.putKey(this, SOS_NUMBER, checkStatusResponse.getSos());
               // Datum picpay =DATUM;
                //preco= picpay.getPayment();
                //namep=picpay.getProvider();

               // String url = String.valueOf("https://app.picpay.com/payment?type=store&hash=nw38VyN9DmfNBxnD"+ amount.getText().toString());
                 String url = String.valueOf("https://app.picpay.com/payment?type=store&hash=nw38VyN9DmfNBxnD");

                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                startActivity( intent );
                break;
                ///chama active pagamento
             /*   Intent intent = new Intent(baseActivity(), PaymentActivity.class);
                intent.putExtra("hideCash", true);
                startActivityForResult(intent, PICK_PAYMENT_METHOD);
                break;*/
        }
    }


    @Override
    public void onSuccess(AddWallet wallet) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if(wallet.getMessage().equals("Transaction Failed")){
            Toast.makeText(this, "Falha no cartão ou saldo insuficiente! Por favor, tente novamente.", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, wallet.getMessage(), Toast.LENGTH_LONG).show();
        }

        amount.setText("");

//        SharedHelper.putKey(this, "walletBalance", String.valueOf(wallet.getBalance()));
        walletBalance.setText(getNumberFormat().format(Double.parseDouble(SharedHelper.getKey(this, "walletBalance", "0"))));
    }

    @Override
    public void onSuccess(BrainTreeResponse response) {

        if (!response.getToken().isEmpty()) {

        }

    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @OnClick (R.id.copias)
    public void onViewClickeds() {
        copi();

    }

    //copia pix por marcio hamasaki
    public void copi(){
        String text  = String.valueOf(Uri.parse(CONTA_PIX));
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService( Context.CLIPBOARD_SERVICE );
        ClipData clip = ClipData.newPlainText( "String", text);
        clipboardManager.setPrimaryClip( clip );
        clip.getDescription();
        Toast.makeText(getApplicationContext(), "chave pix copiado",
                Toast.LENGTH_SHORT).show();
    }

}
