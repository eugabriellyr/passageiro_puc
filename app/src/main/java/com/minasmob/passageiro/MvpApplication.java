package com.minasmob.passageiro;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;

import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.core.CrashlyticsCore;
//import com.facebook.stetho.Stetho;
//import com.facebook.stetho.Stetho;
import com.google.firebase.FirebaseApp;
import com.minasmob.passageiro.common.ConnectivityReceiver;
import com.minasmob.passageiro.common.LocaleHelper;
import com.minasmob.passageiro.data.network.model.Datum;

import java.util.HashMap;

import io.fabric.sdk.android.Fabric;

//import io.fabric.sdk.android.Fabric;

//import com.facebook.stetho.Stetho;

public class MvpApplication extends Application {

    private static MvpApplication mInstance;

    public static boolean canGoToChatScreen;
    public static boolean isChatScreenOpen;

    public static boolean isCash = true;
    public static boolean isCard;
    public static boolean isPayumoney;
    public static boolean isPaypal;
    public static boolean isPaytm;
    public static boolean isPaypalAdaptive;
    public static boolean isBraintree;
    public static boolean openChatFromNotification = true;

    //TODO MARCIO HAMASAKI PICPAY MERCADO PAGO
    public static boolean isDebitMachine;
    public static boolean isVoucher;
    public static boolean isPicPay;
    public static boolean isPix;
    public static boolean isMp;
    public static HashMap<String, Object> RIDE_REQUEST = new HashMap<>();
    public static Datum DATUM = null;
    public static boolean showOTP = true;

    public static synchronized MvpApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        FirebaseApp.initializeApp( this );
        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
        Crashlytics crashlytics = new Crashlytics.Builder().core(core).build();
        Fabric.with(this, crashlytics, new Answers());

        if (BuildConfig.DEBUG)
            //Stetho.initializeWithDefaults(this);

        MultiDex.install(this);
    }
    public String getNewNumberFormat(double d) {
        //      String text = Double.toString(Math.abs(d));
        String text = Double.toString(d);
        int integerPlaces = text.indexOf('.');
        int decimalPlaces = text.length() - integerPlaces - 1;
        if (decimalPlaces == 2) return text;
        else if (decimalPlaces == 1) return text + "0";
        else if (decimalPlaces == 0) return text + ".00";
        else if (decimalPlaces > 2) {
            String converted = String.valueOf((double) Math.round(d * 100) / 100);
            int convertedInegers = converted.indexOf('.');
            int convertedDecimals = converted.length() - convertedInegers - 1;
            if (convertedDecimals == 2) return converted;
            else if (convertedDecimals == 1) return converted + "0";
            else if (convertedDecimals == 0) return converted + ".00";
            else return converted;
        } else return text;
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase, "en"));
        MultiDex.install(newBase);
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
