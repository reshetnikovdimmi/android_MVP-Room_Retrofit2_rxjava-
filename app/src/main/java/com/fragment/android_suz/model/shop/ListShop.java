package com.fragment.android_suz.model.shop;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListShop implements Serializable {

    private ArrayList<Shop> data = new ArrayList<>();
    public List<Shop> getData() {
        return data;
    }

    public void setData(ArrayList<Shop> data) {
        this.data =  data;
    }


}