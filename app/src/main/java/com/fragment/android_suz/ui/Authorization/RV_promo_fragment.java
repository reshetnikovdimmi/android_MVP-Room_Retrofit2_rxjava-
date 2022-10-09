package com.fragment.android_suz.ui.Authorization;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import com.fragment.android_suz.R;
import com.fragment.android_suz.common.PresenterFragment;
import com.fragment.android_suz.common.RefreshOwner;
import com.fragment.android_suz.common.Refreshable;
import com.fragment.android_suz.model.shop.Shop;
import com.fragment.android_suz.model.stoks.Storage;

import java.util.List;

public class RV_promo_fragment extends PresenterFragment<PromoPresenter> implements Refreshable, PromoView {
    private View mprogressBar;
    private View view;
    private View tvProgressCircle;
    private ListAdapter listAdapter;
    private Storage mStorage;
    private RefreshOwner mRefreshOwner;
    private ExpandableListView mExpandableListView;
    private PromoPresenter promoPresenter;

    public static RV_promo_fragment newInstance() {
        RV_promo_fragment fragment = new RV_promo_fragment();
        //  fragment.setArguments();
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

        view = inflater.inflate(R.layout.rv_promo, container, false);

        if (getArguments() != null) {
            String mUsername = getArguments().getString("USERNAME_KEY");
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        mprogressBar = view.findViewById(R.id.progressBar);
        tvProgressCircle = view.findViewById(R.id.progressBar);
        mExpandableListView = view.findViewById(R.id.expandable_listview);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            getActivity().setTitle(R.string.PROMO);
        }

        promoPresenter = new PromoPresenter(this, mStorage);
        onRefreshData();
    }

    private void listData() {
        listAdapter = new ListAdapter(this, getActivity(), mStorage.listChild(), mStorage.listHeader());

        mExpandableListView.setAdapter(listAdapter);
        mExpandableListView.expandGroup(0);

        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                // Toast.makeText(getContext(), listHeader.get(groupPosition) + " : " + listChild.get(listHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // ListView Group Click listener
        mExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                // Toast.makeText(getContext(), listHeader.get(groupPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // ListView Group Expand listener
        mExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {

                //   Toast.makeText(getContext(), listHeader.get(groupPosition) + " Expanded", Toast.LENGTH_SHORT).show();
            }
        });

        //List Group collapsed listener
        mExpandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {

                // Toast.makeText(getContext(), listHeader.get(groupPosition) + " Collapsed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetach() {
        mStorage = null;
        mRefreshOwner = null;
        super.onDetach();
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
    public void showPromos() {
        mprogressBar.setVisibility(View.GONE);
        listData();
    }

    @Override
    public void showAuthorization(List<Shop> response) {

    }

    @Override
    public void onRefreshData() {
        promoPresenter.getPromos();
    }
}
