package com.mkfnx.officehoursnearsoft.data.source;

import com.mkfnx.officehoursnearsoft.data.Venue;

import java.util.List;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenuesRepository implements VenuesDataSource {

    private static VenuesRepository INSTANCE = null;

    private final VenuesDataSource venuesRemoteDataSource;

    private VenuesRepository(VenuesDataSource venuesRemoteDataSource) {
        this.venuesRemoteDataSource = venuesRemoteDataSource;
    }

    public static VenuesRepository getInstance(VenuesDataSource venuesRemoteDataSource) {
        if ( INSTANCE == null ) {
            INSTANCE = new VenuesRepository(venuesRemoteDataSource);
        }
        return INSTANCE;
    }
    
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public void getVenues(LoadVenuesCallback loadVenuesCallback) {
        getVenuesFromRemoteDataSource(loadVenuesCallback);
    }

    private void getVenuesFromRemoteDataSource(final LoadVenuesCallback loadVenuesCallback) {
        venuesRemoteDataSource.getVenues(loadVenuesCallback);
    }
}
