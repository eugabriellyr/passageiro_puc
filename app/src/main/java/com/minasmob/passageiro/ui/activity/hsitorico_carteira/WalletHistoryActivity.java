package com.minasmob.passageiro.ui.activity.hsitorico_carteira;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.common.EqualSpacingItemDecoration;
import com.minasmob.passageiro.data.network.model.Wallet;
import com.minasmob.passageiro.data.network.model.WalletResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalletHistoryActivity extends BaseActivity implements WalletHistoryIView {

    @BindView(R.id.rvWallet)
    RecyclerView rvWallet;
    @BindView(R.id.tvNoWalletData)
    TextView tvNoWalletData;

    private WalletHistoryPresenter<WalletHistoryActivity> presenter = new WalletHistoryPresenter<>();
    private List<Wallet> walletList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_passbook;
    }

    @Override
    public void initView() {
        presenter.attachView(this);
        ButterKnife.bind(this);

        setTitle(getString(R.string.passbook));
        showLoading();
        presenter.wallet();

    }

    @Override
    public void onSuccess(WalletResponse response) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (!response.getWallets().isEmpty()) {
            tvNoWalletData.setVisibility(View.GONE);
            walletList.clear();
            walletList.addAll(response.getWallets());
            WalletHistoryAdapter mWalletAdapter = new WalletHistoryAdapter(walletList);
            rvWallet.setLayoutManager(new LinearLayoutManager(WalletHistoryActivity.this,
                    LinearLayoutManager.VERTICAL, false));
            rvWallet.setVisibility(View.VISIBLE);
            rvWallet.setItemAnimator(new DefaultItemAnimator());
            rvWallet.addItemDecoration(new EqualSpacingItemDecoration(16, EqualSpacingItemDecoration.HORIZONTAL));
            rvWallet.setAdapter(mWalletAdapter);
        } else tvNoWalletData.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @Override
    public void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    private class WalletHistoryAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private List<Wallet> mWallet;

        WalletHistoryAdapter(List<Wallet> walletList) {
            this.mWallet = walletList;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_wallet_history, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Wallet item = mWallet.get(position);
            holder.tvId.setText(item.getTransactionAlias());
           /* holder.tvAmountVal.setText(String.format("%s %s", SharedHelper.getKey(WalletHistoryActivity.this, "currency"),
                    getNewNumberFormat(item.getAmount())));
            holder.tvBalanceVal.setText(String.format("%s %s", SharedHelper.getKey(WalletHistoryActivity.this, "currency"),
                    getNewNumberFormat(item.getCloseBalance())));*/

            holder.tvAmountVal.setText(getNumberFormat().format(item.getAmount()));
            holder.tvBalanceVal.setText(getNumberFormat().format(item.getCloseBalance()));
            holder.data.setText(item.getData());

        }

        @Override
        public int getItemCount() {
            return mWallet.size();
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        

        private TextView tvId, tvAmountVal, tvBalanceVal,data;

        MyViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            data = itemView.findViewById(R.id.data);
            tvAmountVal = itemView.findViewById(R.id.tvAmountVal);
            tvBalanceVal = itemView.findViewById(R.id.tvBalanceVal);
        }
    }
}
