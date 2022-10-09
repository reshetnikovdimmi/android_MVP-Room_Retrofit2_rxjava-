package com.fragment.android_suz.model.SIM;

import com.fragment.android_suz.model.shop.Shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListSim implements Serializable {
    private ArrayList<Sim> data = new ArrayList<>();
    public List<Sim> getData() {
        return data;
    }

    public void setData(ArrayList<Sim> data) {
        this.data =  data;
    }


}
