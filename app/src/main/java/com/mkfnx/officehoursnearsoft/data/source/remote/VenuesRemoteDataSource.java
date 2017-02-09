package com.mkfnx.officehoursnearsoft.data.source.remote;

import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponseItem;
import com.mkfnx.officehoursnearsoft.data.FoursquareService;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.VenuesDataSource;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenuesRemoteDataSource implements VenuesDataSource {

    public final static String DEFAULT_LOCATION = "Mexico City";

    FoursquareService service;

    public VenuesRemoteDataSource(FoursquareService service){
        this.service = service;
    }

    @Override
    public Single<List<Venue>> getVenues() {
        return service.exploreVenuesNearLocationObs(DEFAULT_LOCATION)
                .flatMapIterable(exploreVenuesResponse -> exploreVenuesResponse.getResponse().getGroups().get(0).getItems())
                .map(ExploreVenuesResponseItem::getVenue)
                .toList();
    }
}
