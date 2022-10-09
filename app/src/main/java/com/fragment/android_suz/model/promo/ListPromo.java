package com.fragment.android_suz.model.promo;

import com.fragment.android_suz.model.shop.Shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListPromo implements Serializable {
    private ArrayList<Promo> data = new ArrayList<>();
    public List<Promo> getData() {
        return data;
    }

    public void setData(ArrayList<Promo> data) {
        this.data =  data;
    }
}
