package com.fragment.android_suz.ui.Authorization;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fragment.android_suz.R;
import com.fragment.android_suz.common.PresenterFragment;
import com.fragment.android_suz.common.RefreshOwner;
import com.fragment.android_suz.common.UpdateRv;
import com.fragment.android_suz.model.stoks.Storage;

import java.util.ArrayList;

public class FragmentInsideFragment extends PresenterFragment<PromoPresenter> implements UpdateRv.callback2 {
    private Storage mStorage;
    private RefreshOwner mRefreshOwner;
    private RecyclerView recyclerView_2;
    private DynamicRvAdapter dynamicRvAdapter;
    private ArrayList<DynamicRvModel> items = new ArrayList<>();

    public static FragmentInsideFragment newInstance(Bundle args) {
        FragmentInsideFragment fragment = new FragmentInsideFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inside, container, false);
        recyclerView_2 = view.findViewById(R.id.rv_3);
        return view;
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

    @Override
    protected PromoPresenter getPresenter() {
        return null;
    }

    @Override
    public void callback2(String x) {
        dynamicRvAdapter = new DynamicRvAdapter(mStorage.getShopSimX(x), getActivity());
        recyclerView_2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView_2.setAdapter(dynamicRvAdapter);
    }
}