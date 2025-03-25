package com.minasmob.passageiro.ui.adiciona_favorito;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.network.model.AddFav;
import com.minasmob.passageiro.ui.favorito.FavActivity;
import com.minasmob.passageiro.ui.favorito.FavPresenter;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class AddFavActivity extends BaseActivity implements AddFavIView {



   @BindView(R.id.editar)
   Button editar;

   @BindView(R.id.txtFav)
    TextView favtxt;

    @BindView(R.id.txtFav2)
    TextView favtxt2;

   @BindView(R.id.criar)
    Button criar;

   @BindView(R.id.lbl_banner_fav)
    FrameLayout lblbannerfav;

   private String s;

    public static final int REQUEST_PERMISSION = 100;
    private AddFavPresenter<AddFavActivity> favoritoPresenter = new AddFavPresenter<>();
    private FavPresenter<FavActivity> favoritoPresenterf = new FavPresenter<>();



    @Override
    public int getLayoutId() {
        return R.layout.add_activity_favorito;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        favoritoPresenter.attachView(this);
        setTitle(getString(R.string.addfavorito));

        //showLoading();
       favoritoPresenter.addfavorito();

      //  lblFavvorito.setVisibility(View.VISIBLE);

    }







    @OnClick({ R.id.criar,R.id.editar})
    public void onViewClicked(View view) {

        switch (view.getId()) {

            case R.id.criar:
                updateProfile();
                break;

            case R.id.editar:
                startActivity(new Intent(this, FavActivity.class));
                break;



        }
    }




    private void updateProfile() {

         updateDetails();
    }

    private void updateDetails() {
        HashMap<String, RequestBody> map = new HashMap<>();

        map.put("fav", RequestBody.create(MediaType.parse("text/plain"), "0"));
        map.put("name", RequestBody.create(MediaType.parse("text/plain"), ""));


        MultipartBody.Part filePart = null;

        showLoading();
        favoritoPresenter.updatefav(map, filePart);
      //  finish();
        startActivity(new Intent(this, FavActivity.class));
        Toasty.success(this, getText(R.string.lista_avorito_updated), Toast.LENGTH_SHORT).show();
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
    //TODO Marcio hamasaki 11.04.2021
    @Override
    public void onSuccess(@NonNull AddFav user) {
        s= String.valueOf(user.getUserId());


        favtxt.setText(String.format(String.valueOf(user.getFav()+"  " +
                user.getName() +"\n\n"+user.getFav2()+"  " + user.getName2()+
                "\n\n"+user.getFav3()+"  " + user.getName3() +"\n\n"+user.getFav4()+"  " + user.getName4() +"\n\n"+user.getFav5()+"  " + user.getName5())));

        favtxt2.setText(String.format(String.valueOf(user.getFav6()+"  " +
                user.getName6() +"\n\n"+user.getFav7()+"  " + user.getName7()+
                "\n\n"+user.getFav8()+"  " + user.getName8() +"\n\n"+user.getFav9()+"  " + user.getName9() +"\n\n"+user.getFav10()+"  " + user.getName10())));

        if(s != null){
            editar.setVisibility(View.VISIBLE);
            criar.setVisibility(View.GONE);
            lblbannerfav.setVisibility(View.VISIBLE);

        }



    }



  /*  @Override
    public void onSuccess(@NonNull AddFav addFav) {

        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }



       // favor.setText( String.format( String.valueOf( addFav.getFv() ) ) );




    }*/





    @Override
    public void onUpdateSuccess(AddFav addFav) {
        try {
            hideLoading();
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        finish();
        Toasty.success(this, getText(R.string.lista_avorito_updated), Toast.LENGTH_SHORT).show();
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
