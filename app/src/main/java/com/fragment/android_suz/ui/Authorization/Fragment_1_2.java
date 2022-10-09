package com.fragment.android_suz.ui.Authorization;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fragment.android_suz.R;
import com.fragment.android_suz.StartActivity;

import java.util.ArrayList;

public class Fragment_1_2 extends Fragment {
    public static Fragment_1_2 newInstance(Bundle args) {
        Fragment_1_2 fragment = new Fragment_1_2();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1_2, container, false);
        Bundle args = new Bundle();
        args.putSerializable(StartActivity.USERNAME_KEY, "SIM T2");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container_a, RV_sim_fragment.newInstance(args), "USERNAME_KEY")
                .replace(R.id.container_b, FragmentInsideFragment.newInstance(args), "USERNAME_KEY")
                .commit();

        return view;
    }
}

