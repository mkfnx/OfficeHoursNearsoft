package com.mkfnx.officehoursnearsoft;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.mkfnx.officehoursnearsoft.util.BaseScheduler;
import com.mkfnx.officehoursnearsoft.util.AppScheduler;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mkfnx on 23/01/17.
 */

@Module
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    Context provideContext() {
        return context;
    }

    @Provides
    RequestManager providesGlideRequestManager(Context context) {
        return Glide.with(context);
    }

    @Provides
    BaseScheduler providesScheduler() {
        return new AppScheduler();
    }
}
