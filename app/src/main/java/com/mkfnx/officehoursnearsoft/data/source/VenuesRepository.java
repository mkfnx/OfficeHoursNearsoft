package com.mkfnx.officehoursnearsoft.data.source;

import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponse;
import com.mkfnx.officehoursnearsoft.data.Venue;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenuesRepository implements VenuesDataSource {

    private final VenuesDataSource venuesRemoteDataSource;

    public VenuesRepository(VenuesDataSource venuesRemoteDataSource) {
        this.venuesRemoteDataSource = venuesRemoteDataSource;
    }

    @Override
    public void getVenues(LoadVenuesCallback loadVenuesCallback) {
        getVenuesFromRemoteDataSource(loadVenuesCallback);
    }

    @Override
    public Observable<List<Venue>> getVenues() {
        return venuesRemoteDataSource.getVenues();
    }

    private void getVenuesFromRemoteDataSource(final LoadVenuesCallback loadVenuesCallback) {
        venuesRemoteDataSource.getVenues(loadVenuesCallback);
    }
}
