package com.minasmob.passageiro.ui.activity.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import androidx.annotation.NonNull;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.minasmob.passageiro.BuildConfig;
import com.minasmob.passageiro.MvpApplication;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.User;
import com.minasmob.passageiro.ui.activity.change_password.ChangePasswordActivity;
import com.minasmob.passageiro.ui.countrypicker.Country;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

import static com.minasmob.passageiro.data.SharedHelper.key.PROFILE_IMG;

public class ProfileActivity extends BaseActivity implements ProfileIView {

    @BindView(R.id.picture)
    CircleImageView picture;
    @BindView(R.id.completeData)
    LinearLayout completeData;
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.cpf)
    EditText cpf;
    @BindView(R.id.sos1)
    EditText sos1;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.email)
    EditText email;
    File imgFile = null;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.change_password)
    TextView changePassword;
    @BindView(R.id.qr_scan)
    ImageView ivQrScan;
    int sos;


    public static final int REQUEST_PERMISSION = 100;
    private ProfilePresenter<ProfileActivity> profilePresenter = new ProfilePresenter<>();
    private String qrCodeUrl;
    private AlertDialog mDialog;
    private String userAvatar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_profile;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        profilePresenter.attachView(this);
        setTitle(getString(R.string.profile));

        showLoading();
        profilePresenter.profile();
        Glide.with(baseActivity())
                .load(SharedHelper.getKey(baseActivity(), PROFILE_IMG))
                .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_user_placeholder)
                        .dontAnimate()
                        .error(R.drawable.ic_user_placeholder))
                .into(picture);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, ProfileActivity.this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                imgFile = imageFiles.get(0);
                Glide.with(baseActivity())
                        .load(Uri.fromFile(imgFile))
                        .apply(RequestOptions
                                .placeholderOf(R.drawable.ic_user_placeholder)
                                .dontAnimate()
                                .error(R.drawable.ic_user_placeholder))
                        .into(picture);
            }
        });


    }

    @Override
    public void onSuccessPhoneNumber(Object object) {
        updateDetails();
    }

    @Override
    public void onVerifyPhoneNumberError(Throwable e) {
        Toasty.error(this, getString(R.string.phone_number_already_exists), Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.picture, R.id.save, R.id.change_password, R.id.qr_scan, R.id.mobile})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.picture:
                if (hasPermission(Manifest.permission.CAMERA) && hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    pickImage();
                else
                    requestPermissionsSafely(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                break;
            case R.id.save:
                updateProfile();
                break;
            case R.id.change_password:
                startActivity(new Intent(this, ChangePasswordActivity.class));
                break;
            case R.id.qr_scan:
              //  if (!TextUtils.isEmpty(qrCodeUrl)) showQRCodePopup();
                break;
            case R.id.mobile:
                Country mCountry = getDeviceCountry(this);
                break;
        }
    }

    private void showQRCodePopup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_banner, null);

        ImageView qrImage = view.findViewById(R.id.imagem);
        ImageView close = view.findViewById(R.id.ivClose);

        Glide.with(ProfileActivity.this)
                .load(qrCodeUrl)
                .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_qr_code)
                        .dontAnimate().error(R.drawable.ic_qr_code))
                .into(qrImage);

        builder.setView(view);
        mDialog = builder.create();
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        close.setOnClickListener(view1 -> mDialog.dismiss());

        mDialog.show();
    }

    private void updateProfile() {
        if (userAvatar == null && imgFile == null) {
            Toast.makeText(this, "Por favor, envie sua Foto de Perfil", Toast.LENGTH_SHORT).show();
        } else if (email.getText().toString().isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            Toast.makeText(this, getString(R.string.invalid_email), Toast.LENGTH_SHORT).show();
        } else if (firstName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_first_name), Toast.LENGTH_SHORT).show();
        } else if (lastName.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_last_name), Toast.LENGTH_SHORT).show();
        } else if (cpf.getText().toString().isEmpty()) {
            Toast.makeText(this, "Por favor, informe seu CPF", Toast.LENGTH_SHORT).show();
        } else if (mobile.getText().toString().isEmpty()) {
            Toast.makeText(this, getString(R.string.invalid_mobile), Toast.LENGTH_SHORT).show();
        } else updateDetails();
    }

    private void updateDetails() {
        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("first_name", RequestBody.create(MediaType.parse("text/plain"), firstName.getText().toString()));
        map.put("last_name", RequestBody.create(MediaType.parse("text/plain"), lastName.getText().toString()));
        map.put("cpf", RequestBody.create(MediaType.parse("text/plain"), cpf.getText().toString()));
        map.put("email", RequestBody.create(MediaType.parse("text/plain"), email.getText().toString()));
        map.put("mobile", RequestBody.create(MediaType.parse("text/plain"), mobile.getText().toString()));
        map.put("idsosp", RequestBody.create(MediaType.parse("text/plain"), String.valueOf(sos)));
        map.put("sos", RequestBody.create(MediaType.parse("text/plain"), sos1.getText().toString()));
        map.put("country_code", RequestBody.create(MediaType.parse("text/plain"), SharedHelper.getKey(ProfileActivity.this, "country_code")));

        MultipartBody.Part filePart = null;
        if (imgFile != null)
            try {
                File compressedImageFile = new Compressor(this).compressToFile(imgFile);
                filePart = MultipartBody.Part.createFormData("picture", compressedImageFile.getName(),
                        RequestBody.create(MediaType.parse("image*//*"), compressedImageFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        showLoading();
        profilePresenter.update(map, filePart);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION:
                if (grantResults.length > 0) {
                    boolean permission1 = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean permission2 = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (permission1 && permission2) pickImage();
                    else
                        Toast.makeText(getApplicationContext(), R.string.please_give_permissions, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onSuccess(@NonNull User user) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        //Verifica se o usuário não informou o CPF ou se não enviou a foto de perfil
        if(user.getCpf() == null || user.getPicture() == null){
            completeData.setVisibility(View.VISIBLE);
        }

        //Seta avatar
        userAvatar = user.getPicture();

        String loginBy = user.getLoginBy();
        SharedHelper.putKey(this, "lang", user.getLanguage());
      //  changePassword.setVisibility(loginBy.equalsIgnoreCase("facebook")
             //   || loginBy.equalsIgnoreCase("google") ? View.INVISIBLE : View.GONE);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        cpf.setText(user.getCpf());
        mobile.setText(user.getMobile());
        email.setText(user.getEmail());
        sos1.setText(user.getSos1());
        sos = user.getId();
       // SharedHelper.putKey(this, "stripe_publishable_key", user.getStripePublishableKey());
        SharedHelper.putKey(this, "measurementType", user.getMeasurement());
        Glide.with(baseActivity())
                .load(BuildConfig.BASE_IMAGE_URL + user.getPicture())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_user_placeholder)
                        .dontAnimate().error(R.drawable.ic_user_placeholder))
                .into(picture);
        MvpApplication.showOTP = user.getRide_otp().equals("1");

      //  qrCodeUrl = !TextUtils.isEmpty(user.getQrcode_url()) ? BuildConfig.BASE_URL + user.getQrcode_url() : null;
       // ivQrScan.setVisibility(TextUtils.isEmpty(qrCodeUrl) ? View.INVISIBLE : View.VISIBLE);
    }

    @Override
    public void onUpdateSuccess(User user) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        SharedHelper.putKey(this, "lang", user.getLanguage());

        SharedHelper.putKey(this, "stripe_publishable_key", user.getStripePublishableKey());
        SharedHelper.putKey(this, "measurementType", user.getMeasurement());
        try {
            MvpApplication.showOTP = user.getRide_otp().equals("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
        Toasty.success(this, getText(R.string.profile_updated), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @Override
    protected void onDestroy() {
        profilePresenter.onDetach();
        super.onDestroy();
    }
}
