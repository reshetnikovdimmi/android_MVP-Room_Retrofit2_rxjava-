package com.fragment.android_suz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import com.fragment.android_suz.common.SingleFragmentActivity;
import com.fragment.android_suz.ui.Authorization.AuthorizationFragment;
import com.fragment.android_suz.ui.Authorization.FragmentInsideFragment;
import com.fragment.android_suz.utils.ApiUtils;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StartActivity extends SingleFragmentActivity {

    public static final String USERNAME_KEY = "USERNAME_KEY";

    public void callback(String text) {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(USERNAME_KEY);
        FragmentInsideFragment fragment_2 = (FragmentInsideFragment) fragment;
        fragment_2.callback2(text);
    }

    @Override

    protected Fragment getFragment() {

        return AuthorizationFragment.newInstance();
    }

}