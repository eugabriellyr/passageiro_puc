package com.minasmob.passageiro.ui.fragment.searching;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.minasmob.passageiro.MvpApplication;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseBottomSheetDialogFragment;
import com.minasmob.passageiro.data.network.model.DataResponse;
import com.minasmob.passageiro.data.network.model.Datum;
import com.minasmob.passageiro.ui.activity.main.MainActivity;

import java.util.HashMap;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.minasmob.passageiro.MvpApplication.DATUM;
import static com.minasmob.passageiro.common.Constants.BroadcastReceiver.INTENT_FILTER;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.DEST_ADD;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.DEST_LAT;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.DEST_LONG;
import static com.minasmob.passageiro.common.Constants.Status.EMPTY;

public class SearchingFragment extends BaseBottomSheetDialogFragment implements SearchingIView {

    private SearchingPresenter<SearchingFragment> presenter = new SearchingPresenter<>();
     
    double taxa_liga ;
    double taxa;

    public SearchingFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_searching;
    }

    @Override
    public void initView(View view) {

        setCancelable(false);
        getDialog().setOnShowListener(dialog -> {
            BottomSheetDialog d = (BottomSheetDialog) dialog;
            View bottomSheetInternal = d.findViewById(R.id.design_bottom_sheet);
            BottomSheetBehavior.from(bottomSheetInternal).setState(BottomSheetBehavior.STATE_EXPANDED);
        });
        ButterKnife.bind(this, view);
        presenter.attachView(this);



    }

    @Override
    public void onSuccess(DataResponse dataResponse) {
        
        double t = dataResponse.getPix();
        
        
        
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick(R.id.cancel)
    public void onViewClicked() {
        alertCancel();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void alertCancel() {



        Datum aviso =DATUM;

       // taxa_liga = u.getTaxa_liga();
        taxa = aviso.getServiceType().getTaxa_txt();
        if(taxa > 0){

            new AlertDialog.Builder(getContext())

                    .setMessage("Tem certeza que deseja Cancelar?\nO serviço cobra uma taxa de R$ "+aviso.getServiceType().getTaxa_txt()+"0\nEsse serviço tem limite de cancelamento de R$" + aviso.getServiceType().getCancelar()+"\napós atingir será bloqueado para saber mais vá em termos e ajuda! ")
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        if (DATUM != null) {
                            showLoading();
                            Datum datum = DATUM;
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("request_id", datum.getId());
                            presenter.cancelRequest(map);
                        }
                    }).setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.cancel())
                    .show();
        }else{

            new AlertDialog.Builder(getContext())

                    .setMessage("Tem certeza que deseja Cancelar? ")
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        if (DATUM != null) {
                            showLoading();
                            Datum datum = DATUM;
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("request_id", datum.getId());
                            presenter.cancelRequest(map);
                        }
                    }).setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.cancel())
                    .show();

        }

    }



    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public void onSuccess(Object object) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        MvpApplication.RIDE_REQUEST.remove(DEST_ADD);
        MvpApplication.RIDE_REQUEST.remove(DEST_LAT);
        MvpApplication.RIDE_REQUEST.remove(DEST_LONG);

        baseActivity().sendBroadcast(new Intent(INTENT_FILTER));
        ((MainActivity) Objects.requireNonNull(getContext())).changeFlow(EMPTY);
        dismissAllowingStateLoss();
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Override
    public void onError(Throwable e) {
        handleError(e);
        baseActivity().sendBroadcast(new Intent(INTENT_FILTER));
        ((MainActivity) Objects.requireNonNull(getContext())).changeFlow(EMPTY);
    }
}
