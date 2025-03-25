package com.minasmob.passageiro.ui.favorito;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.network.model.Fav;
import com.minasmob.passageiro.ui.activity.main.MainActivity;

import java.util.HashMap;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;




public class FavActivity extends BaseActivity implements FavIView {


    @BindView(R.id.fav)
    EditText favor;
    @BindView( R.id.favnome )
    EditText favname;
    @BindView(R.id.fav2)
    EditText favor2;
    @BindView( R.id.favnome2 )
    EditText favname2;
    @BindView(R.id.fav3)
    EditText favor3;
    @BindView( R.id.favnome3)
    EditText favname3;
    @BindView(R.id.fav4)
    EditText favor4;
    @BindView( R.id.favnome4)
    EditText favname4;

    @BindView(R.id.fav5)
    EditText favor5;
    @BindView( R.id.favnome5)
    EditText favname5;

    @BindView(R.id.fav6)
    EditText favor6;
    @BindView( R.id.favnome6)
    EditText favname6;

    @BindView(R.id.fav7)
    EditText favor7;
    @BindView( R.id.favnome7)
    EditText favname7;

    @BindView(R.id.fav8)
    EditText favor8;
    @BindView( R.id.favnome8)
    EditText favname8;

    @BindView(R.id.fav9)
    EditText favor9;
    @BindView( R.id.favnome9)
    EditText favname9;

    @BindView(R.id.fav10)
    EditText favor10;
    @BindView( R.id.favnome10)
    EditText favname10;






    public static final int REQUEST_PERMISSION = 100;
    private FavPresenter<FavActivity> favoritoPresenter = new FavPresenter<>();



    @Override
    public int getLayoutId() {
        return R.layout.activity_favorito;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        favoritoPresenter.attachView(this);
        setTitle(getString(R.string.favorito));

        showLoading();
        favoritoPresenter.favorito();

    }







    @OnClick({ R.id.save})
    public void onViewClicked(View view) {



        switch (view.getId()) {

            case R.id.save:
                updateProfile();
            break;

        }
    }




    private void updateProfile() {
         updateDetails();
    }

    private void updateDetails() {
        HashMap<String, RequestBody> map = new HashMap<>();

        map.put("fav", RequestBody.create(MediaType.parse("text/plain"), favor.getText().toString()));
        map.put("name", RequestBody.create(MediaType.parse("text/plain"), favname.getText().toString()));

        map.put("fav2", RequestBody.create(MediaType.parse("text/plain"), favor2.getText().toString()));
        map.put("name2", RequestBody.create(MediaType.parse("text/plain"), favname2.getText().toString()));

        map.put("fav3", RequestBody.create(MediaType.parse("text/plain"), favor3.getText().toString()));
        map.put("name3", RequestBody.create(MediaType.parse("text/plain"), favname3.getText().toString()));

        map.put("fav4", RequestBody.create(MediaType.parse("text/plain"), favor4.getText().toString()));
        map.put("name4", RequestBody.create(MediaType.parse("text/plain"), favname4.getText().toString()));

        map.put("fav5", RequestBody.create(MediaType.parse("text/plain"), favor5.getText().toString()));
        map.put("name5", RequestBody.create(MediaType.parse("text/plain"), favname5.getText().toString()));

        map.put("fav6", RequestBody.create(MediaType.parse("text/plain"), favor6.getText().toString()));
        map.put("name6", RequestBody.create(MediaType.parse("text/plain"), favname6.getText().toString()));

        map.put("fav7", RequestBody.create(MediaType.parse("text/plain"), favor7.getText().toString()));
        map.put("name7", RequestBody.create(MediaType.parse("text/plain"), favname7.getText().toString()));

        map.put("fav8", RequestBody.create(MediaType.parse("text/plain"), favor8.getText().toString()));
        map.put("name8", RequestBody.create(MediaType.parse("text/plain"), favname8.getText().toString()));

        map.put("fav9", RequestBody.create(MediaType.parse("text/plain"), favor9.getText().toString()));
        map.put("name9", RequestBody.create(MediaType.parse("text/plain"), favname9.getText().toString()));

        map.put("fav10", RequestBody.create(MediaType.parse("text/plain"), favor10.getText().toString()));
        map.put("name10", RequestBody.create(MediaType.parse("text/plain"), favname10.getText().toString()));
        MultipartBody.Part filePart = null;

        showLoading();
        startActivity(new Intent(this, MainActivity.class));
        favoritoPresenter.updatefav(map, filePart);


    }

   /* @Override
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
    }*/

    @Override
    public void onSuccess(@NonNull Fav fav) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        favname.setText(fav.getFav());
        favor.setText( String.format( String.valueOf( fav.getFv() ) ) );
        favname2.setText(fav.getFav2());
        favor2.setText( String.format( String.valueOf( fav.getFv2() ) ) );
        favname3.setText(fav.getFav3());
        favor3.setText( String.format( String.valueOf( fav.getFv3()) ) );
        favname4.setText(fav.getFav4());
        favor4.setText( String.format( String.valueOf( fav.getFv4()) ) );
        favname5.setText(fav.getFav5());
        favor5.setText( String.format( String.valueOf( fav.getFv5() ) ) );
        favname6.setText(fav.getFav6());
        favor6.setText( String.format( String.valueOf( fav.getFv6() ) ) );
        favname7.setText(fav.getFav7());
        favor7.setText( String.format( String.valueOf( fav.getFv7() ) ) );
        favname8.setText(fav.getFav8());
        favor8.setText( String.format( String.valueOf( fav.getFv8() ) ) );
        favname9.setText(fav.getFav9());
        favor9.setText( String.format( String.valueOf( fav.getFv9() ) ) );
        favname10.setText(fav.getFav10());
        favor10.setText( String.format( String.valueOf( fav.getFv10() ) ) );




    }

    @Override
    public void onUpdateSuccess(Fav fav) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        finish();
        Toasty.success(this, getText(R.string.avorito_updated), Toast.LENGTH_SHORT).show();
    }




    @Override
    public void onError(Throwable e) {
        handleError(e);
    }

    @Override
    protected void onDestroy() {
        favoritoPresenter.onDetach();
        super.onDestroy();
    }
}
