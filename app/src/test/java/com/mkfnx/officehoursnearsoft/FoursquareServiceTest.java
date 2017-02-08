package com.mkfnx.officehoursnearsoft;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponse;
import com.mkfnx.officehoursnearsoft.data.ExploreVenuesResponseItem;
import com.mkfnx.officehoursnearsoft.data.FoursquareService;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.remote.VenuesRemoteDataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mkfnx on 07/02/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 24)
public class FoursquareServiceTest {
//    FoursquareService foursquareService = Ret

    FoursquareService foursquareService;

    public FoursquareServiceTest() {
        foursquareService = new Retrofit.Builder()
                .baseUrl(OfficeHoursNearsoftApp.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FoursquareService.class);
    }

    @Test
    public void getVenues() throws Exception {

        Observable<ExploreVenuesResponse> venuesResponseObservable = foursquareService.exploreVenuesNearLocationObs(VenuesRemoteDataSource.DEFAULT_LOCATION);
        TestObserver<ExploreVenuesResponse> venuesResponseTestObserver = venuesResponseObservable.test();

        venuesResponseObservable.subscribe(venuesResponseTestObserver);

        venuesResponseTestObserver.assertSubscribed();
        venuesResponseTestObserver.assertComplete();
        venuesResponseTestObserver.assertValueCount(1);
        venuesResponseTestObserver.assertValue(exploreVenuesResponse -> {
            List<ExploreVenuesResponseItem> venuesResponseItemList = exploreVenuesResponse.getResponse().getGroups().get(0).getItems();
            return venuesResponseItemList != null
                    && !venuesResponseItemList.isEmpty();
        });

        Single<List<Venue>> listSingle = venuesResponseObservable
                .flatMapIterable(exploreVenuesResponse -> exploreVenuesResponse.getResponse().getGroups().get(0).getItems())
                .map(ExploreVenuesResponseItem::getVenue)
                .toList();

        TestObserver<List<Venue>> venueListTestObserver = listSingle.test();
        venueListTestObserver.assertValueCount(1);
        venueListTestObserver.assertValue(venues -> {
            return venues != null
                    && !venues.isEmpty();
        });
    }
}
