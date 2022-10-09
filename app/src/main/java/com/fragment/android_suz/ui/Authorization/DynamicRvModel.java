package com.fragment.android_suz.ui.Authorization;

public class DynamicRvModel {

    String tmc,remanis;

    public DynamicRvModel(String tmc, String remanis) {
        this.tmc = tmc;
        this.remanis = remanis;
    }

    public String getTmc() {
        return tmc;
    }
    public String getRemanis() {
        return remanis;
    }
}
