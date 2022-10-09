package com.fragment.android_suz.ui.Authorization;


import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fragment.android_suz.R;

import com.fragment.android_suz.StartActivity;

import com.fragment.android_suz.common.PresenterFragment;
import com.fragment.android_suz.common.RefreshOwner;
import com.fragment.android_suz.common.Refreshable;
import com.fragment.android_suz.common.UpdateRv;
import com.fragment.android_suz.model.shop.Shop;
import com.fragment.android_suz.model.stoks.Storage;

import java.util.ArrayList;
import java.util.List;

public class RV_sim_fragment extends PresenterFragment<PromoPresenter> implements Refreshable, PromoView, UpdateRv.callback,UpdateRv.callback2 {
    private View view;
    private View mprogressBar;
    private View tvProgressCircle;
    private Storage mStorage;
    private RefreshOwner mRefreshOwner;
    private PromoPresenter promoPresenter;
    private RecyclerView recyclerView, recyclerView_2;
    private StaticRvAdapter staticRvAdapter;
    DynamicRvAdapter dynamicRvAdapter;
    ArrayList<DynamicRvModel> items = new ArrayList<>();

    public static RV_sim_fragment newInstance(Bundle args) {
        RV_sim_fragment fragment = new RV_sim_fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof Storage.StorageOwner) {
            mStorage = ((Storage.StorageOwner) context).obtainStorage();
        }

        if (context instanceof RefreshOwner) {
            mRefreshOwner = ((RefreshOwner) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fr_sim_t2, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        mprogressBar = view.findViewById(R.id.progressBar);
        tvProgressCircle = view.findViewById(R.id.progressBar);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null && getArguments() != null) {
            getActivity().setTitle(getArguments().getString("USERNAME_KEY"));
        }
        setHasOptionsMenu(true);
        promoPresenter = new PromoPresenter(this, mStorage);
        onRefreshData();

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sim_fr_menu, menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.in_sim_t2:
                mStorage.getShoName("t2");
                RecyclerViews();
                getActivity().setTitle(R.string.sim_t2);
                break;
            case R.id.in_sim_t2m:
                mStorage.getShoName("t2m");
                RecyclerViews();
                getActivity().setTitle(R.string.sim_t2m);
                break;
            case R.id.in_sim_mts:
                mStorage.getShoName("mts");
                RecyclerViews();
                getActivity().setTitle(R.string.sim_mts);
                break;
            case R.id.in_sim_mf:
                mStorage.getShoName("mf");
                RecyclerViews();
                getActivity().setTitle(R.string.sim_mf);

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {
        mRefreshOwner.setRefreshState(false);
    }

    @Override
    public void showError() {
        mprogressBar.setVisibility(View.GONE);
        showMessage(R.string.request_error);
    }

    @Override
    protected PromoPresenter getPresenter() {
        return promoPresenter;
    }

    @Override
    public void onRefreshData() {
        promoPresenter.getSimos();
    }

    @Override
    public void showPromos() {
        mprogressBar.setVisibility(View.GONE);
        RecyclerViews();
    }

    @Override
    public void showAuthorization(List<Shop> response) {

    }

    private void RecyclerViews() {
        recyclerView = view.findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(mStorage.getShopName(),mStorage.getShopView(), getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        items = new ArrayList<>();

        recyclerView_2 = view.findViewById(R.id.rv_2);
        dynamicRvAdapter = new DynamicRvAdapter(items,getActivity(),this::callback2);
        recyclerView_2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_2.setAdapter(dynamicRvAdapter);
    }

    @Override
    public void callback(ArrayList<DynamicRvModel> item, int position) {
        dynamicRvAdapter = new DynamicRvAdapter(item,getActivity(),this::callback2);
        dynamicRvAdapter.notifyDataSetChanged();
        recyclerView_2.setAdapter(dynamicRvAdapter);
        callback2(null);

    }
    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }


    @Override
    public void callback2(String x) {
        StartActivity activi = (StartActivity) getActivity();
        activi.callback(x);
    }
}
