package com.minasmob.passageiro.ui.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.minasmob.passageiro.MvpApplication;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.common.Constants;
import com.minasmob.passageiro.data.network.model.RequestedDataItem;
//import com.moob.motorista.ui.activity.status.Status_saqueActivity;

import java.util.List;

public class RequestAmtAdapter extends RecyclerView.Adapter<RequestAmtAdapter.MyViewHolder> {

    private List<RequestedDataItem> mRequestedDataItems;
   //private TransferenciaActivity.RequestedItem mListener;

 //   public RequestAmtAdapter(List<RequestedDataItem> requestedDataItems, TransferenciaActivity.RequestedItem mItemListener) {
 //       this.mRequestedDataItems = requestedDataItems;
      //  this.mListener = mItemListener;
 //   }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.list_item_wallet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvId.setText(mRequestedDataItems.get(position).getAliasId());
        holder.tvDate.setText(String.format("%s%s", Constants.Currency,
                MvpApplication.getInstance().getNewNumberFormat(mRequestedDataItems.get(position).getAmount())));
        holder.ivDelete.setVisibility(View.VISIBLE);
        holder.ivDelete.setOnClickListener(view -> {
           // mListener.onDelete(mRequestedDataItems.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return mRequestedDataItems.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId, tvDate;
        private ImageView ivDelete;

        private MyViewHolder(View view) {
            super(view);
            tvId = view.findViewById(R.id.tvId);
            tvDate = view.findViewById(R.id.tvDate);
            ivDelete = view.findViewById(R.id.ivDelete);
        }
    }
}