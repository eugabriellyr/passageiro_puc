package com.minasmob.passageiro.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromocaoManager {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("ld")
    @Expose
    private int ld;
    @SerializedName("notify_type")
    @Expose
    private String notifyType;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    public String description;

    @SerializedName("whts")
    @Expose
    public String whts;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public PromocaoManager() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getld() {
        return ld;
    }

    public void setld(int ld) {
        this.ld = ld;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description  ;
    }

    public void setWhts(String whts) {
        this.whts = whts;
    }

    public String getWhts() {
        return whts  ;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
