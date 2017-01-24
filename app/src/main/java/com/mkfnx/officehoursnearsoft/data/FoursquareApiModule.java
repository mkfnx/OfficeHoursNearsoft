package com.mkfnx.officehoursnearsoft.data;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mkfnx on 23/01/17.
 */

@Module
public class FoursquareApiModule {
    String baseUrl;

    public FoursquareApiModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    FoursquareService providesFoursquareService(Retrofit retrofit) {
        return retrofit.create(FoursquareService.class);
    }
}
