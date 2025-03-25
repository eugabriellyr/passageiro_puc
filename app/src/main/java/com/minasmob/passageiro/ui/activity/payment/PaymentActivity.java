package com.minasmob.passageiro.ui.activity.payment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.minasmob.passageiro.MvpApplication.RIDE_REQUEST;

//TODO MARCIO HAMASAKI PICPAY MERCADO PAGO
import static com.minasmob.passageiro.MvpApplication.isDebitMachine;

import static com.minasmob.passageiro.MvpApplication.isMp;
import static com.minasmob.passageiro.MvpApplication.isPicPay;
import static com.minasmob.passageiro.MvpApplication.isPix;
import static com.minasmob.passageiro.common.Constants.PaymentMode.CASH;
import static com.minasmob.passageiro.common.Constants.PaymentMode.DEBIT_MACHINE;
import static com.minasmob.passageiro.common.Constants.PaymentMode.M_P;
import static com.minasmob.passageiro.common.Constants.PaymentMode.PIC_PAY;

import static com.minasmob.passageiro.common.Constants.PaymentMode.PIX;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.PAYMENT_MODE;

public class PaymentActivity extends BaseActivity  {

    public static final int PICK_PAYMENT_METHOD = 12;


    @BindView(R.id.add_card)
    TextView addCard;
    @BindView(R.id.cash)
    TextView tvCash;
    @BindView(R.id.cards_rv)
    RecyclerView cardsRv;
    @BindView(R.id.llCardContainer)
    LinearLayout llCardContainer;
    @BindView(R.id.llCashContainer)
    LinearLayout llCashContainer;

    //TODO MARCIO HAMASAKI PICPAY MERCADO PAGO

    @BindView(R.id.debit_machine)
    TextView debit_machine;


    @BindView(R.id.pic_pay)
    TextView pic_pay;

    @BindView(R.id.pix)
    TextView pix;

    @BindView(R.id.mercado_pago)
    TextView mercado_pago;




    private static final String TAG = "PaymentActivity";

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        setTitle(getString(R.string.payment));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean hideCash = extras.getBoolean("hideCash", false);
            tvCash.setVisibility(hideCash ? View.GONE : View.VISIBLE);
        }

        //TODO MARCIO HAMASAKI PICPAY MERCADO PAGO
        debit_machine.setVisibility(isDebitMachine ? View.VISIBLE : View.GONE);
        pic_pay.setVisibility(isPicPay ? View.VISIBLE : View.GONE);
        pix.setVisibility(isPix ? View.VISIBLE : View.GONE);
        mercado_pago.setVisibility(isMp ? View.VISIBLE : View.GONE);





    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //TODO MARCIO HAMASAKI PICPAY MERCADO PAGO
//    @OnClick({R.id.add_card, R.id.cash, R.id.braintree, R.id.paytm, R.id.payumoney})
    @OnClick({ R.id.cash,R.id.pic_pay,R.id.pix,R.id.mercado_pago, R.id.debit_machine})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.cash:
                finishResult(CASH);
                break;
            case R.id.debit_machine:
                finishResult(DEBIT_MACHINE);
                break;

            case R.id.pic_pay:
                finishResult(PIC_PAY);

                break;
            case R.id.pix:
                finishResult(PIX);
                break;

            case R.id.mercado_pago:
                finishResult(M_P);
                break;




        }
    }



    public void finishResult(String mode) {
        Intent intent = new Intent();
        RIDE_REQUEST.put(PAYMENT_MODE, mode);
        intent.putExtra("payment_mode", mode);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }





    @Override
    public void onError(Throwable e) {
        handleError(e);
    }








}
