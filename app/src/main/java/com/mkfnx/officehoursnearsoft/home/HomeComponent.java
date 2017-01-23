package com.mkfnx.officehoursnearsoft.home;

import com.mkfnx.officehoursnearsoft.ApplicationComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by mkfnx on 20/01/17.
 */

@Singleton
@Component(dependencies = {ApplicationComponent.class}, modules = {HomeModule.class})
public interface HomeComponent {
    void inject(MainFragment mainFragment);
}
