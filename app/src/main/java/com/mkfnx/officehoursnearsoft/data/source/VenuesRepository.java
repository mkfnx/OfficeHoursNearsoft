package com.mkfnx.officehoursnearsoft.data.source;

import com.mkfnx.officehoursnearsoft.data.Venue;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenuesRepository implements VenuesDataSource {

    private final VenuesDataSource venuesRemoteDataSource;

    public VenuesRepository(VenuesDataSource venuesRemoteDataSource) {
        this.venuesRemoteDataSource = venuesRemoteDataSource;
    }

    @Override
    public Single<List<Venue>> getVenues() {
        return venuesRemoteDataSource.getVenues();
    }
}
