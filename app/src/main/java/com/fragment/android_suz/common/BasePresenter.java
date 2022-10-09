package com.fragment.android_suz.common;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BasePresenter {

    protected final CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void disposeAll() {
        mCompositeDisposable.dispose();
    }
}