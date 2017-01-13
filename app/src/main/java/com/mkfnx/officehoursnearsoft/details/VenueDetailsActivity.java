package com.mkfnx.officehoursnearsoft.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.mkfnx.officehoursnearsoft.R;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.util.ActivityUtils;

public class VenueDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_SELECTED_VENUE = "SELECTED_VENUE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_venue_details);

        Venue selectedVenue = getIntent().getParcelableExtra(EXTRA_SELECTED_VENUE);
        Log.d("Details", "onCreate: " +  selectedVenue.getName());

        VenueDetailsFragment venueDetailsFragment = (VenueDetailsFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if ( venueDetailsFragment == null ) {
            venueDetailsFragment = VenueDetailsFragment.newInstance(selectedVenue);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    venueDetailsFragment, R.id.contentFrame);
        }

        new VenueDetailsPresenter(
                selectedVenue,
                venueDetailsFragment
        );
    }
}
