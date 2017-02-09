package com.mkfnx.officehoursnearsoft.details;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.mkfnx.officehoursnearsoft.BuildConfig;
import com.mkfnx.officehoursnearsoft.R;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.VenueFeaturedPhotos;
import com.mkfnx.officehoursnearsoft.data.VenuePhoto;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import static org.junit.Assert.assertNotNull;

/**
 * Created by mkfnx on 09/02/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 24)
public class VenueDetailsViewTest {

    @Mock
    private VenueDetailsPresenter venueDetailsPresenter;
    @Mock
    private Venue venue;

    private DetailsContract.View view;
    private VenueDetailsFragment venueDetailsFragment;

    private final VenuePhoto VENUE_PHOTO = new VenuePhoto("photo1", "prefix", "suffix", 100, 100);
    private final VenueFeaturedPhotos VENUE_FEATURED_PHOTOS= new VenueFeaturedPhotos(1, Lists.newArrayList(VENUE_PHOTO));

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);

        venueDetailsFragment = VenueDetailsFragment.newInstance(venue);
        view = venueDetailsFragment;
        SupportFragmentTestUtil.startVisibleFragment(venueDetailsFragment);
    }

    @Test
    public void testSetPresenter() throws Exception {
        Mockito.when(venue.getFeaturedPhotos()).thenReturn(VENUE_FEATURED_PHOTOS);
        view.setPresenter(venueDetailsPresenter);

        assertNotNull(venueDetailsFragment.getPresenter());
    }

    @Test
    public void testShowVenueDetails() throws Exception {

        final String VENUE_TEST_NAME = "Test Venue";
        final String VENUE_TEST_URL = "Test Venue";

        Mockito.when(venue.getName()).thenReturn(VENUE_TEST_NAME);
        Mockito.when(venue.getUrl()).thenReturn(VENUE_TEST_URL);
        Mockito.when(venue.getFeaturedPhotos()).thenReturn(VENUE_FEATURED_PHOTOS);

        view.showVenueDetails(venue);

        TextView venueTextView = (TextView) venueDetailsFragment.getActivity().findViewById(R.id.venue_name_textview);
        TextView venueDescriptionTextView = (TextView) venueDetailsFragment.getActivity().findViewById(R.id.venue_description_textview);

        Assert.assertEquals(VENUE_TEST_NAME, venueTextView.getText());
        Assert.assertEquals(VENUE_TEST_URL, venueDescriptionTextView.getText());
    }
}