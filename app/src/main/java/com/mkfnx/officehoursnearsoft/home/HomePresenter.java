package com.mkfnx.officehoursnearsoft.home;

import android.support.annotation.NonNull;

import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.source.VenuesDataSource;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;

import java.util.List;

/**
 * Created by mkfnx on 13/01/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final VenuesRepository venuesRepository;

    private final HomeContract.View view;

    public HomePresenter(VenuesRepository venuesRepository, HomeContract.View view) {
        this.venuesRepository = venuesRepository;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void loadVenues() {
        view.setLoadingIndicator(true);
        venuesRepository.getVenues(new VenuesDataSource.LoadVenuesCallback() {
            @Override
            public void onVenuesLoaded(List<Venue> venues) {
                view.setLoadingIndicator(false);
                view.showVenues(venues);
            }

            @Override
            public void onDataNotAvailable() {
                view.setLoadingIndicator(false);
                view.showLoadingVenuesError();
            }
        });
    }

    @Override
    public void openVenueDetails(@NonNull Venue venue) {
        view.showVenueDetails(venue);
    }

    @Override
    public void start() {
        loadVenues();
    }
}
