package com.mkfnx.officehoursnearsoft;

import android.app.Application;

import com.mkfnx.officehoursnearsoft.data.FoursquareApiModule;
import com.mkfnx.officehoursnearsoft.home.HomeComponent;

/**
 * Created by mkfnx on 20/01/17.
 */

public class OfficeHoursNearsoftApp extends Application {

    final String BASE_URL = "https://api.foursquare.com/v2/";
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .foursquareApiModule(new FoursquareApiModule(BASE_URL))
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
