package com.minasmob.passageiro.ui.fragment.book_ride;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.bumptech.glide.request.Request;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.minasmob.passageiro.data.network.model.AddPortaMalas;
import com.minasmob.passageiro.data.network.model.User;
import com.minasmob.passageiro.ui.activity.splash.SplashActivity;
import com.rbrooks.indefinitepagerindicator.IndefinitePagerIndicator;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseFragment;
import com.minasmob.passageiro.common.EqualSpacingItemDecoration;
import com.minasmob.passageiro.data.network.model.Datum;
import com.minasmob.passageiro.data.network.model.EstimateFare;
import com.minasmob.passageiro.data.network.model.Fav;
import com.minasmob.passageiro.data.network.model.PromoList;
import com.minasmob.passageiro.data.network.model.PromoResponse;
import com.minasmob.passageiro.data.network.model.Service;
import com.minasmob.passageiro.ui.activity.main.MainActivity;
import com.minasmob.passageiro.ui.activity.payment.PaymentActivity;
import com.minasmob.passageiro.ui.activity.wallet.WalletActivity;
import com.minasmob.passageiro.ui.adapter.CouponAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.minasmob.passageiro.BuildConfig.ATUALIZA;
import static com.minasmob.passageiro.MvpApplication.DATUM;
import static com.minasmob.passageiro.MvpApplication.RIDE_REQUEST;
import static com.minasmob.passageiro.MvpApplication.isCard;
import static com.minasmob.passageiro.MvpApplication.isCash;
import static com.minasmob.passageiro.MvpApplication.isDebitMachine;
import static com.minasmob.passageiro.MvpApplication.isMp;
import static com.minasmob.passageiro.MvpApplication.isPicPay;
import static com.minasmob.passageiro.MvpApplication.isPix;
import static com.minasmob.passageiro.common.Constants.BroadcastReceiver.INTENT_FILTER;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.DISTANCE_VAL;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.PAYMENT_MODE;
import static com.minasmob.passageiro.ui.activity.payment.PaymentActivity.PICK_PAYMENT_METHOD;

public class BookRideFragment extends BaseFragment implements BookRideIView {

    Unbinder unbinder;
    @BindView(R.id.schedule_ride)
    Button scheduleRide;
    @BindView(R.id.ride_now)
    Button rideNow;
    @BindView(R.id.tvEstimatedFare)
    TextView tvEstimatedFare;
    @BindView(R.id.use_wallet)
    CheckBox useWallet;
    @BindView(R.id.estimated_image)
    ImageView estimatedImage;
    @BindView(R.id.view_coupons)
    TextView viewCoupons;
    @BindView(R.id.estimated_payment_mode)
    TextView estimatedPaymentMode;
    @BindView(R.id.tv_change)
    TextView tvChange;
    @BindView(R.id.wallet_balance)
    TextView walletBalance;
    @BindView(R.id.llEstimatedFareContainer)
    LinearLayout llEstimatedFareContainer;
    @BindView( R.id.bloqueado )
    TextView bloqueado;
    @BindView( R.id.pg )
    Button pgcarteira;
    @BindView( R.id.lblcupons )
    LinearLayout lblcupon;
    @BindView( R.id.lblmudar )
    LinearLayout lblmuda;
    @BindView(R.id.lblportamalas)
    LinearLayout lblportamalas;
    @BindView( R.id.tvTx )
    TextView tvtx;
    @BindView( R.id.txtTar )
    TextView txttar;
    @BindView( R.id.txtSaldo )
    TextView txtsaldo;

    @BindView( R.id.txtfav )
    TextView txtfav;


    @BindView( R.id.favoritos )
    Button txtname;
    @BindView( R.id.favorito1 )
    Button favorito1;
    @BindView( R.id.favorito2 )
    Button favorito2;
    @BindView( R.id.favorito3 )
    Button favorito3;
    @BindView( R.id.favorito4 )
    Button favorito4;
    @BindView( R.id.favorito5 )
    Button favorito5;
    @BindView( R.id.favorito6 )
    Button favorito6;
    @BindView( R.id.favorito7 )
    Button favorito7;
    @BindView( R.id.favorito8 )
    Button favorito8;
    @BindView( R.id.favorito9 )
    Button favorito9;
    @BindView( R.id.favorito10 )
    Button favorito10;
    @BindView(R.id.add_porta_malas)
    CheckBox add_porta_malas;
    @BindView(R.id.add_pet)
    CheckBox add_pet;
    @BindView(R.id.valor_add)
    TextView valor_add;
    @BindView(R.id.valor_addP)
    TextView valor_pet;
    @BindView(R.id.obs)
    EditText obs;

    String txtfav1;
    String txtfav2;
    String txtfav3;
    String txtfav4;
    String txtfav5;
    String txtfav6;
    String txtfav7;
    String txtfav8;
    String txtfav9;
    String txtfav10;


    private Datum datum = DATUM;
    double taxa ;
    private int lastSelectCoupon = 0;
    private String mCouponStatus;
    private String favoritoss;
    private String servico ;
    private String Obs;

    int idfav;
    double addM;
    double addP;
    private String paymentMode;
    private Double estimatedFare ;
    private Double vrtx ;
    double total_add;
    private BookRidePresenter<BookRideFragment> presenter = new BookRidePresenter<>();


    private CouponListener mCouponListener = new CouponListener() {
        @Override
        public void couponClicked(int pos, PromoList promoList, String promoStatus) {
            if (!promoStatus.equalsIgnoreCase(getString(R.string.remove))) {
                lastSelectCoupon = promoList.getId();

                viewCoupons.setText(promoList.getPromoCode());
                viewCoupons.setTextColor(getResources().getColor(R.color.colorAccent));
                viewCoupons.setBackgroundResource(R.drawable.coupon_transparent);
                mCouponStatus = viewCoupons.getText().toString();

                Double discountFare = (estimatedFare * promoList.getPercentage()) / 100;

                if (discountFare > promoList.getMaxAmount()) {
                    tvEstimatedFare.setText(String.format("%s", getNewNumberFormat(estimatedFare - promoList.getMaxAmount() )));
                } else {
                    tvEstimatedFare.setText(String.format("%s", getNewNumberFormat(estimatedFare - discountFare )));
                }
            } else {
                scaleView(viewCoupons, 0f, 0.9f);
                viewCoupons.setText(getString(R.string.view_coupon));
                viewCoupons.setBackgroundResource(R.drawable.button_round_accent);
                viewCoupons.setTextColor(getResources().getColor(R.color.white));
                mCouponStatus = viewCoupons.getText().toString();
                tvEstimatedFare.setText(String.format("%s", getNewNumberFormat(estimatedFare )));
            }
        }
    };



    public BookRideFragment() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_book_ride;
    }

    @SuppressLint({"SetTextI18n", "UseRequireInsteadOfGet"})
    @Override
    public View initView(View view) {
        pagamento();
        unbinder = ButterKnife.bind(this, view);
        presenter.attachView(this);
        presenter.favorito();
        presenter.add_porta_malas();
        Bundle args = getArguments();

        //double limit = -1;
        //TODO Marcio hamasaki aviso de cancelamento com bloqueio



        if (args != null) {
            String serviceName = args.getString("service_name");
            Service service = (Service) args.getSerializable("mService");
            EstimateFare estimateFare = (EstimateFare) args.getSerializable("estimate_fare");
            servico = serviceName = args.getString("service_name");
            double walletAmount = Objects.requireNonNull(estimateFare).getWalletBalance();
            double limitAmount = Objects.requireNonNull(service).getCancelar();
            // double limit = -6;
            if (serviceName != null && !serviceName.isEmpty()) {
                Glide
                        .with(Objects.requireNonNull(getContext()))
                        .load(Objects.requireNonNull(service).getImage())
                        .apply(RequestOptions
                                .placeholderOf(R.drawable.ic_car)
                                .dontAnimate()
                                .override(100, 100)
                                .error(R.drawable.ic_car))
                        .into(estimatedImage);
                estimatedFare = estimateFare.getEstimatedFare();
                tvEstimatedFare.setText(getNewNumberFormat(estimatedFare ));
                tvtx.setText(getNewNumberFormat(walletAmount ));
                txtsaldo.setText("SALDO DEVEDOR É");
                txttar.setText("Tarifa estimada");


                // txtname.setText( "Todos" );



                if(walletAmount < -limitAmount){

                    rideNow.setVisibility( View.GONE );
                    lblcupon.setVisibility( View.GONE );
                    lblmuda.setVisibility( View.GONE );
                    // scheduleRide.setVisibility( View.GONE );
                    txtsaldo.setVisibility( View.VISIBLE );
                    txttar.setVisibility( View.GONE );
                    tvEstimatedFare.setVisibility( View.GONE );
                    tvtx.setVisibility( View.VISIBLE );
                    bloqueado.setVisibility( View.VISIBLE );
                    pgcarteira.setVisibility( View.VISIBLE );
                    bloqueado.setText( String.valueOf( "Limite de Cancelamentos excedidos! \nestamos verificando motivo, \npara liberar pague saldo negativo em sua carteira." ) );
                }else{
                    lblcupon.setVisibility( View.VISIBLE );
                    lblmuda.setVisibility( View.VISIBLE );
                    // scheduleRide.setVisibility( View.VISIBLE );
                    rideNow.setVisibility( View.VISIBLE );
                    tvEstimatedFare.setVisibility( View.VISIBLE );
                    tvtx.setVisibility( View.GONE );
                    txtsaldo.setVisibility( View.GONE );
                    txttar.setVisibility( View.VISIBLE );
                    pgcarteira.setVisibility( View.GONE );
                    bloqueado.setVisibility( View.GONE );



                }

                if (walletAmount == 0 ||walletAmount < -0) {
                    useWallet.setVisibility(View.GONE);
                    walletBalance.setVisibility(View.GONE);
                } else {
                    useWallet.setVisibility(View.VISIBLE);
                    walletBalance.setVisibility(View.VISIBLE);
                    walletBalance.setText(getNewNumberFormat(Double.parseDouble(String.valueOf(walletAmount)) ));

                }
                RIDE_REQUEST.put(DISTANCE_VAL, estimateFare.getDistance());
            }
        }
        scaleView(viewCoupons, 0f, 0.9f);
        return view;
    }

    @Override
    public void onSuccess(AddPortaMalas adicionais) {

        addM = adicionais.getAddM();
        addP = adicionais.getAddP();
        valor_add.setText(getNewNumberFormat(Double.parseDouble(String.valueOf(addM)) ));
        valor_pet.setText(getNewNumberFormat(Double.parseDouble(String.valueOf(addP)) ));
        if(addM > 0 ){
            lblportamalas.setVisibility(View.VISIBLE);
        }



    }

    public void pagamento(){
        ((MainActivity) Objects.requireNonNull(getActivity())).updatePaymentEntities();
        startActivityForResult(new Intent(getActivity(), PaymentActivity.class), PICK_PAYMENT_METHOD);
    }

    public void scaleView(View v, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                1f, 1f, // Start and end values for the X axis scaling
                startScale, endScale, // Start and end values for the Y axis scaling
                Animation.RELATIVE_TO_SELF, 0f, // Pivot point of X scaling
                Animation.RELATIVE_TO_SELF, 1f); // Pivot point of Y scaling
        anim.setFillAfter(true); // Needed to keep the result of the animation
        anim.setDuration(100);
        v.startAnimation(anim);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }

    //    @OnClick({R.id.schedule_ride, R.id.ride_now, R.id.view_coupons, R.id.tv_change})
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("UseRequireInsteadOfGet")
    @OnClick({R.id.ride_now, R.id.view_coupons, R.id.tv_change, R.id.pg,R.id.schedule_ride,R.id.favoritos,R.id.favorito1,R.id.favorito2,
            R.id.favorito3,R.id.favorito4,R.id.favorito5,R.id.favorito6,R.id.favorito7,R.id.favorito8,R.id.favorito9,R.id.favorito10,R.id.add_porta_malas,R.id.add_pet})
    public void onViewClicked(View view) {

        //  int  a =0 ;
        //  int b =1;

        switch (view.getId()) {

            case R.id.ride_now:

                sendRequest();
                rideNow.setVisibility(View.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Procurando agora!",
                        Toast.LENGTH_SHORT).show();

                break;
            case R.id.favoritos:

                txtfav.setText("Todos");
                favorito2.setVisibility( View.GONE );
                favorito3.setVisibility( View.GONE );
                favorito4.setVisibility( View.GONE );
                favorito5.setVisibility( View.GONE );
                favorito6.setVisibility( View.GONE );
                favorito7.setVisibility( View.GONE );
                favorito8.setVisibility( View.GONE );
                favorito9.setVisibility( View.GONE );
                favorito10.setVisibility( View.GONE );

                break;


            case R.id.favorito1:

                txtfav.setText( txtfav1 );
                favorito2.setVisibility( View.VISIBLE );
                favorito3.setVisibility( View.VISIBLE );
                favorito4.setVisibility( View.VISIBLE );
                favorito5.setVisibility( View.VISIBLE );
                favorito6.setVisibility( View.VISIBLE );
                favorito7.setVisibility( View.VISIBLE );
                favorito8.setVisibility( View.VISIBLE );
                favorito9.setVisibility( View.VISIBLE );
                favorito10.setVisibility( View.VISIBLE );


                break;

            case R.id.favorito2:

                txtfav.setText( txtfav2 );
                break;

            case R.id.favorito3:

                txtfav.setText( txtfav3 );
                break;

            case R.id.favorito4:

                txtfav.setText( txtfav4 );
                break;

            case R.id.favorito5:

                txtfav.setText( txtfav5 );
                break;

            case R.id.favorito6:

                txtfav.setText( txtfav6 );
                break;

            case R.id.favorito7:

                txtfav.setText( txtfav7 );
                break;

            case R.id.favorito8:

                txtfav.setText( txtfav8 );
                break;

            case R.id.favorito9:

                txtfav.setText( txtfav9 );
                break;

            case R.id.favorito10:

                txtfav.setText( txtfav10 );
                break;





            case R.id.pg:
                startActivityForResult(new Intent(getActivity(), WalletActivity.class), PICK_PAYMENT_METHOD);

                // startActivity(new Intent(BookRideFragment.this, EmailActivity.class));

                break;
            case R.id.view_coupons:
                showLoading();
                try {
                    presenter.getCouponList();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        hideLoading();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                break;
            case R.id.tv_change:
                ((MainActivity) Objects.requireNonNull(getActivity())).updatePaymentEntities();
                startActivityForResult(new Intent(getActivity(), PaymentActivity.class), PICK_PAYMENT_METHOD);
                break;

            case  R.id.add_porta_malas:

                if(add_porta_malas.isChecked() == true){
                    alertCancel();
                }

                break;
        }


        if (add_porta_malas.isChecked() == true &&  add_pet.isChecked() == true){
            tvEstimatedFare.setText(getNewNumberFormat(estimatedFare + addM  + addP));
            total_add = estimatedFare + addM + addP ;


        }else  if (add_porta_malas.isChecked() == false &&  add_pet.isChecked() == true){

            tvEstimatedFare.setText(getNewNumberFormat(estimatedFare   + addP));
            total_add = estimatedFare + addP ;

        }else if (add_porta_malas.isChecked() == true &&  add_pet.isChecked() == false){
            tvEstimatedFare.setText(getNewNumberFormat(estimatedFare   + addM));
            total_add = estimatedFare + addM ;

        }else{

            tvEstimatedFare.setText(getNewNumberFormat(estimatedFare ));
            total_add = estimatedFare ;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void alertCancel() {


                     new AlertDialog.Builder(getContext())

                    .setMessage("Atenção! Os cupons promocionais são apenas para valores em corridas de passageiros. Serviços adicionais como uso de porta malas são cobrados a parte.")
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.ok), (dialog, which) -> {
                     }).show();



    }




    private Dialog couponDialog(PromoResponse promoResponse) {
        @SuppressLint("UseRequireInsteadOfGet") BottomSheetDialog couponDialog = new BottomSheetDialog(Objects.requireNonNull(getContext()), R.style.SheetDialog);
        couponDialog.setCanceledOnTouchOutside(true);
        couponDialog.setCancelable(true);
        couponDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        couponDialog.setContentView(R.layout.activity_coupon_dialog);
        RecyclerView couponView = couponDialog.findViewById(R.id.coupon_rv);
        IndefinitePagerIndicator indicator = couponDialog.findViewById(R.id.recyclerview_pager_indicator);
        List<PromoList> couponList = promoResponse.getPromoList();
        if (couponList != null && !couponList.isEmpty()) {
            CouponAdapter couponAdapter = new CouponAdapter(getActivity(), couponList,
                    mCouponListener, couponDialog, lastSelectCoupon, mCouponStatus);
            assert couponView != null;
            couponView.setLayoutManager(new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.HORIZONTAL, false));
            couponView.setItemAnimator(new DefaultItemAnimator());
            couponView.addItemDecoration(new EqualSpacingItemDecoration(16,
                    EqualSpacingItemDecoration.HORIZONTAL));
            Objects.requireNonNull(indicator).attachToRecyclerView(couponView);
            couponView.setAdapter(couponAdapter);
            couponAdapter.notifyDataSetChanged();
        }
        couponDialog.setOnKeyListener((dialogInterface, keyCode, keyEvent) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                new BottomSheetDialog(getContext()).dismiss();
                Log.d("TAG", "--------- Do Something -----------");
                return true;
            }
            return false;
        });
        Window window = couponDialog.getWindow();
        assert window != null;
        WindowManager.LayoutParams param = window.getAttributes();
        param.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        window.setAttributes(param);
        window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);
        couponDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        return couponDialog;
    }







    public void sendRequest() {
        showLoading();
        HashMap<String, Object> map = new HashMap<>(RIDE_REQUEST);
        // pega informação vindo do txtlbl txtfav
        favoritoss = txtfav.getText().toString();
        Obs = obs.getText().toString();
         


        if(favoritoss != null){

            map.put("favorito",favoritoss);

        }else{


            map.put("favorito",0);

        }

        map.put("servico",servico);
        map.put("comments",Obs);
        map.put("use_wallet", useWallet.isChecked() ? 1 : 0);
        map.put("promocode_id", lastSelectCoupon);
        map.put("add_portamalas",add_porta_malas.isChecked() ? 1 : 0);
        map.put("add_pet",add_pet.isChecked() ? 1 : 0);
        map.put("add_portamalas_valor",total_add );


        if (paymentMode != null && !paymentMode.equalsIgnoreCase(""))
            map.put("payment_mode", paymentMode);
        else map.put("payment_mode", "CASH");

        try {
            presenter.rideNow(map);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onSuccess(@NonNull Object object) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        baseActivity().sendBroadcast(new Intent(INTENT_FILTER));
    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @Override
    public void onSuccessCoupon(PromoResponse promoResponse) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (promoResponse != null && promoResponse.getPromoList() != null
                && !promoResponse.getPromoList().isEmpty()) couponDialog(promoResponse).show();
        else Toast.makeText(baseActivity(), "Cupons Indisponíveis", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(Fav fav) {


        if(fav.getFv() > 0){

            txtname.setVisibility( View.VISIBLE );
            favorito1.setVisibility( View.VISIBLE );
            // estimatedImage.setVisibility( View.GONE );
            txtfav.setVisibility( View.VISIBLE);
        }else{
            //estimatedImage.setVisibility( View.VISIBLE );
            txtfav.setVisibility( View.GONE);
            favorito1.setVisibility( View.GONE );
            favorito2.setVisibility( View.GONE );
            favorito3.setVisibility( View.GONE );
            favorito4.setVisibility( View.GONE );
            favorito5.setVisibility( View.GONE );
            favorito6.setVisibility( View.GONE );
            favorito7.setVisibility( View.GONE );
            favorito8.setVisibility( View.GONE );
            favorito9.setVisibility( View.GONE );
            favorito10.setVisibility( View.GONE );
            txtname.setVisibility( View.GONE );
        }





        // txtname3.setText(String.format( String.valueOf( fav.getFav() ) ));
        txtfav1 = String.valueOf( String.valueOf( fav.getFv() + " = "+fav.getFav()) );
        txtfav2 = String.valueOf( String.valueOf( fav.getFv2() + " = "+fav.getFav2()) );
        txtfav3 = String.valueOf( String.valueOf( fav.getFv3() + " = "+fav.getFav3()) );
        txtfav4 = String.valueOf( String.valueOf( fav.getFv4() + " = "+fav.getFav4()) );
        txtfav5 = String.valueOf( String.valueOf( fav.getFv5() + " = "+fav.getFav5()) );
        txtfav6 = String.valueOf( String.valueOf( fav.getFv6() + " = "+fav.getFav6()) );
        txtfav7 = String.valueOf( String.valueOf( fav.getFv7() + " = "+fav.getFav7()) );
        txtfav8 = String.valueOf( String.valueOf( fav.getFv8() + " = "+fav.getFav8()) );
        txtfav9 = String.valueOf( String.valueOf( fav.getFv9() + " = "+fav.getFav9()) );
        txtfav10 = String.valueOf( String.valueOf( fav.getFv10() + " = "+fav.getFav10()) );
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PAYMENT_METHOD && resultCode == Activity.RESULT_OK) {
            RIDE_REQUEST.put(PAYMENT_MODE, data.getStringExtra("payment_mode"));
            paymentMode = data.getStringExtra("payment_mode");


            System.out.println("RRR PAMENT_MODE = " + data.getStringExtra("payment_mode"));


            initPayment(estimatedPaymentMode);
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        initPayment(estimatedPaymentMode);
        tvChange.setVisibility((!isCard && !isDebitMachine&& !isPicPay && !isPix && !isMp) && isCash ? View.GONE : View.VISIBLE);
    }

    public interface CouponListener {
        void couponClicked(int pos, PromoList promoList, String promoStatus);
    }
}
