package com.fragment.android_suz.ui.Authorization;

import androidx.annotation.NonNull;

import com.fragment.android_suz.common.BaseView;
import com.fragment.android_suz.model.promo.Promo;
import com.fragment.android_suz.model.shop.Shop;

import java.util.HashMap;
import java.util.List;

public interface PromoView extends BaseView {
    void showPromos();
    void showAuthorization(List<Shop> response);
}
