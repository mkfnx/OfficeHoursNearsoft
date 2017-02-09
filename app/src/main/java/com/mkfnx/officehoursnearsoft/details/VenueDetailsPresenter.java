package com.mkfnx.officehoursnearsoft.details;

import com.mkfnx.officehoursnearsoft.data.Venue;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenueDetailsPresenter implements DetailsContract.Presenter {

    private Venue venue;
    private DetailsContract.View view;

    public VenueDetailsPresenter(Venue venue, DetailsContract.View view) {
        this.venue = venue;
        this.view = view;

        view.setPresenter(this);
    }

    @Override
    public void loadVenueDetails() {
        view.showVenueDetails(venue);
    }

    @Override
    public void start() {
        loadVenueDetails();
    }

    @Override
    public void stop() {
        this.view = null;
    }

    public DetailsContract.View getView() {
        return view;
    }
}
