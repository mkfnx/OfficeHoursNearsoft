package com.mkfnx.officehoursnearsoft.details;

import com.mkfnx.officehoursnearsoft.data.FoursquareService;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.VenuesDataSource;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;
import com.mkfnx.officehoursnearsoft.data.source.remote.VenuesRemoteDataSource;
import com.mkfnx.officehoursnearsoft.home.HomeContract;
import com.mkfnx.officehoursnearsoft.home.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mkfnx on 23/01/17.
 */

@Module
public class DetailsModule {

    private final DetailsContract.View view;
    private final Venue venue;

    public DetailsModule(DetailsContract.View view, Venue venue) {
        this.view = view;
        this.venue = venue;
    }

    @Provides
    DetailsContract.View provideDetailsContractView() {
        return view;
    }

    @Provides
    Venue provideVenue() {
        return venue;
    }

    @Provides
    DetailsContract.Presenter provideDetailsContractPresenter(DetailsContract.View view, Venue venue) {
        return new VenueDetailsPresenter(venue, view);
    }
}
