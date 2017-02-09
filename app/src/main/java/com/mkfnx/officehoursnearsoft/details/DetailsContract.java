package com.mkfnx.officehoursnearsoft.details;

import com.mkfnx.officehoursnearsoft.BasePresenter;
import com.mkfnx.officehoursnearsoft.BaseView;
import com.mkfnx.officehoursnearsoft.data.Venue;

/**
 * Created by mkfnx on 13/01/17.
 */

public interface DetailsContract {
    interface View extends BaseView<Presenter> {
        void showVenueDetails(Venue venue);
    }

    interface Presenter extends BasePresenter {
        void loadVenueDetails();
    }
}
