package com.minasmob.passageiro.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.minasmob.passageiro.BuildConfig;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.ForgotResponse;
import com.minasmob.passageiro.data.network.model.Token;
import com.minasmob.passageiro.ui.activity.forgot_password.ForgotPasswordActivity;
import com.minasmob.passageiro.ui.activity.main.MainActivity;
import com.minasmob.passageiro.ui.activity.register.RegisterActivity;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class PasswordActivity extends BaseActivity implements LoginIView {

    public static String TAG = "";
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private String email;
    private loginPresenter<PasswordActivity> presenter = new loginPresenter();

    @Override
    public int getLayoutId() {
        return R.layout.activity_password;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mToolbar.setNavigationOnClickListener(v -> finish());

        presenter.attachView(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) email = extras.getString("email");
        if (BuildConfig.DEBUG) password.setText("");
    }

    private void login() {
        try {
            if (password.getText().toString().isEmpty()) {
                Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
                return;
            }
            if (email.isEmpty()) {
                Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
                return;
            }
            if (SharedHelper.getKey(this, "device_token").isEmpty()) {
                FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        SharedHelper.putKey(this, "device_token", task.getResult().getToken());
                        Log.d("FCM_TOKEN", task.getResult().getToken());
                    } else Log.w("PasswordActivity", "getInstanceId failed", task.getException());
                });
                return;
            }
            HashMap<String, Object> map = new HashMap<>();
            map.put("grant_type", "password");
            map.put("username", email);
            map.put("password", password.getText().toString());
            map.put("client_secret", BuildConfig.CLIENT_SECRET);
            map.put("client_id", BuildConfig.CLIENT_ID);
            map.put("device_token", SharedHelper.getKey(this, "device_token", "No device"));
            map.put("device_id", SharedHelper.getKey(this, "device_id", "2350"));
            map.put("device_type", BuildConfig.DEVICE_TYPE);

            showLoading();
            presenter.login(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.sign_up, R.id.forgot_password, R.id.next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign_up:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.forgot_password:
                showLoading();
                presenter.forgotPassword(email);
                break;
            case R.id.next:

                login();
                break;
        }
    }

    @Override
    public void onSuccess(Token token) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        hideLoading();
        String accessToken = token.getTokenType() + " " + token.getAccessToken();
        SharedHelper.putKey(this, "access_token", accessToken);
        SharedHelper.putKey(this, "refresh_token", token.getRefreshToken());
        SharedHelper.putKey(this, "logged_in", true);
        finishAffinity();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onSuccess(ForgotResponse forgotResponse) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        hideLoading();
        Toast.makeText(this, forgotResponse.getMessage(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        intent.putExtra("email", forgotResponse.getUser().getEmail());
        intent.putExtra("otp", forgotResponse.getUser().getOtp());
        intent.putExtra("id", forgotResponse.getUser().getId());
        startActivity(intent);
    }

    @Override
    public void onError(Throwable e) {

        hideLoading();
        if (e != null)
            TAG = "PasswordActivity";

        Toasty.info(this, "Email ou senha Invalidos",Toast.LENGTH_LONG).show();
        onErrorBase(e);
        //TAG = "PasswordActivity";
        //handleError(e);
      //  Toasty.info(this, "Email ou Senha Invalidos",Toast.LENGTH_LONG).show();
    }

   @Override
    protected void onDestroy() {
       hideLoading();
        presenter.onDetach();
        super.onDestroy();
    }
}
