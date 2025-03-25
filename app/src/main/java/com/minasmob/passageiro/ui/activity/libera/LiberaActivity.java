package com.minasmob.passageiro.ui.activity.libera;

import static com.minasmob.passageiro.data.SharedHelper.key.PROFILE_IMG;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.minasmob.passageiro.BuildConfig;
import com.minasmob.passageiro.MvpApplication;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.User;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class LiberaActivity extends BaseActivity implements LiberaIView {

    @BindView(R.id.frente)
    ImageView frente;
    @BindView(R.id.verso)
    ImageView verso;
    @BindView(R.id.carrega)
    ImageView carrega;
    @BindView(R.id.completeData)
    LinearLayout completeData;
    File imgFile = null;
    File imgFiles = null;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.status_ed)
    TextView status_ed;
    @BindView(R.id.txt_educ)
    TextView txt_educacao;




    public static final int REQUEST_PERMISSION = 100;
    private LiberaPresenter<LiberaActivity> profilePresenter = new LiberaPresenter<>();
    private AlertDialog mDialog;
    private String userAvatar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_libera;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        profilePresenter.attachView(this);
        setTitle(getString(R.string.documento));

        showLoading();
        profilePresenter.profile();
        Glide.with(baseActivity())
                .load(SharedHelper.getKey(baseActivity(), PROFILE_IMG))
                .apply(RequestOptions
                        .placeholderOf(R.drawable.educ)
                        .dontAnimate()
                        .error(R.drawable.educ))
                .into(frente);

        Glide.with(baseActivity())
                .load(SharedHelper.getKey(baseActivity(), PROFILE_IMG))
                .apply(RequestOptions
                        .placeholderOf(R.drawable.educ)
                        .dontAnimate()
                        .error(R.drawable.educ))
                .into(verso);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        EasyImage.handleActivityResult(requestCode, resultCode, data, LiberaActivity.this, new DefaultCallback() {
            @Override
            public void onImagesPicked(@NonNull List<File> imageFiles, EasyImage.ImageSource source, int type) {
                imgFile = imageFiles.get(0);
                imgFiles = imageFiles.get(0);


               Glide.with(baseActivity())
                        .load(Uri.fromFile(imgFile))
                        .apply(RequestOptions
                                .placeholderOf(R.drawable.educ)
                                .dontAnimate()
                                .error(R.drawable.educ))
                        .into(carrega);

                Glide.with(baseActivity())
                        .load(Uri.fromFile(imgFiles))
                        .apply(RequestOptions
                                .placeholderOf(R.drawable.educ)
                                .dontAnimate()
                                .error(R.drawable.educ))
                        .into(carrega);
            }
        });


    }



    @OnClick({R.id.frente,R.id.verso, R.id.save, R.id.saves})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.frente:
                if (hasPermission(Manifest.permission.CAMERA) && hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    pickImage();
                else
                    requestPermissionsSafely(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                break;

            case R.id.verso:
                if (hasPermission(Manifest.permission.CAMERA) && hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                    pickImage();
                else
                    requestPermissionsSafely(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                break;
            case R.id.save:
                updateProfile();
                break;

            case R.id.saves:
                updateProfileVerso();
                break;



        }
    }



    private void updateProfile() {
        if (userAvatar == null && imgFile == null) {
            Toast.makeText(this, "Por favor, envie foto do documento", Toast.LENGTH_SHORT).show();
        } else updateDetails();
    }

    private void updateProfileVerso() {
        if (userAvatar == null && imgFiles == null) {
            Toast.makeText(this, "Por favor, envie foto do documento", Toast.LENGTH_SHORT).show();
        } else updateDetailsVerso();
    }

    private void updateDetails() {
        HashMap<String, RequestBody> map = new HashMap<>();

        MultipartBody.Part filePart = null;
        if (imgFile != null)
            try {
                File compressedImageFile = new Compressor(this).compressToFile(imgFile);
                filePart = MultipartBody.Part.createFormData("frente", compressedImageFile.getName(),
                        RequestBody.create(MediaType.parse("image*//*"), compressedImageFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        showLoading();
        profilePresenter.update(map, filePart);
    }

    private void updateDetailsVerso() {
        HashMap<String, RequestBody> map = new HashMap<>();

        MultipartBody.Part filePart = null;
        if (imgFile != null)
            try {
                File compressedImageFile = new Compressor(this).compressToFile(imgFile);
                filePart = MultipartBody.Part.createFormData("verso", compressedImageFile.getName(),
                        RequestBody.create(MediaType.parse("image*//*"), compressedImageFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        showLoading();
        profilePresenter.update_verso(map, filePart);
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


        //Seta avatar
        int a = user.getAprova();

        if(a > 0){
            status_ed.setText("Status: " + "VERIFICADO APROVADO");
        }else{
            status_ed.setText("Status: " + "AGUARDANDO APROVAÇÃO");
        }
        userAvatar = user.getPicture();
        txt_educacao.setText(String.valueOf(user.getTxt_educacao()));

        String loginBy = user.getLoginBy();
        SharedHelper.putKey(this, "lang", user.getLanguage());
      //  changePassword.setVisibility(loginBy.equalsIgnoreCase("facebook")
             //   || loginBy.equalsIgnoreCase("google") ? View.INVISIBLE : View.GONE);

       // SharedHelper.putKey(this, "stripe_publishable_key", user.getStripePublishableKey());
       // SharedHelper.putKey(this, "measurementType", user.getMeasurement());
        Glide.with(baseActivity())
                .load(BuildConfig.BASE_IMAGE_URL + user.getImgFrente())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.upload)
                        .dontAnimate().error(R.drawable.upload))
                .into(frente);

        Glide.with(baseActivity())
                .load(BuildConfig.BASE_IMAGE_URL + user.getImgVerso())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.upload)
                        .dontAnimate().error(R.drawable.upload))
                .into(verso);
        MvpApplication.showOTP = user.getRide_otp().equals("1");


    }

    @Override
    public void onUpdateSuccess(User user) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        try {
            MvpApplication.showOTP = user.getRide_otp().equals("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();
        Toasty.success(this, getText(R.string.enviado), Toast.LENGTH_SHORT).show();
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
