package com.mkfnx.officehoursnearsoft.data.source;

import com.mkfnx.officehoursnearsoft.data.Venue;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by mkfnx on 13/01/17.
 */

public interface VenuesDataSource {

    Single<List<Venue>> getVenues();
}
