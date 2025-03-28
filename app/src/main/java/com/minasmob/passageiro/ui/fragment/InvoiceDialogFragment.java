package com.minasmob.passageiro.ui.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseBottomSheetDialogFragment;
import com.minasmob.passageiro.common.Constants;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.Datum;
import com.minasmob.passageiro.data.network.model.Payment;
import com.minasmob.passageiro.data.network.model.ServiceType;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.minasmob.passageiro.MvpApplication.DATUM;
import static com.minasmob.passageiro.data.SharedHelper.getKey;

public class InvoiceDialogFragment extends BaseBottomSheetDialogFragment {

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
    @BindView(R.id.total)
    TextView total;
    @BindView(R.id.payable)
    TextView payable;
    @BindView(R.id.close)
    Button close;
    @BindView(R.id.time_fare)
    TextView timeFare;
    @BindView(R.id.tips)
    TextView tips;
    @BindView(R.id.tips_layout)
    LinearLayout tipsLayout;
    @BindView(R.id.distance_constainer)
    LinearLayout distanceConstainer;
    @BindView(R.id.time_container)
    LinearLayout timeContainer;
    @BindView(R.id.wallet_deduction)
    TextView walletDeduction;
    @BindView(R.id.discount)
    TextView discount;
    @BindView(R.id.walletLayout)
    LinearLayout walletLayout;
    @BindView(R.id.discountLayout)
    LinearLayout discountLayout;
    @BindView(R.id.llWaitingAmountContainer)
    LinearLayout llWaitingAmountContainer;
    @BindView(R.id.llTolleChargeContainer)
    LinearLayout llTolleChargeContainer;
    @BindView(R.id.llRoundOffContainer)
    LinearLayout llRoundOffContainer;
    @BindView(R.id.tvWaitingAmount)
    TextView tvWaitingAmount;
    @BindView(R.id.tvTollCharges)
    TextView tvTollCharges;
    @BindView(R.id.tvRoundOff)
    TextView tvRoundOff;
    @BindView(R.id.desconto)
    TextView desconto;
    @BindView(R.id.porta_m)
    TextView portam;

    public InvoiceDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_invoice_dialog;
    }

    @SuppressLint({"StringFormatInvalid", "UseRequireInsteadOfGet"})
    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);

        if (DATUM != null) {
            Datum datum = DATUM;
            bookingId.setText(datum.getBookingId());
            if (SharedHelper.getKey(Objects.requireNonNull(getContext()), "measurementType").equalsIgnoreCase
                    (Constants.MeasurementType.KM)) {
                if (datum.getDistance() > 1 || datum.getDistance() > 1.0) {
                    distance.setText(String.format("%s %s", datum.getDistance(), Constants.MeasurementType.KM));
                } else {
                    distance.setText(String.format("%s %s", datum.getDistance(), getString(R.string.km)));
                }
            } else {
                if (datum.getDistance() > 1 || datum.getDistance() > 1.0) {
                    distance.setText(String.format("%s %s", datum.getDistance(), Constants.MeasurementType.MILES));
                } else {
                    distance.setText(String.format("%s %s", datum.getDistance(), getString(R.string.mile)));
                }
            }

         /*   int t_a = datum.getTempo_inicio();
            int t_b = datum.getTempo_final();
            int resultado;
            resultado = t_b - t_a;*/
            travelTime.setText(String.valueOf(datum.getTravelTime() +" minutos"));

            Payment payment = datum.getPayment();
            if (payment != null) {
                fixed.setText(getNewNumberFormat(payment.getPayable()));
                tax.setText(getNewNumberFormat(payment.getTax()));
                double pastTripTotal = payment.getTotal() + payment.getTips();
                total.setText(getNewNumberFormat(pastTripTotal));
                desconto.setText(getNewNumberFormat(payment.getDesc()));
                portam.setText(getNewNumberFormat(payment.getPortaM()));
                Double payableValue = payment.getPayable() - (payment.getWallet() + payment.getDiscount());
                double pastTripPayable = payableValue + payment.getTips();
                payable.setText(getNewNumberFormat(pastTripPayable));

                if (payment.getTips() == 0 || payment.getTips() == 0.0)
                    tipsLayout.setVisibility(View.GONE);
                else {
                    tipsLayout.setVisibility(View.VISIBLE);
                    tips.setText(getNewNumberFormat(payment.getTips()));
                }

                if (payment.getWallet() == 0 || payment.getWallet() == 0.0)
                    walletLayout.setVisibility(View.GONE);
                else {
                    walletLayout.setVisibility(View.VISIBLE);
                    walletDeduction.setText(getNewNumberFormat(payment.getWallet()));
                }
                if (payment.getDiscount() == 0 || payment.getDiscount() == 0.0)
                    discountLayout.setVisibility(View.GONE);
                else {
                    discountLayout.setVisibility(View.VISIBLE);
                    discount.setText(getNewNumberFormat(payment.getDiscount()));
                }
                if (payment.getWaitingAmount() == 0 || payment.getWaitingAmount() == 0.0)
                    llWaitingAmountContainer.setVisibility(View.GONE);
                else {
                    llWaitingAmountContainer.setVisibility(View.VISIBLE);
                    tvWaitingAmount.setText(getNewNumberFormat(payment.getWaitingAmount()));
                }

                if (payment.getToll_charge() <= 0) {
                    llTolleChargeContainer.setVisibility(View.GONE);
                } else {
                    llTolleChargeContainer.setVisibility(View.VISIBLE);
                    tvTollCharges.setText(getNewNumberFormat(payment.getToll_charge()));
                }

                if (payment.getRoundOf() != 0) {
                    llRoundOffContainer.setVisibility(View.GONE);
                    tvRoundOff.setText(getNewNumberFormat(payment.getRoundOf()));
                } else llRoundOffContainer.setVisibility(View.GONE);

                ServiceType serviceType = datum.getServiceType();
                if (serviceType != null) {
                    String serviceCalculator = serviceType.getCalculator();
                    switch (serviceCalculator) {
                        case Constants.InvoiceFare.MINUTE:
                            distanceConstainer.setVisibility(View.GONE);
                            timeContainer.setVisibility(View.GONE);
                            timeFare.setText(getNewNumberFormat(payment.getMinute()));
                            break;
                        case Constants.InvoiceFare.HOUR:
                            distanceConstainer.setVisibility(View.GONE);
                            timeContainer.setVisibility(View.GONE);
                            timeFare.setText(getNewNumberFormat(payment.getHour()));
                            break;
                        case Constants.InvoiceFare.DISTANCE:
                            timeContainer.setVisibility(View.GONE);
                            if (payment.getDistance() == 0.0 || payment.getDistance() == 0)
                                distanceConstainer.setVisibility(View.GONE);
                            else {
                                distanceConstainer.setVisibility(View.GONE);
                                distanceFare.setText(getNewNumberFormat(payment.getDistance()));
                            }
                            break;
                        case Constants.InvoiceFare.DISTANCE_MIN:
                            timeContainer.setVisibility(View.GONE);
                            timeFare.setText(getNewNumberFormat(payment.getMinute()));
                            if (payment.getDistance() == 0.0 || payment.getDistance() == 0)
                                distanceConstainer.setVisibility(View.GONE);
                            else {
                                distanceConstainer.setVisibility(View.GONE);
                                distanceFare.setText(getNewNumberFormat(payment.getDistance()));
                            }
                            break;
                        case Constants.InvoiceFare.DISTANCE_HOUR:
                            timeContainer.setVisibility(View.GONE);
                            timeFare.setText(getNewNumberFormat(payment.getHour()));
                            if (payment.getDistance() == 0.0 || payment.getDistance() == 0)
                                distanceConstainer.setVisibility(View.GONE);
                            else {
                                distanceConstainer.setVisibility(View.GONE);
                                distanceFare.setText(getNewNumberFormat(payment.getDistance()));
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }

    @OnClick(R.id.close)
    public void onViewClicked() {
        dismiss();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
