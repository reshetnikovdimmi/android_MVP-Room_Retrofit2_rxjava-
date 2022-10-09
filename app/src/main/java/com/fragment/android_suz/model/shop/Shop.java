package com.fragment.android_suz.model.shop;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Shop implements Serializable {

    @PrimaryKey
    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;
    @SerializedName("login")
    @ColumnInfo(name = "login")
    private String login;
    @SerializedName("pasword")
    @ColumnInfo(name = "pasword")
    private String pasword;
    @SerializedName("name")
    @ColumnInfo(name = "name")
    private String name;
    @SerializedName("clusterT2")
    @ColumnInfo(name = "clusterT2")
    private String clusterT2;
    @SerializedName("clusterRtk")
    @ColumnInfo(name = "clusterRtk")
    private String clusterRtk;
    @SerializedName("simT2")
    @ColumnInfo(name = "simT2")
    private String simT2;
    @SerializedName("simMts")
    @ColumnInfo(name = "simMts")
    private String simMts;
    @SerializedName("simBee")
    @ColumnInfo(name = "simBee")
    private String simBee;
    @SerializedName("simMf")
    @ColumnInfo(name = "simMf")
    private String simMf;
    @SerializedName("shopIskra")
    @ColumnInfo(name = "shopIskra")
    private String shopIskra;
    @SerializedName("shopRarus")
    @ColumnInfo(name = "shopRarus")
    private String shopRarus;

    

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", pasword='" + pasword + '\'' +
                ", name='" + name + '\'' +
                ", clusterT2='" + clusterT2 + '\'' +
                ", clusterRtk='" + clusterRtk + '\'' +
                ", simT2='" + simT2 + '\'' +
                ", simMts='" + simMts + '\'' +
                ", simBee='" + simBee + '\'' +
                ", simMf='" + simMf + '\'' +
                ", shopIskra='" + shopIskra + '\'' +
                ", shopRarus='" + shopRarus + '\'' +
                '}';
    }

    public Shop(int id, String login, String pasword, String name, String clusterT2, String clusterRtk, String simT2, String simMts, String simBee, String simMf, String shopIskra, String shopRarus) {
        this.id = id;
        this.login = login;
        this.pasword = pasword;
        this.name = name;
        this.clusterT2 = clusterT2;
        this.clusterRtk = clusterRtk;
        this.simT2 = simT2;
        this.simMts = simMts;
        this.simBee = simBee;
        this.simMf = simMf;
        this.shopIskra = shopIskra;
        this.shopRarus = shopRarus;
    }

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasword() {
        return pasword;
    }

    public void setPasword(String pasword) {
        this.pasword = pasword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClusterT2() {
        return clusterT2;
    }

    public void setClusterT2(String clusterT2) {
        this.clusterT2 = clusterT2;
    }

    public String getClusterRtk() {
        return clusterRtk;
    }

    public void setClusterRtk(String clusterRtk) {
        this.clusterRtk = clusterRtk;
    }

    public String getSimT2() {
        return simT2;
    }

    public void setSimT2(String simT2) {
        this.simT2 = simT2;
    }

    public String getSimMts() {
        return simMts;
    }

    public void setSimMts(String simMts) {
        this.simMts = simMts;
    }

    public String getSimBee() {
        return simBee;
    }

    public void setSimBee(String simBee) {
        this.simBee = simBee;
    }

    public String getSimMf() {
        return simMf;
    }

    public void setSimMf(String simMf) {
        this.simMf = simMf;
    }

    public String getShopIskra() {
        return shopIskra;
    }

    public void setShopIskra(String shopIskra) {
        this.shopIskra = shopIskra;
    }

    public String getShopRarus() {
        return shopRarus;
    }

    public void setShopRarus(String shopRarus) {
        this.shopRarus = shopRarus;
    }
}

