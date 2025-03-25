package com.minasmob.passageiro.ui.sos;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.minasmob.passageiro.BuildConfig;
import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.SharedHelper;
import com.minasmob.passageiro.data.network.model.User;
import com.minasmob.passageiro.ui.activity.help.HelpActivity;


import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.minasmob.passageiro.data.SharedHelper.key.PROFILE_IMG;

public class SosActivity extends BaseActivity implements SosIView {

    @BindView(R.id.picture)
    CircleImageView picture;
    @BindView(R.id.completeData)
    LinearLayout completeData;
    @BindView(R.id.first_name)
    EditText firstName;
    @BindView(R.id.last_name)
    EditText lastName;
    @BindView(R.id.mobile)
    TextView mobile;
    @BindView(R.id.iduser)
    EditText iduser;
    File imgFile = null;



    public static final int REQUEST_PERMISSION = 100;
    private SosPresenter<SosActivity> sosPresenter = new SosPresenter<>();
    private String qrCodeUrl;
    private AlertDialog mDialog;
    private String userAvatar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_sos;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        sosPresenter.attachView(this);
        setTitle(getString(R.string.pedidosos));

       showLoading();
       sosPresenter.sos();
        Glide.with(baseActivity())
                .load(SharedHelper.getKey(baseActivity(), PROFILE_IMG))
                .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_user_placeholder)
                        .dontAnimate()
                        .error(R.drawable.ic_user_placeholder))
                .into(picture);
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

    @OnClick({ R.id.socorro})
    public void onViewClicked(View view) {

        switch (view.getId()) {


            case R.id.socorro:
                //sosPresenter.sos();
                startActivity(new Intent(this, HelpActivity.class));
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
        userAvatar = user.getPicture();
        SharedHelper.putKey(this, "lang", user.getLanguage());
      //  changePassword.setVisibility(loginBy.equalsIgnoreCase("facebook")
             //   || loginBy.equalsIgnoreCase("google") ? View.INVISIBLE : View.GONE);
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        mobile.setText(user.getMobile());
        iduser.setText(String.valueOf(user.getId()));
       // SharedHelper.putKey(this, "stripe_publishable_key", user.getStripePublishableKey());
        SharedHelper.putKey(this, "measurementType", user.getMeasurement());
        Glide.with(baseActivity())
                .load(BuildConfig.BASE_IMAGE_URL + user.getPicture())
                .apply(RequestOptions
                        .placeholderOf(R.drawable.ic_user_placeholder)
                        .dontAnimate().error(R.drawable.ic_user_placeholder))
                .into(picture);

    }



    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @Override
    protected void onDestroy() {
        sosPresenter.onDetach();
        super.onDestroy();
    }
}
