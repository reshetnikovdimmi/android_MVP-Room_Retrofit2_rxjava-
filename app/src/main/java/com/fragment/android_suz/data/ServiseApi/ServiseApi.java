package com.fragment.android_suz.data.ServiseApi;

import com.fragment.android_suz.model.SIM.Sim;
import com.fragment.android_suz.model.promo.Promo;
import com.fragment.android_suz.model.shop.Shop;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiseApi {
    @GET("/api")
    Single<List<Shop>> getAllShop();
    @GET("/api/promo")
    Single<List<Promo>> getAllPromos();
    @GET("/api/SIM")
    Single<List<Sim>> getAllSim();
    @FormUrlEncoded
    @POST("/api/save")
    Single<List<Shop>> postData(@Field("login") String login, @Field("password") String pass);
}
