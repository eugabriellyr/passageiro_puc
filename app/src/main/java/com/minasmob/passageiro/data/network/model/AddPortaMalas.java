package com.minasmob.passageiro.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddPortaMalas {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("add_porta_mala")
    @Expose
    private double add_porta_mala;

    @SerializedName("add_pet")
    @Expose
    private double add_pet;


    public int getId() {
        return id  ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAddM() { return add_porta_mala  ; }

    public void setAddM(double add_porta_mala) {
        this.add_porta_mala = add_porta_mala;
    }

    public double getAddP() { return add_pet  ; }

    public void setAddP(double add_pet) {
        this.add_pet = add_pet;
    }


}
