package com.minasmob.passageiro.ui.activity.promocoes_manager;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.network.model.PromocaoManager;
import com.minasmob.passageiro.ui.adapter.NotificationAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PromocaoManagerActivity extends BaseActivity implements PromocaoManagerIView {

    @BindView(R.id.rvNotificationManager)
    RecyclerView rvNotificationManager;


    private PromocaoManagerPresenter<PromocaoManagerActivity> presenter = new PromocaoManagerPresenter<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_promocao_manager;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        presenter.attachView(this);
        setTitle(getString(R.string.notification_manager));

        presenter.getPromocaoManager();








    }









    @Override
    public void onSuccess(List<PromocaoManager> managers) {
        rvNotificationManager.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvNotificationManager.setAdapter(new NotificationAdapter(managers));












    }

    @Override
    public int getItemCount() {
        return 0;
    }


    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}
