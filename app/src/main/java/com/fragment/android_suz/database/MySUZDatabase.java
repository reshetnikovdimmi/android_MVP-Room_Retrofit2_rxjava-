package com.fragment.android_suz.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.fragment.android_suz.model.SIM.Sim;
import com.fragment.android_suz.model.promo.Promo;
import com.fragment.android_suz.model.shop.Shop;

@Database(entities = {Shop.class, Promo.class, Sim.class}, version = 3)
public abstract class MySUZDatabase extends RoomDatabase {

    public abstract MySUZDao getMySUZDao();

}