package com.mkfnx.officehoursnearsoft;

import android.content.ComponentName;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.RequestManager;
import com.google.common.collect.Lists;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.VenueFeaturedPhotos;
import com.mkfnx.officehoursnearsoft.data.VenuePhoto;
import com.mkfnx.officehoursnearsoft.details.VenueDetailsActivity;
import com.mkfnx.officehoursnearsoft.home.HomeContract;
import com.mkfnx.officehoursnearsoft.home.MainActivity;
import com.mkfnx.officehoursnearsoft.home.MainFragment;
import com.mkfnx.officehoursnearsoft.home.VenuesAdapter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.ShadowsAdapter;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;
import org.robolectric.util.ActivityController;

import java.util.List;

/**
 * Created by mkfnx on 08/02/17.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 24)
public class HomeViewTest extends BaseTest {

    @Mock
    private HomeContract.Presenter presenter;
    @Mock
    private RequestManager glideRequestManager;
    @Mock
    private VenuesAdapter venuesAdapter;
    @Mock
    private VenuesAdapter.VenueItemListener venueItemListener;

    private HomeContract.View view;

    private MainFragment mainFragment;

    private List<VenuePhoto> VENUE_PHOTOS;
    private VenueFeaturedPhotos VENUE_FEATURED_PHOTOS;
    private List<Venue> VENUES;

    @Override
    public void setup() throws Exception {
        super.setup();

        VENUE_PHOTOS = Lists.newArrayList(
                new VenuePhoto("VenuePhotoId1", "VenuePhotoPref", "VenuePhotoSuff", 100, 100)
        );
        VENUE_FEATURED_PHOTOS = new VenueFeaturedPhotos(1, VENUE_PHOTOS);
        VENUES = Lists.newArrayList(
                new Venue("VenueId1", "Venue1", "VenueUrl", 10.0, VENUE_FEATURED_PHOTOS),
                new Venue("VenueId2", "Venue2", "VenueUrl", 10.0, VENUE_FEATURED_PHOTOS)
        );

        mainFragment = new MainFragment();
        view = mainFragment;
        SupportFragmentTestUtil.startVisibleFragment(mainFragment);
    }


    @Test
    public void testSetLoadingIndicator_visible() throws Exception {
        view.setLoadingIndicator(true);

        ProgressBar progressBar = (ProgressBar) mainFragment.getActivity().findViewById(R.id.progressbar);
        Assert.assertEquals(View.VISIBLE, progressBar.getVisibility());
    }

    @Test
    public void testSetLoadingIndicator_invisible() throws Exception {
        view.setLoadingIndicator(false);

        ProgressBar progressBar = (ProgressBar) mainFragment.getActivity().findViewById(R.id.progressbar);
        Assert.assertEquals(View.GONE, progressBar.getVisibility());
    }

    @Test
    public void testShowVenues() throws Exception {
        view.showVenues(VENUES);

//        Mockito.verify(venuesAdapter).setVenues(VENUES);
//        Mockito.verify(venuesAdapter).notifyDataSetChanged();
    }

    @Test
    public void testShowVenueDetails() throws Exception {
        view.showVenueDetails(VENUES.get(0));

        ShadowActivity shadowActivity = Shadows.shadowOf(mainFragment.getActivity());
        Intent intent = shadowActivity.peekNextStartedActivity();
        Assert.assertEquals(new ComponentName(mainFragment.getActivity(), VenueDetailsActivity.class), intent.getComponent());
    }
}
