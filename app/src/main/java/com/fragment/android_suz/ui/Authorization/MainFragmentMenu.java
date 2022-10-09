package com.fragment.android_suz.ui.Authorization;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fragment.android_suz.R;
import com.fragment.android_suz.ServiceNotification.MyServiceNotification;
import com.fragment.android_suz.StartActivity;
import com.fragment.android_suz.common.RefreshOwner;
import com.fragment.android_suz.model.stoks.SharedPreferencesHelper;
import com.fragment.android_suz.model.stoks.Storage;

import java.util.HashMap;

public class MainFragmentMenu extends Fragment {
    private SharedPreferencesHelper mSharedPreferencesHelper;
    private TextView shopTV;
    private HashMap<String, String> mUser;
    private ImageButton stocksBUT;
    private ImageButton Sim;
    private Storage mStorage;
    private RefreshOwner mRefreshOwner;
    private  MyServiceNotification myServiceNotification;

    public static MainFragmentMenu newInstance(Bundle args) {
        MainFragmentMenu fragment = new MainFragmentMenu();
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
        View v = inflater.inflate(R.layout.fr_menu, container, false);
        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        shopTV= v.findViewById(R.id.shopTV);
        Sim= v.findViewById(R.id.t2);
        stocksBUT =v.findViewById(R.id.stocksBUT);

        return v;
    }
    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {

        stocksBUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, RV_promo_fragment.newInstance(), "USERNAME_KEY")
                        .addToBackStack(RV_promo_fragment.class.getName())
                        .commit();
            }
        });
        Sim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putSerializable(StartActivity.USERNAME_KEY, "SIM T2");
                mStorage.getShoName("t2");
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, Fragment_1_2.newInstance(args), "USERNAME_KEY")

                        .addToBackStack(RV_sim_fragment.class.getName())
                        .commit();
            }
        });

        myServiceNotification = new MyServiceNotification();
        Context context = getActivity();
        Intent intent = new Intent(context, MyServiceNotification.class);
        context.startService(intent);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getArguments() != null) {
            mUser = (HashMap<String, String>) getArguments().getSerializable(StartActivity.USERNAME_KEY);
            shopTV.setText( mUser.get(mSharedPreferencesHelper.NAME));
            getActivity().setTitle(R.string.menu);
        }

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_fr_menu, menu);
        menu.findItem(R.id.auto_input).setChecked(mSharedPreferencesHelper.isLoggin());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.actionLogout:

                mSharedPreferencesHelper.logout();
                getFragmentManager().popBackStack();
                break;

            case R.id.auto_input:

                if (item.isChecked()){
                    item.setChecked(false);
                    mSharedPreferencesHelper.logout();

                }else {
                    item.setChecked(true);
                    mSharedPreferencesHelper.createSession(mUser.get(mSharedPreferencesHelper.NAME),mUser.get(mSharedPreferencesHelper.EMAIL));
                }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
