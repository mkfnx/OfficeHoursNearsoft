package com.mkfnx.officehoursnearsoft;

import com.bumptech.glide.RequestManager;
import com.mkfnx.officehoursnearsoft.data.FoursquareApiModule;
import com.mkfnx.officehoursnearsoft.data.FoursquareService;

import dagger.Component;

/**
 * Created by mkfnx on 23/01/17.
 */

@Component(modules = { FoursquareApiModule.class, ApplicationModule.class })
public interface ApplicationComponent {
    FoursquareService providesFoursquareService();

    RequestManager providesGlideRequestManager();
}
