package com.mkfnx.officehoursnearsoft.data.source.remote;

import android.util.Log;

import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponse;
import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponseItem;
import com.mkfnx.officehoursnearsoft.data.FoursquareService;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.VenuesDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenuesRemoteDataSource implements VenuesDataSource {

    private final static String TAG = VenuesRemoteDataSource.class.getSimpleName();
    private final static String DEFAULT_LOCATION = "Mexico City";

    FoursquareService service;

    public VenuesRemoteDataSource(FoursquareService service){
        this.service = service;
    }

    @Override
    public void getVenues(final LoadVenuesCallback loadVenuesCallback) {
        Call<ExploreVenuesResponse> exploreVenuesResponseCall = service.exploreVenuesNearLocation(DEFAULT_LOCATION);

        exploreVenuesResponseCall.enqueue(new Callback<ExploreVenuesResponse>() {
            @Override
            public void onResponse(Call<ExploreVenuesResponse> call, Response<ExploreVenuesResponse> response) {

                if ( response.errorBody() != null ) {
                    Log.e(TAG, "onResponse: " +  response.errorBody());
                    loadVenuesCallback.onDataNotAvailable();
                    return;
                }

                ArrayList<Venue> venues = new ArrayList<Venue>();

                for (ExploreVenuesResponseItem item : response.body().getResponse().getGroups().get(0).getItems() ) {
                    venues.add(item.getVenue());
                }

                loadVenuesCallback.onVenuesLoaded(venues);
            }

            @Override
            public void onFailure(Call<ExploreVenuesResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage(), t);
                loadVenuesCallback.onDataNotAvailable();
            }
        });
    }

    @Override
    public Observable<List<Venue>> getVenues() {
        return service.exploreVenuesNearLocationObs(DEFAULT_LOCATION)
                .map(new Function<ExploreVenuesResponse, List<Venue>>() {
                    @Override
                    public List<Venue> apply(ExploreVenuesResponse exploreVenuesResponse) throws Exception {
                        ArrayList<Venue> venues = new ArrayList<Venue>();

                        for (ExploreVenuesResponseItem item : exploreVenuesResponse.getResponse().getGroups().get(0).getItems() ) {
                            venues.add(item.getVenue());
                        }

                        return venues;
                    }
                });
    }
}
