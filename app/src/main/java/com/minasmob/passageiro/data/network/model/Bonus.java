package com.minasmob.passageiro.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by suthakar@appoets.com on 29-06-2018.
 */
public class Bonus {

    @SerializedName("rides")
    @Expose
    private int rides;
    @SerializedName("completed_rides")
    @Expose
    private int completedRides;
    @SerializedName("revenue")
    @Expose
    private int  revenue;

    @SerializedName("metas")
    @Expose
    public int metas;

    @SerializedName("ttal")
    @Expose
    private int ttal;

    @SerializedName("montly_pass")
    @Expose
    private Float montlyPass;
    @SerializedName("montly_gains")
    @Expose
    private Float montlyGains;
    @SerializedName("cancel_rides")
    @Expose
    private int cancelRides;
    //get quantidade de indicados
    public int getRides() {
        return rides ;
    }

    public void setRides(int rides) {
        this.rides = rides;
    }

    public int getCompletedRides() {
        return completedRides;
    }

    public void setCompletedRides(int completedRides) {
        this.rides = completedRides;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int  revenue) {
        this.revenue = revenue;
    }

    public int getMetas() {
        return metas;
    }

    public void setMetas(int metas) {
        this.metas = metas;
    }

    public int getTotal() {
        return ttal ;
    }

    public void setTotal(int ttal) {
        this.ttal = ttal;
    }

    public Float getMontlyPass() {
        return montlyPass ;
    }

    public void setMontlyPass(Float montlyPass) {
        this.montlyPass = montlyPass;
    }

    public Float getMontlyGains() {
        return montlyGains;
    }

    public void setMontlyGains(Float montlyGains) {
        this.montlyGains = montlyGains;
    }

    public int getCancelRides() {
        return cancelRides;
    }

    public void setCancelRides(int cancelRides) {
        this.cancelRides = cancelRides;
    }


}
