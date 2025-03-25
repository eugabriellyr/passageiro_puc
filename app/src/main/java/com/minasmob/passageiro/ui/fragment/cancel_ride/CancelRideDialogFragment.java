package com.minasmob.passageiro.ui.fragment.cancel_ride;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseBottomSheetDialogFragment;
import com.minasmob.passageiro.common.CancelRequestInterface;
import com.minasmob.passageiro.data.network.model.CancelResponse;
import com.minasmob.passageiro.data.network.model.Datum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.minasmob.passageiro.MvpApplication.DATUM;
import static com.minasmob.passageiro.common.Constants.BroadcastReceiver.INTENT_FILTER;

public class CancelRideDialogFragment extends BaseBottomSheetDialogFragment implements CancelRideIView {

    @BindView(R.id.cancel_reason)
    EditText cancelReason;
    @BindView(R.id.dismiss)
    Button dismiss;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.rcvReason)
    RecyclerView rcvReason;
    double taxa ;

    private List<CancelResponse> cancelResponseList = new ArrayList<>();
    private ReasonAdapter adapter;
    private int last_selected_location = -1;

    private CancelRidePresenter<CancelRideDialogFragment> presenter = new CancelRidePresenter<>();
    private CancelRequestInterface callback;

    public CancelRideDialogFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public CancelRideDialogFragment(CancelRequestInterface callback) {
        this.callback = callback;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cancel_ride_dialog;
    }

    @Override
    public void initView(View view) {
        ButterKnife.bind(this, view);
        presenter.attachView(this);
        adapter = new ReasonAdapter(cancelResponseList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager
                (getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvReason.setLayoutManager(mLayoutManager);
        rcvReason.setItemAnimator(new DefaultItemAnimator());
        rcvReason.setAdapter(adapter);

        presenter.getReasons();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @OnClick({R.id.dismiss, R.id.submit})
    public void onViewClicked(View view) {


        switch (view.getId()) {
            case R.id.dismiss:
                dismiss();
                break;
            case R.id.submit:

                if (last_selected_location == -1) {

                    Toast.makeText(getContext(), getString(R.string.invalid_selection), Toast.LENGTH_SHORT).show();
                    return;
                }
                alertCancel();

                break;
        }
    }




    private void cancelRequest() {
        if (cancelReason.getText().toString().isEmpty() && (last_selected_location == cancelResponseList.size() - 1)) {
            Toast.makeText(getContext(), getString(R.string.please_enter_cancel_reason), Toast.LENGTH_SHORT).show();
            return;
        }

        if (DATUM != null) {
            Datum datum = DATUM;
            HashMap<String, Object> map = new HashMap<>();
            map.put("request_id", datum.getId());
            if ((last_selected_location == cancelResponseList.size() - 1))
                map.put("cancel_reason", cancelReason.getText().toString());
            else
                map.put("cancel_reason", cancelResponseList.get(last_selected_location).getReason());
            showLoading();
            presenter.cancelRequest(map);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void alertCancel() {



        Datum aviso =DATUM;
        taxa = aviso.getServiceType().getTaxa_txt();
        if(taxa > 0){

            new AlertDialog.Builder(getContext())

                    .setMessage("Tem certeza que deseja Cancelar?\nO serviço cobra uma taxa de R$ "+taxa+"0\nEsse serviço tem limite de cancelamento de R$" + aviso.getServiceType().getCancelar()+"0\napós atingir será bloqueado.\nPara informações, vá em suporte e ajuda. ")
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        cancelRequest();
                    }).setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.cancel())
                    .show();
        }else{

            new AlertDialog.Builder(getContext())

                    .setMessage("Tem certeza que deseja Cancelar?")
                    .setCancelable(true)
                    .setPositiveButton(getString(R.string.yes), (dialog, which) -> {
                        cancelRequest();
                    }).setNegativeButton(getString(R.string.no), (dialog, which) -> dialog.cancel())
                    .show();

        }

    }

    @Override
    public void onSuccess(Object object) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (DATUM != null)
            FirebaseMessaging.getInstance().unsubscribeFromTopic(String.valueOf(DATUM.getId()));
        getActivity().sendBroadcast(new Intent(INTENT_FILTER));
        callback.cancelRequestMethod();
        dismiss();
    }

    @Override
    public void onSuccess(List<CancelResponse> response) {
        cancelResponseList.addAll(response);
        addCommonReason();
    }

    @Override
    public void onReasonError(Throwable e) {
        addCommonReason();
    }

    private void addCommonReason() {
        //Common reason added here
        CancelResponse commonReason = new CancelResponse();
        commonReason.setReason("Outro Motivo");
        commonReason.setType("USER");
        commonReason.setStatus(1);
        commonReason.setId(0);
        cancelResponseList.add(commonReason);
        if (cancelResponseList.size() == 1) {
            last_selected_location = 0;
            cancelReason.setVisibility(View.VISIBLE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @Override
    public void onDestroyView() {
        presenter.onDetach();
        super.onDestroyView();
    }


    private class ReasonAdapter extends RecyclerView.Adapter<CancelRideDialogFragment.ReasonAdapter.MyViewHolder> {

        private List<CancelResponse> list;
        private Context mContext;

        private ReasonAdapter(List<CancelResponse> list) {
            this.list = list;
        }

        @NonNull
        @Override
        public CancelRideDialogFragment.ReasonAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            mContext = parent.getContext();
            return new CancelRideDialogFragment.ReasonAdapter.MyViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.cancel_reasons_inflate, parent, false));
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull CancelRideDialogFragment.ReasonAdapter.MyViewHolder holder, int position) {

            CancelResponse data = list.get(position);
            holder.tvReason.setText(data.getReason());

            if (last_selected_location == position) {
                holder.cbItem.setChecked(true);
            } else {
                holder.cbItem.setChecked(false);
            }

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            LinearLayout llItemView;
            TextView tvReason;
            CheckBox cbItem;

            MyViewHolder(View view) {
                super(view);
                llItemView = view.findViewById(R.id.llItemView);
                tvReason = view.findViewById(R.id.tvReason);
                cbItem = view.findViewById(R.id.cbItem);

                llItemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                if (position == list.size() - 1) {
                    cancelReason.setVisibility(View.VISIBLE);
                } else {
                    cancelReason.setVisibility(View.GONE);
                }
                last_selected_location = position;
                notifyDataSetChanged();
            }
        }
    }

}
