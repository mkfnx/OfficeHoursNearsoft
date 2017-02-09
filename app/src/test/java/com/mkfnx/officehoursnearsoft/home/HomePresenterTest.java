package com.mkfnx.officehoursnearsoft.home;

import com.google.common.collect.Lists;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.VenueFeaturedPhotos;
import com.mkfnx.officehoursnearsoft.data.VenuePhoto;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;
import com.mkfnx.officehoursnearsoft.util.BaseScheduler;
import com.mkfnx.officehoursnearsoft.util.ImmediateScheduler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by mkfnx on 08/02/17.
 */

public class HomePresenterTest {

    @Mock
    private HomeContract.View view;
    @Mock
    private VenuesRepository venuesRepository;

    private CompositeDisposable compositeDisposable;

    private BaseScheduler scheduler;

    private HomePresenter presenter;

    private List<VenuePhoto> VENUE_PHOTOS;
    private VenueFeaturedPhotos VENUE_FEATURED_PHOTOS;
    private List<Venue> VENUES;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        VENUE_PHOTOS = Lists.newArrayList(
                new VenuePhoto("VenuePhotoId1", "VenuePhotoPref", "VenuePhotoSuff", 100, 100)
        );
        VENUE_FEATURED_PHOTOS = new VenueFeaturedPhotos(1, VENUE_PHOTOS);
        VENUES = Lists.newArrayList(
                new Venue("VenueId1", "Venue1", "VenueUrl", 10.0, VENUE_FEATURED_PHOTOS),
                new Venue("VenueId2", "Venue2", "VenueUrl", 10.0, VENUE_FEATURED_PHOTOS)
        );

        compositeDisposable = new CompositeDisposable();

        scheduler = new ImmediateScheduler();

        presenter = new HomePresenter(venuesRepository, view, compositeDisposable, scheduler);
    }

    @Test
    public void testStart() throws Exception {
        Mockito.when(venuesRepository.getVenues()).thenReturn(Single.just(VENUES));
        presenter.start();
        Assert.assertNotNull(presenter.getView());
    }

    @Test
    public void testGetView() throws Exception {
        Assert.assertEquals(view, presenter.getView());
    }

    @Test
    public void testLoadVenues() throws Exception {
        Mockito.when(venuesRepository.getVenues()).thenReturn(Single.just(VENUES));
        presenter.loadVenues();

        Assert.assertNotNull(presenter.getView());
        Assert.assertNotNull(venuesRepository.getVenues());
        Mockito.verify(view).setLoadingIndicator(true);
        Mockito.verify(view).showVenues(Mockito.anyList());
        Mockito.verify(view).setLoadingIndicator(false);
    }

    @Test
    public void testLoadVenues_onError() throws Exception {
        Mockito.when(venuesRepository.getVenues()).thenReturn(Single.<List<Venue>>error(new Exception()));
        presenter.loadVenues();

        Assert.assertNotNull(presenter.getView());
        Mockito.verify(view).setLoadingIndicator(false);
        Mockito.verify(view).showLoadingVenuesError();
    }

    @Test
    public void testOpenVenueDetails() throws Exception {
        Venue venue = VENUES.get(0);

        presenter.openVenueDetails(venue);

        Mockito.verify(view).showVenueDetails(Mockito.any(Venue.class));
    }

    @Test
    public void testStop() throws Exception {
        presenter.stop();

        Assert.assertNull(presenter.getView());
    }
}
