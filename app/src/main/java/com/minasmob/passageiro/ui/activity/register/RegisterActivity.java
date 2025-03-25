package com.minasmob.passageiro.ui.activity.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
//import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.minasmob.passageiro.BuildConfig;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.common.mask.MaskEditUtil;
import com.minasmob.passageiro.common.validator.ValidaCPF;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.RegisterResponse;
import com.minasmob.passageiro.data.network.model.SettingsResponse;
import com.minasmob.passageiro.ui.activity.main.MainActivity;
import com.minasmob.passageiro.ui.countrypicker.Country;
import com.minasmob.passageiro.ui.countrypicker.CountryPicker;
import com.minasmob.passageiro.ui.telefone.PrincipalTelefone;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class RegisterActivity extends BaseActivity implements RegisterIView {

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.txtReferralCode)
    EditText txtReferralCode;
    @BindView(R.id.cpf)
    EditText cpf;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.password_confirmation)
    EditText passwordConfirmation;
    @BindView(R.id.chkTerms)
    CheckBox chkTerms;
    @BindView(R.id.countryImage)
    ImageView countryImage;
    @BindView(R.id.countryNumber)
    TextView countryNumber;
    @BindView(R.id.phoneNumber)
    EditText phoneNumber;
    @BindView(R.id.lnrReferralCode)
    LinearLayout lnrReferralCode;
    String txtCf;
    private String countryDialCode = "+55";
    private CountryPicker mCountryPicker;
    private ValidaCPF validaCPF;

    private RegisterPresenter<RegisterActivity> registerPresenter = new RegisterPresenter();
    private boolean isEmailAvailable = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        registerPresenter.attachView(this);
        //Mascaras de CPF e Telefone//
        cpf.addTextChangedListener( MaskEditUtil.mask(cpf, MaskEditUtil.FORMAT_CPF));
        phoneNumber.addTextChangedListener(MaskEditUtil.mask(phoneNumber, MaskEditUtil.FORMAT_FONE));
        //Fim Mascaras de CPF e Telefone//
        // Activity title will be updated after the locale has changed in Runtime
        setTitle(getString(R.string.register));

        registerPresenter.getSettings();

        clickFunctions();

        setCountryList();
        phoneNumber.setText( PrincipalTelefone.telefone);
        Intent intentrecebe = getIntent();
        Bundle parametro = intentrecebe.getExtras();
        if(parametro != null){

            String codigoReferencia = parametro.getString("chave_valor");
            txtCf = codigoReferencia;
        }
       if (BuildConfig.DEBUG) {
            email.setText("");
            firstName.setText("");
            lastName.setText("");
            phoneNumber.setText("");
            password.setText("");
            passwordConfirmation.setText("");
           txtReferralCode.setText(txtCf);
       }



    }

    private void setCountryList() {
        mCountryPicker = CountryPicker.newInstance("Select Country");
        List<Country> countryList = Country.getAllCountries();
        Collections.sort(countryList, (s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()));
        mCountryPicker.setCountriesList(countryList);

        setListener();
    }


    private void setListener() {
        mCountryPicker.setListener((name, code, dialCode, flagDrawableResID) -> {
            countryNumber.setText(dialCode);
            countryDialCode = dialCode;
            countryImage.setImageResource(flagDrawableResID);
            mCountryPicker.dismiss();
        });

        countryImage.setOnClickListener(v -> mCountryPicker.show(getSupportFragmentManager(), "COUNTRY_PICKER"));

        countryNumber.setOnClickListener(v -> mCountryPicker.show(getSupportFragmentManager(), "COUNTRY_PICKER"));

        getUserCountryInfo();
    }

    private void getUserCountryInfo() {
        Country country = getDeviceCountry(RegisterActivity.this);
        countryImage.setImageResource(country.getFlag());
        countryNumber.setText(country.getDialCode());
        countryDialCode = country.getDialCode();
    }

    private void clickFunctions() {
        email.setOnFocusChangeListener((v, hasFocus) -> {
            isEmailAvailable = true;
            if (!hasFocus && !TextUtils.isEmpty(email.getText().toString()))
                if (Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches())
                    registerPresenter.verifyEmail(email.getText().toString().trim());
        });

        phoneNumber.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus && !TextUtils.isEmpty(phoneNumber.getText().toString()))
                registerPresenter.verifyCredentials(phoneNumber.getText().toString(), countryDialCode);
        });
    }

    @OnClick({R.id.next, R.id.lblTerms})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.next:
                if (validate()) {
                    //Country mCountry = getDeviceCountry(this);
                    // fbOtpVerify(mCountry.getCode(), mCountry.getDialCode(), phoneNumber.getText().toString());
                    register(); // CONTROLD
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
            case R.id.lblTerms:
                showTermsConditionsDialog();
                break;
        }
    }

    private void showTermsConditionsDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getText(R.string.terms_and_conditions));

        WebView wv = new WebView(this);
        wv.loadUrl(BuildConfig.TERMS_CONDITIONS);
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        alert.setView(wv);
        alert.setNegativeButton("Close", (dialog, id) -> dialog.dismiss());
        alert.show();
    }

    private void register() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("first_name", firstName.getText().toString());
        map.put("last_name", lastName.getText().toString());
        map.put("email", email.getText().toString());
        map.put("cpf", cpf.getText().toString());
        map.put("password", password.getText().toString());
        map.put("password_confirmation", passwordConfirmation.getText().toString());
        map.put("device_token", SharedHelper.getKey(this, "device_token"));
        map.put("device_id", SharedHelper.getKey(this, "device_id"));
        map.put("mobile",  phoneNumber.getText().toString());// SharedHelper.getKey(this, "mobile"));
        map.put("country_code",countryNumber.getText().toString());
        map.put("referral_unique_id", txtReferralCode.getText().toString());
      //  map.put("countryCode", SharedHelper.getKey(this, "countryCode"));
        map.put("device_type", BuildConfig.DEVICE_TYPE);
        map.put("login_by", "manual");
        showLoading();
        registerPresenter.register(map);
    }

    private boolean validate() {
        validaCPF = new ValidaCPF();
        boolean valida = validaCPF.isCPF(cpf.getText().toString().replaceAll("\\.", "").replace("-", ""));
        if (!valida){
            Toast.makeText(this, "CPF inválido!", Toast.LENGTH_SHORT).show();
            return false;
        } else
        password.requestFocus();
        if (email.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
            return false;
        } else if (firstName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_first_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (lastName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_last_name), Toast.LENGTH_SHORT).show();
            return false;
        } else if (cpf.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, informe seu CPF.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (phoneNumber.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_phone_number), Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_password), Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.getText().toString().length() < 6) {
            Toast.makeText(this, getString(R.string.invalid_password_length), Toast.LENGTH_SHORT).show();
            return false;
        } else if (passwordConfirmation.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_confirm_password), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.getText().toString().equals(passwordConfirmation.getText().toString())) {
            Toast.makeText(this, getString(R.string.password_should_be_same), Toast.LENGTH_SHORT).show();
            return false;
        } else if (SharedHelper.getKey(this, "device_token").isEmpty()) {
            FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.w("RegisterActivity", "getInstanceId failed", task.getException());
                    return;
                }
                Log.d("FCM_TOKEN", task.getResult().getToken());
                SharedHelper.putKey(RegisterActivity.this, "device_token", task.getResult().getToken());
            });
            return false;
        } else if (SharedHelper.getKey(this, "device_id").isEmpty()) {
            String deviceId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
            SharedHelper.putKey(this, "device_id", deviceId);
            Toast.makeText(this, getString(R.string.invalid_device_id), Toast.LENGTH_SHORT).show();
            return false;
        } else if (!chkTerms.isChecked()) {
            Toast.makeText(this, getString(R.string.please_accept_terms_condition), Toast.LENGTH_SHORT).show();
            return false;
        } else if (isEmailAvailable) {
            showErrorMessage(email, getString(R.string.email_already_exist));
            if (BuildConfig.DEBUG) {
                password.setText(null);
                passwordConfirmation.setText(null);
            }
            return false;
        } else return true;
    }

    @Override
    public void onSuccess(RegisterResponse response) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Toast.makeText(this, getString(R.string.you_have_been_successfully_registered), Toast.LENGTH_SHORT).show();
        SharedHelper.putKey(this, "access_token", "Bearer " + response.getAccessToken());
        SharedHelper.putKey(this, "logged_in", true);
        finishAffinity();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onSuccess(Object object) {
        isEmailAvailable = false;
    }

    @Override
    public void onSuccessPhoneNumber(Object object) {
    }

    @Override
    public void onVerifyPhoneNumberError(Throwable e) {
        showErrorMessage(phoneNumber, getString(R.string.phone_number_already_exists));
    }

    @Override
    public void onError(Throwable e) {
        showErrorMessage(phoneNumber, getString(R.string.phone_number_already_exists));
        handleError(e);
    }

    @Override
    public void onVerifyEmailError(Throwable e) {
        isEmailAvailable = true;
        showErrorMessage(email, getString(R.string.email_already_exist));
    }

    private void showErrorMessage(EditText view, String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
        view.requestFocus();
        view.setText(null);
    }

    /*@Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_ACCOUNT_KIT && data != null && resultCode == RESULT_OK) {


        }
    }*/

    @Override
    protected void onDestroy() {
        registerPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onSuccess(SettingsResponse response) {
       // lnrReferralCode.setVisibility(response.getReferral().getReferral().equalsIgnoreCase("1") ? View.GONE : View.GONE);
    }
}
