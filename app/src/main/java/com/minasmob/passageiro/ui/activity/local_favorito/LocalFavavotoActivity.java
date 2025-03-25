package com.minasmob.passageiro.ui.activity.local_favorito;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.common.Constants;
import com.minasmob.passageiro.common.LocaleHelper;
import com.minasmob.passageiro.data.network.model.AddressResponse;
import com.minasmob.passageiro.data.network.model.UserAddress;
import com.minasmob.passageiro.ui.activity.main.MainActivity;
import com.minasmob.passageiro.ui.adiciona_enderecof.AddEnderecosActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.SRC_ADD;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.SRC_LAT;
import static com.minasmob.passageiro.common.Constants.RIDE_REQUEST.SRC_LONG;

public class LocalFavavotoActivity extends BaseActivity implements LocalFavoritoIView {


    @BindView(R.id.home_status)
    TextView homeStatus;
    @BindView(R.id.home_address)
    TextView homeAddress;
    @BindView(R.id.work_status)
    TextView workStatus;
    @BindView(R.id.work_address)
    TextView workAddress;


    private String type = "home";
    private String language;
    private LocalFavoritoPresenter<LocalFavavotoActivity> presenter = new LocalFavoritoPresenter<>();
    private UserAddress work = null, home = null;

    @Override
    public int getLayoutId() {
        return R.layout.activity_local_favorito;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        presenter.attachView(this);

        // Activity title will be updated after the locale has changed in Runtime
        setTitle(getString(R.string.settings));
        showLoading();
        presenter.address();


    }



    @Override
    public void onSuccessAddress(Object object) {
        presenter.address();
    }

    @Override
    public void onLanguageChanged(Object object) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        LocaleHelper.setLocale(getApplicationContext(), language);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK));
        this.overridePendingTransition(R.anim.rotate_in, R.anim.rotate_out);
    }

    @Override
    public void onSuccess(AddressResponse address) {
        if (address.getHome().isEmpty()) {
            homeAddress.setText("");
            homeStatus.setText(getString(R.string.add));
            homeStatus.setTag("add");
            home = null;
        } else {
            home = address.getHome().get(address.getHome().size() - 1);
            homeAddress.setText(home.getAddress());
            homeStatus.setText(getString(R.string.delete));
        }

        if (address.getWork().isEmpty()) {
            workAddress.setText("");
            workStatus.setText(getString(R.string.add));
            workStatus.setTag("add");
            work = null;
        } else {
            work = address.getWork().get(address.getWork().size() - 1);
            workAddress.setText(work.getAddress());
            workStatus.setText(getString(R.string.delete));
        }
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @OnClick({R.id.home_status, R.id.work_status})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_status:
                if (home == null) {
                    type = "home";
                    Intent intent = new Intent(this, AddEnderecosActivity.class);
                    intent.putExtra("actionName", Constants.LocationActions.SELECT_HOME);
                    startActivityForResult(intent, REQUEST_PICK_LOCATION);
                } else {
                    new AlertDialog.Builder(this)
                            .setMessage(getString(R.string.are_sure_you_want_to_delete))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(getString(R.string.delete), (dialog, whichButton) -> {
                                showLoading();
                                presenter.deleteAddress(home.getId(), new HashMap<>());
                            }).setNegativeButton(getString(R.string.no), null).show();
                }
                break;
            case R.id.work_status:
                if (work == null) {
                    type = "work";
                    Intent workIntent = new Intent(this, AddEnderecosActivity.class);
                    workIntent.putExtra("actionName", Constants.LocationActions.SELECT_WORK);
                    startActivityForResult(workIntent, REQUEST_PICK_LOCATION);
                } else {
                    new AlertDialog.Builder(this)
                            .setMessage(getString(R.string.are_sure_you_want_to_delete))
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(getString(R.string.delete), (dialog, whichButton) -> {
                                showLoading();
                                presenter.deleteAddress(work.getId(), new HashMap<>());
                            }).setNegativeButton(getString(R.string.no), null).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_LOCATION) if (resultCode == Activity.RESULT_OK) {
            if (data != null) {
                showLoading();
                HashMap<String, Object> map = new HashMap<>();
                map.put("type", type);
                map.put("address", data.getStringExtra(SRC_ADD));
                map.put("latitude", data.getDoubleExtra(SRC_LAT, 0.0));
                map.put("longitude", data.getDoubleExtra(SRC_LONG, 0.0));
                presenter.addAddress(map);
            }

        }

    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }
}