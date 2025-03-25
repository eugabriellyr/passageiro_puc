package com.minasmob.passageiro.ui.activity.premio_bonus;

import android.os.Build;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.minasmob.passageiro.R;
import com.minasmob.passageiro.base.BaseActivity;
import com.minasmob.passageiro.data.network.model.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PremioBonusActivity extends BaseActivity implements PremioBonusIView {
 //TODO função Marcio hamasaki
    private static final String TAG = "Premio";


    @BindView(R.id.validas)
    TextView validas;

    @BindView(R.id.canc_p)
    TextView canc_p;
    @BindView(R.id.canc_m)
    TextView canc_m;

    @BindView(R.id.corridas)
    TextView corridas;

    @BindView(R.id.txtaviso)
    TextView txtaviso;

    @BindView(R.id.txtpassageiro)
    TextView txtpassageiro;

    @BindView(R.id.txtmotorista)
    TextView txtmotorista;

    @BindView(R.id.txtpassageiroc)
    TextView txtpassageiroc;

    @BindView(R.id.txtmotoristac)
    TextView txtmotoristac;

    @BindView(R.id.primeirameta)
    TextView primeirameta;

    @BindView(R.id.segundameta)
    TextView segundameta;

    @BindView(R.id.terceirameta)
    TextView terceirameta;

    @BindView(R.id.primeirametacodigo)
    TextView primeirametacodigo;

    @BindView(R.id.lblprimeirameta)
    LinearLayout lblprimeirameta;

    @BindView(R.id.lblprimeirametacodigo)
    LinearLayout lblprimeirametacodigo;


    @BindView(R.id.segundametacodigo)
    TextView segundametacodigo;

    @BindView(R.id.lblsegundameta)
    LinearLayout lblsegundameta;

    @BindView(R.id.lblsegundametacodigo)
    LinearLayout lblsegundametacodigo;

    @BindView(R.id.terceirametacodigo)
    TextView terceirametacodigo;

    @BindView(R.id.lblterceirameta)
    LinearLayout lblterceirameta;

    @BindView(R.id.lblterceirametacodigo)
    LinearLayout lblterceirametacodigo;




    private PremioBonusPresenter<PremioBonusActivity> premioBonusPresenter = new PremioBonusPresenter<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_premio_bonus;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        premioBonusPresenter.attachView(this);
        premioBonusPresenter.profile();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onSuccess(User user) {

        hideLoading();



        //TODO MARCIO HAMASAKI /17/05/2021  00:40:45 AM
        int resultado = 0;
        int mcc = user.getCancm();
        int mcp = user.getMcp();
        int mtc = mcc * mcp;
        int cp = user.getCancp();
        int cpd = user.getCpd();
        int rgpc = cp * cpd;
        int cm = user.getTotal_corridas_premio() ;
        resultado = cm - rgpc + mtc ;



        corridas.setText((String.valueOf(user.getTotal_corridas_premio())));
        canc_m.setText(String.valueOf(user.getCancm()));
        canc_p.setText(String.valueOf(user.getCancp()));
        validas.setText( String.format( String.valueOf( resultado ) ) );
        txtaviso.setText(String.format(user.getTxtaviso()));
        txtpassageiro.setText(String.valueOf("Cancelamento Passageiro" + " - " + user.getCpd()));
        txtmotorista.setText(String.valueOf("Cancelamento Motorista"  + " + " + user.getMcp()));
        txtpassageiroc.setText(String.valueOf("  Desconto Passageiro" + " = " + rgpc));
        txtmotoristac.setText(String.valueOf("  Bonus Motorista"  + " = " + mtc));
        primeirameta.setText(String.valueOf("1º META DE "+ user.getPmeta() + " CORRIDAS"));
        segundameta.setText(String.valueOf("2º META DE "+ user.getSmeta() + " CORRIDAS"));
        terceirameta.setText(String.valueOf("3º META DE "+ user.getTmeta() + " CORRIDAS"));
        primeirametacodigo.setText(String.valueOf(user.getPmetac()));
        segundametacodigo.setText(String.valueOf(user.getSmetac()));
        terceirametacodigo.setText(String.valueOf(user.getTmetac()));

        int r = Integer.parseInt(user.getPmeta());

        if (resultado >= r){
            lblprimeirametacodigo.setVisibility(View.VISIBLE);
            lblprimeirameta.setVisibility(View.GONE);
        }else{
            lblprimeirametacodigo.setVisibility(View.GONE);
            lblprimeirameta.setVisibility(View.VISIBLE);
        }

        int rs = Integer.parseInt(user.getSmeta());

        if (resultado >= rs){
            lblsegundametacodigo.setVisibility(View.VISIBLE);
            lblsegundameta.setVisibility(View.GONE);
        }else{
            lblsegundametacodigo.setVisibility(View.GONE);
            lblsegundameta.setVisibility(View.VISIBLE);
        }

        int rt = Integer.parseInt(user.getTmeta());

        if (resultado >= rt){
            lblterceirametacodigo.setVisibility(View.VISIBLE);
            lblterceirameta.setVisibility(View.GONE);
        }else{
            lblterceirametacodigo.setVisibility(View.GONE);
            lblterceirameta.setVisibility(View.VISIBLE);
        }


    }




    @Override
    public void onError(Throwable throwable) {
        handleError(throwable);
    }


}
