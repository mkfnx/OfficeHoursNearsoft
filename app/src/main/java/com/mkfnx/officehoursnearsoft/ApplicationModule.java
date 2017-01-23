package com.mkfnx.officehoursnearsoft;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

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
        Log.d("provide glide", "context: " + context);
        RequestManager requestManager = Glide.with(context);
        Log.d("provide glide", "request manager: " + requestManager);
        return requestManager;
    }
}
