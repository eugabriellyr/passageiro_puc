package com.minasmob.passageiro.base;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.common.Constants;

import java.util.Calendar;

import static com.minasmob.passageiro.MvpApplication.RIDE_REQUEST;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.PAYMENT_MODE;

public abstract class BaseFragment extends Fragment implements MvpView {

    private View view;
    private BaseActivity mBaseActivity;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view == null) {
            view = inflater.inflate(getLayoutId(), container, false);
            initView(view);
        }

        return view;
    }

    protected abstract int getLayoutId();

    protected abstract View initView(View view);

    @Override
    public FragmentActivity baseActivity() {
        return getActivity();
    }

    @Override
    public void showLoading() {
        if (mBaseActivity != null) {
            mBaseActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (mBaseActivity != null) {
            mBaseActivity.hideLoading();
        }
    }

    protected void datePicker(DatePickerDialog.OnDateSetListener dateSetListener) {
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(baseActivity(), dateSetListener, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.show();
    }

    protected void timePicker(TimePickerDialog.OnTimeSetListener timeSetListener) {
        Calendar myCalendar = Calendar.getInstance();
        TimePickerDialog mTimePicker = new TimePickerDialog(getContext(), timeSetListener, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE), true);
        mTimePicker.show();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            this.mBaseActivity = (BaseActivity) context;
        }
    }

    protected void initPayment(TextView paymentMode) {
        if (RIDE_REQUEST.containsKey(PAYMENT_MODE))
            switch (RIDE_REQUEST.get(PAYMENT_MODE).toString()) {
                case Constants.PaymentMode.CASH:
                    paymentMode.setText(getString(R.string.cash));
                     //   paymentMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_money, 0, 0, 0);
                    break;

                //TODO
                case Constants.PaymentMode.DEBIT_MACHINE:
                    paymentMode.setText(getString(R.string.debit_machine));
                    //    paymentMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_money, 0, 0, 0);
                    break;



                case Constants.PaymentMode.PIC_PAY:
                    paymentMode.setText(getString(R.string.pic_pay));
                       // paymentMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_picpay, 0, 0, 0);
                    break;



                case Constants.PaymentMode.PIX:
                    paymentMode.setText(getString(R.string.add_pix));
                    //    paymentMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_money, 0, 0, 0);
                    break;

                case Constants.PaymentMode.M_P:
                    paymentMode.setText(getString(R.string.add_mp));
                    //    paymentMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_money, 0, 0, 0);
                    break;

            }
       // else {
          /*  if (isCash) {
                RIDE_REQUEST.put(PAYMENT_MODE, Constants.PaymentMode.CASH);
                paymentMode.setText(getString(R.string.cash));
                //  paymentMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_money, 0, 0, 0);
            } else if (isCard) {
                paymentMode.setText(R.string.add_card_);
                //  paymentMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_card, 0, 0, 0);

            }*/
       // }
    }

    protected void onErrorBase(Throwable t) {
        if (mBaseActivity != null) {
            mBaseActivity.onErrorBase(t);
        }
    }

    protected void handleError(Throwable e) {
        try {
            try {
                hideLoading();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (mBaseActivity != null) {
            mBaseActivity.handleError(e);
        }
    }

    @Override
    public void onSuccessLogout(Object object) {
        if (mBaseActivity != null) {
            mBaseActivity.onSuccessLogout(object);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (mBaseActivity != null) {
            mBaseActivity.onError(throwable);
        }
    }

    protected String getNewNumberFormat(double d) {
        return BaseActivity.getNumberFormat().format(d);
    }

}
