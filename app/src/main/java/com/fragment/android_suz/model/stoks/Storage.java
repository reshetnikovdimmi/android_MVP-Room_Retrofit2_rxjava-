package com.fragment.android_suz.model.stoks;

import com.fragment.android_suz.database.MySUZDao;
import com.fragment.android_suz.model.SIM.ListSim;
import com.fragment.android_suz.model.SIM.Sim;
import com.fragment.android_suz.model.promo.ListPromo;
import com.fragment.android_suz.model.promo.Promo;
import com.fragment.android_suz.model.shop.ListShop;
import com.fragment.android_suz.model.shop.Shop;
import com.fragment.android_suz.ui.Authorization.DynamicRvModel;
import com.fragment.android_suz.ui.Authorization.StaticRvModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Storage {
    String tele2;
    String shop;
    private MySUZDao mySUZDao;
    ArrayList<StaticRvModel> item;
    ArrayList<DynamicRvModel> items;

    public Storage(MySUZDao mySUZDao) {
        this.mySUZDao = mySUZDao;
    }

    public Storage() {

    }

    public void insertShop(List<Shop> response) {
        mySUZDao.insertShop(response);

    }

    public List<Shop> getShop() {
        List<Shop> shop = mySUZDao.getShop();
        ListShop response = new ListShop();
        response.setData((ArrayList<Shop>) shop);
        return response.getData();
    }


    public List<String> unpacking_shopList() {

        List<Shop> shopList = mySUZDao.getShop();
        ArrayList<String> shops = new ArrayList<>();

        for (int i = 1; i < shopList.size(); i++) {

            shops.add(shopList.get(i).getLogin());

        }
        return shops;
    }

    public void insertPromo(List<Promo> promo) {

        mySUZDao.insertPromo(promo);
    }

    public void insertSim(List<Sim> sim) {

        mySUZDao.insertSim(sim);
    }

    public List<Promo> getPromo() {
        List<Promo> promo = mySUZDao.getPromo();
        ListPromo response = new ListPromo();
        response.setData((ArrayList<Promo>) promo);
        return response.getData();
    }

    public List<Sim> getSim() {
        List<Sim> sim = mySUZDao.getSim();
        ListSim response = new ListSim();
        response.setData((ArrayList<Sim>) sim);
        return response.getData();
    }

    public List<Promo> getStartAndEndPromo() {
        List<Promo> promo = mySUZDao.getEndsPromo(datess());
        ListPromo response = new ListPromo();
        response.setData((ArrayList<Promo>) promo);
        return response.getData();
    }

    public HashMap<String, List<Promo>> listChild() {

        HashMap<String, List<Promo>> listChild = new HashMap<String, List<Promo>>();

        List<Promo> header3 = mySUZDao.getAlbums(datess());
        List<Promo> header1 = mySUZDao.getEndPromo(datess());
        List<Promo> header2 = mySUZDao.getFuture_promos(datess());
        List<Promo> header4 = mySUZDao.getStartPromo(datess());
        List<Promo> header5 = mySUZDao.getEndsPromo(datess());

        listChild.put(listHeader().get(0), header3);
        listChild.put(listHeader().get(1), header1);
        listChild.put(listHeader().get(2), header2);
        listChild.put(listHeader().get(3), header4);
        listChild.put(listHeader().get(4), header5);

        return listChild;
    }

    public List<String> listHeader() {

        List<String> listHeader = new ArrayList<String>();

        listHeader.add("Текущие Promo");
        listHeader.add("Прошедшие Promo");
        listHeader.add("Будующие Promo");
        listHeader.add("Stapt Promo");
        listHeader.add("End Promo");

        return listHeader;
    }

    public static String datess() {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        return date;
    }

    public void getShoName(String t2) {
        tele2 = t2;
        item = new ArrayList<>();
        List<Shop> shop = mySUZDao.getShop();
        for (int i = 0; i < shop.size(); i++) {
            if (shop.get(i).getSimT2().equals(t2) || shop.get(i).getSimMts().equals(t2) || shop.get(i).getSimMf().equals(t2)) {
                item.add(new StaticRvModel(mySUZDao.getShop().get(i).getName()));
            }

        }

    }

    public ArrayList<StaticRvModel> getShopName() {
        return item;
    }

    public ArrayList<DynamicRvModel> getShopSim(String text, String shopView) {
        shop = text;
        items = new ArrayList<>();

        List<Sim> sim = mySUZDao.getSim();
        for (int i = 0; i < sim.size(); i++) {
            if (sim.get(i).getShop().equals(text) && sim.get(i).getView().equals(shopView)) {
                items.add(new DynamicRvModel(sim.get(i).getNameSim(), sim.get(i).getRemanis()));
            }
        }
        return items;
    }

    public ArrayList<DynamicRvModel> getShopSimX(String text) {
        items = new ArrayList<>();

        List<Sim> sim = mySUZDao.getSim();
        for (int i = 0; i < sim.size(); i++) {
            if (sim.get(i).getShop().equals(shop) && sim.get(i).getView().equals(tele2) && sim.get(i).getNameSim().equals(text)) {
                items.add(new DynamicRvModel("остаток", sim.get(i).getRemanis()));
                items.add(new DynamicRvModel("продажи в тек мес", sim.get(i).getSale()));
                items.add(new DynamicRvModel("продажи за 6 мес", sim.get(i).getSale6()));
                items.add(new DynamicRvModel("потребность", sim.get(i).getDistribution()));
                items.add(new DynamicRvModel("план", sim.get(i).getPlan()));
                items.add(new DynamicRvModel("% выполн плана", sim.get(i).getPlanVypol()));
            }
        }
        return items;
    }


    public String getShopView() {
        return tele2;
    }

    public interface StorageOwner {
        Storage obtainStorage();
    }
}