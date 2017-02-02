package com.mkfnx.officehoursnearsoft.data.source;

import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponse;
import com.mkfnx.officehoursnearsoft.data.Venue;

import java.util.List;
import java.util.Observable;

/**
 * Created by mkfnx on 13/01/17.
 */

public interface VenuesDataSource {

    interface LoadVenuesCallback {
        void onVenuesLoaded(List<Venue> venues);
        void onDataNotAvailable();
    }

    void getVenues(LoadVenuesCallback loadVenuesCallback);

    io.reactivex.Observable<List<Venue>> getVenues();
}
