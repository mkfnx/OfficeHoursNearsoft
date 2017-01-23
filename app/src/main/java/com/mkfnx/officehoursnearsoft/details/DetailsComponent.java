package com.mkfnx.officehoursnearsoft.details;

import com.mkfnx.officehoursnearsoft.ApplicationComponent;

import dagger.Component;

/**
 * Created by mkfnx on 23/01/17.
 */

@Component(dependencies = {ApplicationComponent.class}, modules = {DetailsModule.class})
public interface DetailsComponent {
    void inject(VenueDetailsFragment detailsFragment);
}
