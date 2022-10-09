package com.fragment.android_suz.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.fragment.android_suz.model.SIM.Sim;
import com.fragment.android_suz.model.promo.Promo;
import com.fragment.android_suz.model.shop.Shop;

import java.util.List;
@Dao
public interface MySUZDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertShop(List<Shop> shop);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPromo(List<Promo> promo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSim(List<Sim> sim);

    @Query("SELECT * from shop")
    List<Shop> getShop();

    @Query("SELECT * from promo")
    List<Promo> getPromo();

    @Query("SELECT * from sim")
    List<Sim> getSim();

    @Query("select * from promo where `endPromo` >= :date and `startPromo` <= :date order by `endPromo`asc")
    List<Promo> getAlbums(String date);

    @Query("select * from promo where `startPromo` > :date order by `endPromo`desc")
    List<Promo> getFuture_promos(String date);

    @Query("select * from promo where `startPromo` == :date")
    List<Promo> getStartPromo(String date);

    @Query("select * from promo where `endPromo` == :date")
    List<Promo> getEndsPromo(String date);

    @Query("select * from promo where `endPromo` >= :date and `startPromo` <= :date GROUP by `price`")
    List<Promo> getEndPromo(String date);
}
