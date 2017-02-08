package com.mkfnx.officehoursnearsoft.util;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by mkfnx on 08/02/17.
 */

public class AppScheduler implements BaseScheduler {
    @NonNull
    @Override
    public io.reactivex.Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public io.reactivex.Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public io.reactivex.Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }
}
