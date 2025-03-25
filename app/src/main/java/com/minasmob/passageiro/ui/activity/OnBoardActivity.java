package com.minasmob.passageiro.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.ui.activity.login.EmailActivity;
import com.minasmob.passageiro.data.network.model.WalkThrough;
import com.minasmob.passageiro.ui.activity.main.MainActivity;
import com.minasmob.passageiro.ui.activity.main.MainPresenter;
import com.minasmob.passageiro.ui.activity.register.RegisterActivity;
import com.minasmob.passageiro.ui.verifica_codigo.VerificaActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OnBoardActivity extends BaseActivity  {
    private MainPresenter<MainActivity> mainPresenter = new MainPresenter<>();

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView( R.id.logo )
     ImageView logo;

    //@BindView(R.id.layoutDots)
    LinearLayout layoutDots;
    private MyViewPagerAdapter adapter;
    private int dotsCount;
    private ImageView[] dots;
    private AlertDialog mDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_on_board;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);



    }



    @OnClick({R.id.sign_in, R.id.sign_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_in:
                startActivity(new Intent(this, EmailActivity.class));
                break;
            case R.id.sign_up:
                startActivity(new Intent(this, VerificaActivity.class));
                //startActivity(new Intent(this, PrincipalTelefone.class));
                break;

        }
    }


    public class MyViewPagerAdapter extends PagerAdapter {
        List<WalkThrough> list;
        Context context;

        MyViewPagerAdapter(Context context, List<WalkThrough> list) {
            this.list = list;
            this.context = context;

        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            View itemView = LayoutInflater.from(container.getContext()).inflate(R.layout.pager_item, container, false);
            WalkThrough walk = list.get(position);

            TextView title = itemView.findViewById(R.id.title);
            TextView description = itemView.findViewById(R.id.description);
            ImageView imageView = itemView.findViewById(R.id.img_pager_item);

            title.setText(walk.title);
            description.setText(walk.description);
            Glide.with(context).load(walk.drawable).into(imageView);
            container.addView(itemView);

            return itemView;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
