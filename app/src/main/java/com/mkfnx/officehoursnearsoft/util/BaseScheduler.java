package com.mkfnx.officehoursnearsoft.util;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

/**
 * Created by mkfnx on 08/02/17.
 */

public interface BaseScheduler {
    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();
}
