package com.fragment.android_suz.common;

import android.app.Activity;

import com.fragment.android_suz.ui.Authorization.DynamicRvModel;

import java.util.ArrayList;

public interface UpdateRv {
    interface callback{
        void callback(ArrayList<DynamicRvModel> item, int position);
    }
    interface callback2{
        void callback2(String x);
    }
}
