package com.fragment.android_suz.ui.Authorization;

import android.os.Bundle;
import android.view.View;

import com.fragment.android_suz.R;
import com.fragment.android_suz.StartActivity;
import com.fragment.android_suz.common.BasePresenter;
import com.fragment.android_suz.model.stoks.Storage;
import com.fragment.android_suz.utils.ApiUtils;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PromoPresenter extends BasePresenter {
    private final PromoView mView;
    private final Storage mStorage;

    public PromoPresenter(PromoView mView, Storage mStorage) {
        this.mView = mView;
        this.mStorage = mStorage;
    }

    public void getPromos() {
        mCompositeDisposable.add(ApiUtils.getApiService().getAllPromos()
                .doOnSuccess(Promo -> mStorage.insertPromo(Promo))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getPromo() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mView.hideRefresh())
                .subscribe(response -> mView.showPromos(),
                        throwable -> mView.showError())
        );

    }
    public void getSimos() {
        mCompositeDisposable.add(ApiUtils.getApiService().getAllSim()
                .doOnSuccess(Sim-> mStorage.insertSim(Sim))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getSim() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mView.hideRefresh())
                .subscribe(response -> mView.showPromos(),
                        throwable -> mView.showError())
        );

    }
    public void loadShop() {
        mCompositeDisposable.add(ApiUtils.getApiService().getAllShop()
                .doOnSuccess(Shop->mStorage.insertShop(Shop))
                .onErrorReturn(throwable ->
                        ApiUtils.NETWORK_EXCEPTIONS.contains(throwable.getClass()) ? mStorage.getShop() : null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mView.hideRefresh())
                .subscribe(response -> mView.showPromos(),
                        throwable -> mView.showError())
        );
    }
    public void Authorization(String login, String pasword) {
        mCompositeDisposable.add(ApiUtils.getApiService().postData(login,
                        pasword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                // .doOnSubscribe(disposable -> mRefreshOwner.setRefreshState(true))
                .doFinally(() -> mView.hideRefresh())
                .subscribe(response -> mView.showAuthorization(response),
                        throwable -> mView.showError())
        );

    }
}
