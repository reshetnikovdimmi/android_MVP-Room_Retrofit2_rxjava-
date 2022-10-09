package com.fragment.android_suz.model.SIM;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Sim implements Serializable {
    @PrimaryKey
    @ColumnInfo(name = "id")
    @SerializedName("id")
    private int id;
    @ColumnInfo(name = "idPlan")
    @SerializedName("idPlan")
    private String idPlan;
    @ColumnInfo(name = "nameSim")
    @SerializedName("nameSim")
    private String nameSim;
    @ColumnInfo(name = "sale_6")
    @SerializedName("sale_6")
    private String sale6;
    @ColumnInfo(name = "sale")
    @SerializedName("sale")
    private String sale;
    @ColumnInfo(name = "remanis")
    @SerializedName("remanis")
    private String remanis;
    @ColumnInfo(name = "remanisRarus")
    @SerializedName("remanisRarus")
    private String remanisRarus;
    @ColumnInfo(name = "plan")
    @SerializedName("plan")
    private String plan;
    @ColumnInfo(name = "planVypol")
    @SerializedName("planVypol")
    private String planVypol;
    @ColumnInfo(name = "shop")
    @SerializedName("shop")
    private String shop;
    @ColumnInfo(name = "view")
    @SerializedName("view")
    private String view;
    @ColumnInfo(name = "toOrder")
    @SerializedName("toOrder")
    private String toOrder;
    @ColumnInfo(name = "distribution")
    @SerializedName("distribution")
    private String distribution;
    @ColumnInfo(name = "remanisSkladSIM")
    @SerializedName("remanisSkladSIM")
    private String remanisSkladSIM;
    @ColumnInfo(name = "remanisSIM")
    @SerializedName("remanisSIM")
    private String remanisSIM;
    @ColumnInfo(name = "averageSalesSIM")
    @SerializedName("averageSalesSIM")
    private String averageSalesSIM;
    @ColumnInfo(name = "recommendedToOrder")
    @SerializedName("recommendedToOrder")
    private String recommendedToOrder;

    public Sim(int id, String idPlan, String nameSim, String sale6, String sale, String remanis, String remanisRarus, String plan, String planVypol, String shop, String view, String toOrder, String distribution, String remanisSkladSIM, String remanisSIM, String averageSalesSIM, String recommendedToOrder) {
        this.id = id;
        this.idPlan = idPlan;
        this.nameSim = nameSim;
        this.sale6 = sale6;
        this.sale = sale;
        this.remanis = remanis;
        this.remanisRarus = remanisRarus;
        this.plan = plan;
        this.planVypol = planVypol;
        this.shop = shop;
        this.view = view;
        this.toOrder = toOrder;
        this.distribution = distribution;
        this.remanisSkladSIM = remanisSkladSIM;
        this.remanisSIM = remanisSIM;
        this.averageSalesSIM = averageSalesSIM;
        this.recommendedToOrder = recommendedToOrder;
    }

    public Sim() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(String idPlan) {
        this.idPlan = idPlan;
    }

    public String getNameSim() {
        return nameSim;
    }

    public void setNameSim(String nameSim) {
        this.nameSim = nameSim;
    }

    public String getSale6() {
        return sale6;
    }

    public void setSale6(String sale6) {
        this.sale6 = sale6;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getRemanis() {
        return remanis;
    }

    public void setRemanis(String remanis) {
        this.remanis = remanis;
    }

    public String getRemanisRarus() {
        return remanisRarus;
    }

    public void setRemanisRarus(String remanisRarus) {
        this.remanisRarus = remanisRarus;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlanVypol() {
        return planVypol;
    }

    public void setPlanVypol(String planVypol) {
        this.planVypol = planVypol;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getToOrder() {
        return toOrder;
    }

    public void setToOrder(String toOrder) {
        this.toOrder = toOrder;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getRemanisSkladSIM() {
        return remanisSkladSIM;
    }

    public void setRemanisSkladSIM(String remanisSkladSIM) {
        this.remanisSkladSIM = remanisSkladSIM;
    }

    public String getRemanisSIM() {
        return remanisSIM;
    }

    public void setRemanisSIM(String remanisSIM) {
        this.remanisSIM = remanisSIM;
    }

    public String getAverageSalesSIM() {
        return averageSalesSIM;
    }

    public void setAverageSalesSIM(String averageSalesSIM) {
        this.averageSalesSIM = averageSalesSIM;
    }

    public String getRecommendedToOrder() {
        return recommendedToOrder;
    }

    public void setRecommendedToOrder(String recommendedToOrder) {
        this.recommendedToOrder = recommendedToOrder;
    }

    @Override
    public String toString() {
        return "Sim{" +
                "id=" + id +
                ", idPlan='" + idPlan + '\'' +
                ", nameSim='" + nameSim + '\'' +
                ", sale6='" + sale6 + '\'' +
                ", sale='" + sale + '\'' +
                ", remanis='" + remanis + '\'' +
                ", remanisRarus='" + remanisRarus + '\'' +
                ", plan='" + plan + '\'' +
                ", planVypol='" + planVypol + '\'' +
                ", shop='" + shop + '\'' +
                ", view='" + view + '\'' +
                ", toOrder='" + toOrder + '\'' +
                ", distribution='" + distribution + '\'' +
                ", remanisSkladSIM='" + remanisSkladSIM + '\'' +
                ", remanisSIM='" + remanisSIM + '\'' +
                ", averageSalesSIM='" + averageSalesSIM + '\'' +
                ", recommendedToOrder='" + recommendedToOrder + '\'' +
                '}';
    }
}
