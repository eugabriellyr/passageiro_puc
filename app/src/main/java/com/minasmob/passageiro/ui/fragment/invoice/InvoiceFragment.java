package com.minasmob.passageiro.ui.fragment.invoice;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;


import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseFragment;
import com.minasmob.passageiro.common.Constants;
import com.minasmob.passageiro.data.network.model.Datum;
import com.minasmob.passageiro.data.network.model.Payment;
import com.minasmob.passageiro.data.network.model.Provider;
import com.minasmob.passageiro.data.network.model.User;
import com.minasmob.passageiro.ui.activity.main.MainActivity;

import java.util.HashMap;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

import static com.minasmob.passageiro.MvpApplication.DATUM;
import static com.minasmob.passageiro.common.Constants.Status.RATING;
import static com.minasmob.passageiro.data.SharedHelper.getKey;

public class InvoiceFragment extends BaseFragment implements InvoiceIView {

    public static boolean isInvoiceCashToCard = false;
    @BindView(R.id.fragment_invoice)
    NestedScrollView containerScroll;
    @BindView(R.id.payment_mode)
    TextView tvPaymentMode;
    @BindView(R.id.pay_now)
    Button payNow;
    @BindView(R.id.done)
    Button done;
    @BindView(R.id.booking_id)
    TextView bookingId;
    @BindView(R.id.distance)
    TextView distance;
    @BindView(R.id.travel_time)
    TextView travelTime;
    @BindView(R.id.fixed)
    TextView fixed;
    @BindView(R.id.distance_fare)
    TextView distanceFare;
    @BindView(R.id.tax)
    TextView tax;
    @BindView( R.id.textpix )
    TextView txtpix;
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.payable)
    TextView payable;
    @BindView(R.id.desconto)
    TextView desconto;
    @BindView(R.id.wallet_detection)
    TextView walletDetection;
    @BindView(R.id.time_fare)
    TextView timeFare;
    @BindView(R.id.llDistanceFareContainer)
    LinearLayout llDistanceFareContainer;
    @BindView(R.id.llTimeFareContainer)
    LinearLayout llTimeFareContainer;
    @BindView(R.id.llTipContainer)
    LinearLayout llTipContainer;

    @BindView(R.id.lblportam)
    LinearLayout lblportam;

    @BindView(R.id.llWalletDeductionContainer)
    LinearLayout llWalletDeductionContainer;
    @BindView(R.id.llDiscountContainer)
    LinearLayout llDiscountContainer;
    @BindView(R.id.llPaymentContainer)
    LinearLayout llPaymentContainer;
    @BindView(R.id.llTravelTime)
    LinearLayout llTravelTime;
    @BindView(R.id.llBaseFare)
    LinearLayout llBaseFare;
    @BindView(R.id.llPayable)
    LinearLayout llPayable;
    @BindView(R.id.llWaitingAmountContainer)
    LinearLayout llWaitingAmountContainer;
    @BindView(R.id.llTolleChargeContainer)
    LinearLayout llTolleChargeContainer;
    @BindView(R.id.llRoundOffContainer)
    LinearLayout llRoundOffContainer;
    //    @BindView(R.id.tvChange)
//    TextView tvChange;
    private Payment preco;
    private Provider namep;
    private Provider mp;
    @BindView(R.id.Mercadopg)
    Button mercadopago;

    @BindView(R.id.picpay)
    Button picpay;

    @BindView(R.id.txtobrigado)
    TextView txtobr;
    @BindView(R.id.txtmaquininha)
    TextView txtmq;
    @BindView(R.id.txtpicpay)
    TextView txtpicp;
    @BindView(R.id.txtmp)
    TextView txtmpg;
    @BindView(R.id.pix)
    TextView pix;
    @BindView( R.id.pixlbl )
    LinearLayout lblpix;
    @BindView(R.id.lbldesconto)
    LinearLayout lbldesconto;

    @BindView(R.id.imgpix)
    ImageView imgpix;

    // @BindView(R.id.tvTipAmt)
    //TextView tvTipAmt;
    @BindView(R.id.tvDiscount)
    TextView tvDiscount;
    @BindView(R.id.tvWaitingAmount)
    TextView tvWaitingAmount;
    @BindView(R.id.tvTollCharges)
    TextView tvTollCharges;
    @BindView(R.id.tvRoundOff)
    TextView tvRoundOff;
    @BindView(R.id.tvWaitingTimeDesc)
    TextView tvWaitingTimeDesc;
    @BindView(R.id.tvWaitingTime)
    TextView tvWaitingTime;
    @BindView(R.id.porta_m)
    TextView porta_m;


    private InvoicePresenter<InvoiceFragment> presenter = new InvoicePresenter<>();
    private Payment payment;
    private User Carteira;
    private String payingMode;
    private Double tips = 0.0;
    private Object InvoiceFragment;

    public Object getSystemService(String name) {
        return null;
    }
    private boolean isDoubleBackPressed = false;

    public InvoiceFragment() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invoice;
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public View initView(View view) {
        ButterKnife.bind(this, view);
        if (DATUM != null) {

            containerScroll.setVisibility(View.VISIBLE);
            initView(DATUM);
        } else {

            containerScroll.setVisibility(View.GONE);



        }


        return view;





    }


    private void initView(@NonNull Datum datum) {


        //RETORNA FRAGMENTO INVOICE

        bookingId.setText(datum.getBookingId());
       if (getKey(getContext(), "measurementType").equalsIgnoreCase(Constants.MeasurementType.KM)) {
            if (datum.getDistance() > 1 || datum.getDistance() > 1.0)
                distance.setText(String.format("%s %s", datum.getDistance(), Constants.MeasurementType.KM));
            else
                distance.setText(String.format("%s %s", datum.getDistance(), getString(R.string.km)));
        } else {
            if (datum.getDistance() > 1 || datum.getDistance() > 1.0)
                distance.setText(String.format("%s %s", datum.getDistance(), Constants.MeasurementType.MILES));
            else
                distance.setText(String.format("%s %s", datum.getDistance(), getString(R.string.mile)));
        }
        try {
            if (datum.getTravelTime() != null && Double.parseDouble(datum.getTravelTime()) > 0) {
                llTravelTime.setVisibility(View.VISIBLE);
               /* int t_a = datum.getTempo_inicio();
                int t_b = datum.getTempo_final();
                int resultado;
                resultado = t_b - t_a;*/
                travelTime.setText(String.valueOf(datum.getTravelTime() +" minutos"));
               // travelTime.setText(getString(R.string._min, datum.getTravelTime()));
            } else llTravelTime.setVisibility(View.GONE);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            llTravelTime.setVisibility(View.VISIBLE);
            travelTime.setText(getString(R.string._min, datum.getTravelTime()));
        }
        initPaymentView(datum.getPaymentMode(), "", false);

        payment = datum.getPayment();

        try {
            if (payment != null) {
                if (payment.getFixed() > 0) {
                    llBaseFare.setVisibility(View.GONE);
                    fixed.setText(String.format("%s", getNewNumberFormat(payment.getFixed())));
                } else llBaseFare.setVisibility(View.GONE);
                tax.setText(String.format("%s", getNewNumberFormat(payment.getTax())));

                //se for descontado da carteira mostra
                if (payment.getWallet() > 0) {

                    total.setText(String.format("%s", getNewNumberFormat( payment.getTotal() - payment.getWallet())));
                } else {

                    total.setText(String.format("%s", getNewNumberFormat(payment.getTotal())));
                }

                if (payment.getPayable() > 0) {
                    llPayable.setVisibility(View.VISIBLE);
                    payable.setText(String.format("%s", getNewNumberFormat(payment.getPayable())));
                } else llPayable.setVisibility(View.GONE);

                if (payment.getDesc() > 0) {
                    lbldesconto.setVisibility(View.VISIBLE);
                    desconto.setText(String.format("%s", getNewNumberFormat(payment.getDesc())));
                } else lbldesconto.setVisibility(View.GONE);

                if (payment.getPortaM() > 0) {
                    lblportam.setVisibility(View.VISIBLE);
                    porta_m.setText(String.format("%s", getNewNumberFormat(payment.getPortaM())));
                } else lblportam.setVisibility(View.GONE);

                if (payment.getWallet() > 0) {
                    llWalletDeductionContainer.setVisibility(View.VISIBLE);
                    walletDetection.setText(String.format("%s", "-"+ getNewNumberFormat(payment.getWallet())));
                } else llWalletDeductionContainer.setVisibility(View.GONE);

                if (payment.getDiscount() > 0) {
                    llDiscountContainer.setVisibility(View.VISIBLE);
                    tvDiscount.setText(String.format("-%s", getNewNumberFormat(payment.getDiscount())));
                } else llDiscountContainer.setVisibility(View.GONE);

                if (payment.getWaitingAmount() > 0) {
                    tvWaitingTimeDesc.setVisibility(View.GONE);
                    llWaitingAmountContainer.setVisibility(View.VISIBLE);
                    tvWaitingTime.setText(getString(R.string.waiting_amount));
                    tvWaitingAmount.setText(String.format("%s", getNewNumberFormat(payment.getWaitingAmount())));
                } else if (payment.getWaitingAmount() == 0
                        && datum.getServiceType().getWaitingMinCharge() == 0) {
                    tvWaitingTimeDesc.setVisibility(View.VISIBLE);
                    llWaitingAmountContainer.setVisibility(View.VISIBLE);
                    tvWaitingTime.setText(getString(R.string.waiting_amount_star));
                    tvWaitingAmount.setText(String.format("%s", "0"));
                } else {
                    tvWaitingTimeDesc.setVisibility(View.GONE);
                    tvWaitingTime.setText(getString(R.string.waiting_amount));
                    llWaitingAmountContainer.setVisibility(View.GONE);
                }

                if (payment.getToll_charge() > 0) {
                    llTolleChargeContainer.setVisibility(View.VISIBLE);
                    tvTollCharges.setText(String.format("%s", getNewNumberFormat(payment.getToll_charge())));
                } else llTolleChargeContainer.setVisibility(View.GONE);

                if (payment.getRoundOf() != 0) {
                    llRoundOffContainer.setVisibility(View.GONE);
                    tvRoundOff.setText(String.format("%s", getNewNumberFormat(payment.getRoundOf())));
                } else llRoundOffContainer.setVisibility(View.GONE);

                //      MIN,    HOUR,   DISTANCE,   DISTANCEMIN,    DISTANCEHOUR
                if (datum.getServiceType().getCalculator().equalsIgnoreCase(Constants.InvoiceFare.MINUTE)
                        || datum.getServiceType().getCalculator().equalsIgnoreCase(Constants.InvoiceFare.HOUR)) {
                    llTimeFareContainer.setVisibility(View.GONE);
                    llDistanceFareContainer.setVisibility(View.GONE);
                    distanceFare.setText(R.string.time_fare);
                    if (datum.getServiceType().getCalculator().equalsIgnoreCase(Constants.InvoiceFare.MINUTE)) {
                        if (payment.getMinute() > 0) {
                            llTimeFareContainer.setVisibility(View.GONE);
                            timeFare.setText(String.format("%s", getNewNumberFormat(payment.getMinute())));
                        } else llTimeFareContainer.setVisibility(View.GONE);

                    } else if (datum.getServiceType().getCalculator().equalsIgnoreCase(Constants.InvoiceFare.HOUR)) {
                        if (payment.getHour() > 0) {
                            llTimeFareContainer.setVisibility(View.GONE);
                            timeFare.setText(String.format("%s", getNewNumberFormat(payment.getHour())));
                        } else llTimeFareContainer.setVisibility(View.GONE);
                    }
                } else if (datum.getServiceType().getCalculator().equalsIgnoreCase(Constants.InvoiceFare.DISTANCE)) {
                    llTimeFareContainer.setVisibility(View.GONE);
                    if (payment.getDistance() == 0.0 || payment.getDistance() == 0)
                        llDistanceFareContainer.setVisibility(View.GONE);
                    else {
                        llDistanceFareContainer.setVisibility(View.GONE);
                        distanceFare.setText(String.format("%s", getNewNumberFormat(payment.getDistance())));
                    }
                } else if (datum.getServiceType().getCalculator().equalsIgnoreCase(Constants.InvoiceFare.DISTANCE_MIN)) {
                    if (payment.getDistance() == 0.0 || payment.getDistance() == 0)
                        llDistanceFareContainer.setVisibility(View.GONE);
                    else {
                        llDistanceFareContainer.setVisibility(View.GONE);
                        distanceFare.setText(String.format("%s", getNewNumberFormat(payment.getDistance())));
                    }
                    if (payment.getMinute() > 0) {
                        llTimeFareContainer.setVisibility(View.GONE);
                        timeFare.setText(String.format("%s", getNewNumberFormat(payment.getMinute())));
                    } else llTimeFareContainer.setVisibility(View.GONE);
                } else if (datum.getServiceType().getCalculator().equalsIgnoreCase(Constants.InvoiceFare.DISTANCE_HOUR)) {
                    if (payment.getDistance() == 0.0 || payment.getDistance() == 0) {
                        llDistanceFareContainer.setVisibility(View.GONE);
                    } else {
                        llDistanceFareContainer.setVisibility(View.GONE);
                        distanceFare.setText(String.format("%s", getNewNumberFormat(payment.getDistance())));
                    }
                    if (payment.getHour() > 0) {
                        llTimeFareContainer.setVisibility(View.GONE);
                        timeFare.setText(String.format("%s", getNewNumberFormat(payment.getHour())));
                    } else llTimeFareContainer.setVisibility(View.GONE);
                }
            } else {
                Toast.makeText(getActivity().getApplicationContext(), "Pagamento não identificado",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }





    }




    @Override
    public void onResume() {
        super.onResume();

        Datum datum = DATUM;
        if (datum.getPaymentMode() != null) payingMode = datum.getPaymentMode();
        llPaymentContainer.setVisibility(datum.getPaid() == 1 ? View.GONE : View.VISIBLE);
        //ao finalizar esconde txtview
        txtpicp.setVisibility(View.GONE);
        txtpix.setVisibility(View.GONE);
        txtpix.setVisibility(View.GONE);
        imgpix.setVisibility(View.GONE);
        pix.setVisibility(View.GONE);
        lblpix.setVisibility( View.GONE );
        //TODO MH
        if (datum.getPaid()  == 0 && payingMode.equalsIgnoreCase("CASH")) {
            txtpicp.setVisibility(View.GONE);
            txtpix.setVisibility(View.GONE);
            imgpix.setVisibility(View.GONE);
            pix.setVisibility(View.GONE);
            lblpix.setVisibility( View.GONE );
            done.setVisibility(View.VISIBLE);
            picpay.setVisibility(View.GONE);
            mercadopago.setVisibility(View.GONE);
            payNow.setVisibility(View.GONE);
            llTipContainer.setVisibility(View.VISIBLE);
//            tvChange.setVisibility(View.VISIBLE);
            done.setOnClickListener(v -> Toasty.info(getContext(),
                    getString(R.string.payment_not_confirmed), Toast.LENGTH_SHORT).show());


        } else if (datum.getPaid() == 0 && payingMode.equalsIgnoreCase("M_P" )) {
            txtmpg.setVisibility(View.VISIBLE);
            txtpicp.setVisibility(View.GONE);
            txtobr.setVisibility( View.GONE );
            txtpix.setVisibility(View.GONE);
            imgpix.setVisibility(View.GONE);
            pix.setVisibility(View.GONE);
            lblpix.setVisibility( View.GONE );
            picpay.setVisibility(View.GONE);
            mercadopago.setVisibility(View.VISIBLE);
            done.setVisibility( View.VISIBLE );
            payNow.setVisibility( View.GONE );
            llTipContainer.setVisibility( View.VISIBLE );
            done.setOnClickListener(v -> Toasty.info(getContext(),
                    getString(R.string.payment_not_confirmed), Toast.LENGTH_SHORT).show());
            //se for picpay mostra botão
        }else if (datum.getPaid() == 0 && payingMode.equalsIgnoreCase("PIC_PAY" )) {
            txtpicp.setVisibility(View.VISIBLE);
            txtpix.setVisibility(View.GONE);
            txtobr.setVisibility( View.GONE );
            imgpix.setVisibility(View.GONE);
            pix.setVisibility(View.GONE);
            lblpix.setVisibility( View.GONE );
            picpay.setVisibility(View.VISIBLE);
            mercadopago.setVisibility(View.GONE);
            done.setVisibility( View.VISIBLE );
            payNow.setVisibility( View.GONE );
            //mostra bt motorista confirmou
            llTipContainer.setVisibility( View.VISIBLE );
            done.setOnClickListener(v -> Toasty.info(getContext(),
                    getString(R.string.payment_not_confirmed), Toast.LENGTH_SHORT).show());

        }else if (datum.getPaid() == 0 && payingMode.equalsIgnoreCase("DEBIT_MACHINE" )) {
            txtpicp.setVisibility(View.GONE);
            txtpix.setVisibility(View.GONE);
            txtmq.setVisibility( View.VISIBLE );
            txtobr.setVisibility( View.GONE );
            imgpix.setVisibility(View.GONE);
            pix.setVisibility(View.GONE);
            lblpix.setVisibility( View.GONE );
            picpay.setVisibility(View.GONE);
            mercadopago.setVisibility(View.GONE);
            done.setVisibility( View.VISIBLE );
            payNow.setVisibility( View.GONE );
            //mostra bt motorista confirmou
            llTipContainer.setVisibility( View.VISIBLE );
            done.setOnClickListener(v -> Toasty.info(getContext(),
                    getString(R.string.payment_not_confirmed), Toast.LENGTH_SHORT).show());

        }else if (datum.getPaid() == 0 && payingMode.equalsIgnoreCase("PIX" )) {
            txtpicp.setVisibility(View.GONE);
            txtobr.setVisibility( View.GONE );
            txtpix.setVisibility(View.VISIBLE);
            pix.setVisibility(View.VISIBLE);
            lblpix.setVisibility( View.VISIBLE );
            imgpix.setVisibility(View.VISIBLE);
            Datum picpay =DATUM;
            namep=picpay.getProvider();
            pix.setText(namep.getChavePix() );
            llTipContainer.setVisibility( View.VISIBLE );
            done.setOnClickListener(v -> Toasty.info(getContext(),
                    getString(R.string.payment_not_confirmed), Toast.LENGTH_SHORT).show());






        }


    }

    @Override
    public void onSuccess(Object obj) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public void onSuccessPayment(Object o) {
        Toast.makeText(getContext(), R.string.you_have_successfully_paid, Toast.LENGTH_SHORT).show();
        ((MainActivity) Objects.requireNonNull(getContext())).changeFlow("RATING");
    }







    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    //    @OnClick({R.id.payment_mode, R.id.pay_now, R.id.done, R.id.tvChange, R.id.tvGiveTip, R.id.tvTipAmt, R.id.ivInvoice})
    @SuppressLint("UseRequireInsteadOfGet")
    //@OnClick({R.id.payment_mode, R.id.pay_now, R.id.done, R.id.tvGiveTip, R.id.tvTipAmt, R.id.ivInvoice,R.id.picpay,R.id.mp})

    @OnClick({R.id.payment_mode, R.id.pay_now, R.id.done, R.id.Mercadopg, R.id.ivInvoice,R.id.picpay,R.id.pix,R.id.copias})
    public void onViewClicked(View view) {



        switch (view.getId()) {
//



            case R.id.picpay:
                Picpay();
                break;






            case R.id.pix:

                break;


            case R.id.copias:
                copi();
                break;



            case R.id.Mercadopg:
                mercado_pago();
                break;


            case R.id.pay_now:
                if (DATUM != null) {
                    Datum datum = DATUM;
                    switch (datum.getPaymentMode()) {

                        case Constants.PaymentMode.CASH:
                            if (isInvoiceCashToCard) {
                                showLoading();
                                HashMap<String, Object> cashHashMap = new HashMap<>();
                                cashHashMap.put("request_id", DATUM.getId());
                                cashHashMap.put("tips", tips);
                                cashHashMap.put("payment_type", Constants.PaymentMode.CASH);
                                presenter.payment(cashHashMap);

                            }
                            break;




                    }
                }
                break;
            case R.id.done:

                ((MainActivity) Objects.requireNonNull(getContext())).changeFlow(RATING);
                break;
            case R.id.ivInvoice:
                break;

        }
    }







    void initPaymentView(String paymentMode, String value, boolean payment) {

        switch (paymentMode) {
            case Constants.PaymentMode.CASH:
                tvPaymentMode.setText("DINHEIRO");
                break;


            case Constants.PaymentMode.M_P:

                tvPaymentMode.setText("MERCADO PAGO");
                break;

            case Constants.PaymentMode.PIX:

                tvPaymentMode.setText("PIX" );


                break;

            case Constants.PaymentMode.DEBIT_MACHINE:
                tvPaymentMode.setText("DÉBITO NA MÁQUINA");
                break;

            case Constants.PaymentMode.PIC_PAY:

                tvPaymentMode.setText("PIC_PAY" );


                break;




            case Constants.PaymentMode.WALLET:
                tvPaymentMode.setText(getString(R.string.wallet));
                break;
            default:
                break;
        }
    }

    //copia pix por marcio hamasaki
    public void copi(){
        String text  = namep.getChavePix();
        ClipboardManager clipboardManager = (ClipboardManager) getActivity().getSystemService( Context.CLIPBOARD_SERVICE );
        ClipData clip = ClipData.newPlainText( "String", text);
        clipboardManager.setPrimaryClip( clip );
        clip.getDescription();
        Toasty.info(getActivity().getApplicationContext(), "chave pix copiado", Toast.LENGTH_SHORT).show();
    }



   ///por marcio hamasaki
    public void Picpay(){




       Datum picpay =DATUM;
        preco= picpay.getPayment();
        namep=picpay.getProvider();
        String url = String.valueOf("https://picpay.me/" +namep.getLinkPicpay())+ "/"+String.valueOf(preco.getTotal()  );
        Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
        startActivity( intent );

    }

    ///por marcio hamasaki
    public void mercado_pago(){

        Datum mp1 =DATUM;
        mp=mp1.getProvider();
        String url1 = String.valueOf("https://mpago.la/pos/" +mp.getLinkMp());
        Intent intent2 = new Intent( Intent.ACTION_VIEW, Uri.parse( url1 ) );
        startActivity( intent2 );


    }



}
