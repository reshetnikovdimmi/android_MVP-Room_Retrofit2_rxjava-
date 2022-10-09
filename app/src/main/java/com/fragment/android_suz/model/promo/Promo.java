package com.fragment.android_suz.model.promo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@Entity
public class Promo implements Serializable {
    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private Integer id;
    @SerializedName("brend")
    @ColumnInfo(name = "brend")
    private String brend;
    @SerializedName("models")
    @ColumnInfo(name = "models")
    private String models;
    @SerializedName("price")
    @ColumnInfo(name = "price")
    private String price;
    @SerializedName("price_promo")
    @ColumnInfo(name = "price_promo")
    private String pricePromo;
    @SerializedName("startPromo")
    @ColumnInfo(name = "startPromo")
    private String startPromo;
    @SerializedName("endPromo")
    @ColumnInfo(name = "endPromo")
    private String endPromo;
    @SerializedName("marwel")
    @ColumnInfo(name = "marwel")
    private String marwel;
    @SerializedName("tfn")
    @ColumnInfo(name = "tfn")
    private String tfn;
    @SerializedName("vvp")
    @ColumnInfo(name = "vvp")
    private String vvp;
    @SerializedName("merlion")
    @ColumnInfo(name = "merlion")
    private String merlion;

    public Promo() {
    }

    public Promo(Integer id, String brend, String models, String price, String pricePromo, String startPromo, String endPromo, String marwel, String tfn, String vvp, String merlion) {
        this.id = id;
        this.brend = brend;
        this.models = models;
        this.price = price;
        this.pricePromo = pricePromo;
        this.startPromo = startPromo;
        this.endPromo = endPromo;
        this.marwel = marwel;
        this.tfn = tfn;
        this.vvp = vvp;
        this.merlion = merlion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrend() {
        return brend;
    }

    public void setBrend(String brend) {
        this.brend = brend;
    }

    public String getModels() {
        return models;
    }

    public void setModels(String models) {
        this.models = models;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPricePromo() {
        return pricePromo;
    }

    public void setPricePromo(String pricePromo) {
        this.pricePromo = pricePromo;
    }

    public String getStartPromo() {
        return startPromo;
    }

    public void setStartPromo(String startPromo) {
        this.startPromo = startPromo;
    }

    public String getEndPromo() {
        return endPromo;
    }

    public void setEndPromo(String endPromo) {
        this.endPromo = endPromo;
    }

    public String getMarwel() {
        return marwel;
    }

    public void setMarwel(String marwel) {
        this.marwel = marwel;
    }

    public String getTfn() {
        return tfn;
    }

    public void setTfn(String tfn) {
        this.tfn = tfn;
    }

    public String getVvp() {
        return vvp;
    }

    public void setVvp(String vvp) {
        this.vvp = vvp;
    }

    public String getMerlion() {
        return merlion;
    }

    public void setMerlion(String merlion) {
        this.merlion = merlion;
    }
}
