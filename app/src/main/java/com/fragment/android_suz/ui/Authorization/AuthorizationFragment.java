package com.fragment.android_suz.ui.Authorization;

import static android.R.layout.simple_spinner_item;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.fragment.android_suz.R;
import com.fragment.android_suz.StartActivity;
import com.fragment.android_suz.common.PresenterFragment;
import com.fragment.android_suz.common.RefreshOwner;
import com.fragment.android_suz.common.Refreshable;
import com.fragment.android_suz.model.shop.Shop;
import com.fragment.android_suz.model.stoks.SharedPreferencesHelper;
import com.fragment.android_suz.model.stoks.Storage;
import com.fragment.android_suz.utils.ApiUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthorizationFragment extends PresenterFragment<PromoPresenter> implements Refreshable, PromoView {

    private View v;
    private View mprogressBar;
    private View startfragment;
    private Button butvhod;
    private EditText ETPasword;
    private Spinner spinner;
    private String mLogin;
    private PromoPresenter promoPresenter;
    private RefreshOwner mRefreshOwner;
    private Storage mStorage;
    private SharedPreferencesHelper mSharedPreferencesHelper;

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
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
        v = inflater.inflate(R.layout.fr_start_fr, container, false);

               return v;
    }

    @Override
    public void onViewCreated(@NonNull View v, @Nullable Bundle savedInstanceState) {
        spinner = (Spinner) v.findViewById(R.id.spinner);
        mprogressBar = v.findViewById(R.id.progressBar);
        startfragment = v.findViewById(R.id.startfragment);
        butvhod = v.findViewById(R.id.ButVhod);
        ETPasword=v.findViewById(R.id.ETP);
        butvhod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isInputValid()) {

                    promoPresenter.Authorization(mLogin,ETPasword.getText().toString());

                } else {
                    showMessage(R.string.input_error);
                }
                mprogressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLogin = (String) parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinner.setOnItemSelectedListener(itemSelectedListener);

        mSharedPreferencesHelper = new SharedPreferencesHelper(getActivity());
        promoPresenter = new PromoPresenter(this, mStorage);
        onRefreshData();

    }

    private void ArrayAdapter() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), simple_spinner_item, mStorage.unpacking_shopList());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    protected PromoPresenter getPresenter() {
        return promoPresenter;
    }

    private boolean isInputValid() {
        if (isPasswordsValid()) {
            return true;
        }
        return false;
    }

    private boolean isPasswordsValid() {
        String password = ETPasword.getText().toString();
        return  !TextUtils.isEmpty(password);
    }

    private void showMessage(@StringRes int string) {
        Toast.makeText(getActivity(), string, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showRefresh() {

    }

    @Override
    public void hideRefresh() {

    }

    @Override
    public void showError() {
        startfragment.setVisibility(View.GONE);
        mprogressBar.setVisibility(View.VISIBLE);
        showMessage(R.string.request_error);
    }

    @Override
    public void onRefreshData() {
        if (mSharedPreferencesHelper.isLoggin())  {
            HashMap<String, String> user = mSharedPreferencesHelper.getUserDetail();
            promoPresenter.Authorization(user.get(mSharedPreferencesHelper.NAME),user.get(mSharedPreferencesHelper.EMAIL));
        } else {
            promoPresenter.loadShop();
        }
    }

    @Override
    public void showPromos() {
        startfragment.setVisibility(View.VISIBLE);
        mprogressBar.setVisibility(View.GONE);
        ArrayAdapter();
    }

    @Override
    public void showAuthorization(List<Shop> response) {
        HashMap<String, String> user = new HashMap<>();
        user.put(SharedPreferencesHelper.NAME, mLogin);
        user.put(SharedPreferencesHelper.EMAIL, ETPasword.getText().toString());
        Bundle args = new Bundle();
        args.putSerializable(StartActivity.USERNAME_KEY, user);
        if (response.get(0).getLogin().equals("success")){

            mprogressBar.setVisibility(View.GONE);

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, MainFragmentMenu.newInstance(args))
                    .addToBackStack(MainFragmentMenu.class.getName())
                    .commit();
        }else {

            mprogressBar.setVisibility(View.GONE);
            showMessage(R.string.login_error);
        }
    }
}
