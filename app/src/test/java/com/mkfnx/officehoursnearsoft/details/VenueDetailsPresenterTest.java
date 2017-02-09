package com.mkfnx.officehoursnearsoft.details;

import com.mkfnx.officehoursnearsoft.data.Venue;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * Created by mkfnx on 09/02/17.
 */
public class VenueDetailsPresenterTest {

    @Mock
    private Venue venue;
    @Mock
    private DetailsContract.View view;

    private VenueDetailsPresenter venueDetailsPresenter;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
        venueDetailsPresenter = new VenueDetailsPresenter(venue, view);
    }

    @Test
    public void testStart() throws Exception {
        venueDetailsPresenter.start();

        Assert.assertNotNull(venueDetailsPresenter.getView());
    }

    @Test
    public void testLoadVenueDetails() throws Exception {
        venueDetailsPresenter.loadVenueDetails();

        Mockito.verify(view).showVenueDetails(venue);
    }

    @Test
    public void testStop() throws Exception {
        venueDetailsPresenter.stop();

        Assert.assertNull(venueDetailsPresenter.getView());
    }
}