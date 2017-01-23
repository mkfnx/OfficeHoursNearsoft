package com.mkfnx.officehoursnearsoft.home;

import android.support.annotation.NonNull;

import com.mkfnx.officehoursnearsoft.BasePresenter;
import com.mkfnx.officehoursnearsoft.BaseView;
import com.mkfnx.officehoursnearsoft.data.Venue;

import java.util.List;

/**
 * Created by mkfnx on 13/01/17.
 */

public interface HomeContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showVenues(List<Venue> venues);

        void showVenueDetails(Venue venue);

        public void showLoadingVenuesError();

        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void result(int requestCode, int resultCode);

        void loadVenues();

        void openVenueDetails(@NonNull Venue venue);
    }
}
