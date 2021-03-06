package com.mkfnx.officehoursnearsoft.data.source;

import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponse;
import com.mkfnx.officehoursnearsoft.data.Venue;

import java.util.List;
import java.util.Observable;

import io.reactivex.Single;

/**
 * Created by mkfnx on 13/01/17.
 */

public interface VenuesDataSource {

    interface LoadVenuesCallback {
        void onVenuesLoaded(List<Venue> venues);
        void onDataNotAvailable();
    }

    void getVenues(LoadVenuesCallback loadVenuesCallback);

    Single<List<Venue>> getVenues();
}
