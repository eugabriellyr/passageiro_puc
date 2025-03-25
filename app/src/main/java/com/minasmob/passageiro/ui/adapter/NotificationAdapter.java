package com.minasmob.passageiro.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.minasmob.passageiro.R;
//import com.setemix.passageiro.data.network.model.NotificationManager;
import com.minasmob.passageiro.data.network.model.PromocaoManager;


import java.util.List;


public class NotificationAdapter extends  RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {


    private List<PromocaoManager> notifications;





    private Context mContext;

    public NotificationAdapter(List<PromocaoManager> notifications) {
        this.notifications = notifications;



    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();


        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_notifications, parent, false));
    }



    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TextView showMore = holder.tvShowMore;
        Button lk = holder.links;







        Glide.with(mContext)
                .load(notifications.get(position).getImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_document_placeholder)
                        .dontAnimate().error(R.drawable.ic_document_placeholder))

                .into(holder.ivNotificationImg);
        holder.ivNotificationDesc.setText(notifications.get(position).getDescription());

        holder.ivNotificationDesc.post(() -> {

        });

        showMore.setOnClickListener(v -> {



           if (showMore.getText().toString().equals(mContext.getString(R.string.show_more))) {

                showMore.setText(mContext.getString(R.string.show_less));
                holder.ivNotificationDesc.setMaxLines(Integer.MAX_VALUE);

            } else {
                showMore.setText(mContext.getString(R.string.show_more));
                holder.ivNotificationDesc.setMaxLines(3);

                holder.ivNotificationDesc.setEllipsize(TextUtils.TruncateAt.END);


            }
        });


         // TODO MARCIO HAMASAKI
        int a= notifications.get(position).getld();
        int b = 0;


        if (a  >  b){

            lk.setOnClickListener(v -> {


                Toast.makeText( mContext,"direcionando...",Toast.LENGTH_LONG ).show();
                String url = String.valueOf(notifications.get(position).getDescription());
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                mContext.startActivity(intent);


            });

        }else{
            lk.setOnClickListener(v -> {


                Toast.makeText( mContext,"direcionando...",Toast.LENGTH_LONG ).show();
                String url = String.valueOf("https://api.whatsapp.com/send?phone=55" + notifications.get(position).getWhts());
                Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse( url ) );
                mContext.startActivity(intent);


            });


        }






    }



    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivNotificationImg;
        private TextView ivNotificationDesc;
        private TextView tvShowMore;
        Button links;


        MyViewHolder(View view) {
            super(view);



            ivNotificationDesc = view.findViewById(R.id.ivNotificationDesc);
            ivNotificationImg = view.findViewById(R.id.ivNotificationImg);
            tvShowMore = view.findViewById(R.id.tvShowMore);
            links = view.findViewById(R.id.links);




        }






    }


}
