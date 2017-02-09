package com.mkfnx.officehoursnearsoft;

import com.google.common.collect.Lists;
import com.mkfnx.officehoursnearsoft.data.Venue;
import com.mkfnx.officehoursnearsoft.data.VenueFeaturedPhotos;
import com.mkfnx.officehoursnearsoft.data.VenuePhoto;
import com.mkfnx.officehoursnearsoft.data.source.VenuesRepository;
import com.mkfnx.officehoursnearsoft.data.source.remote.VenuesRemoteDataSource;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by mkfnx on 08/02/17.
 */

public class VenuesRepositoryTest {

    @Mock
    private VenuesRemoteDataSource venuesRemoteDataSource;

    private VenuesRepository venuesRepository;

    private List<VenuePhoto> VENUE_PHOTOS;
    private VenueFeaturedPhotos VENUE_FEATURED_PHOTOS;
    private List<Venue> VENUES;

    @Before
    public void setup() throws Exception {

        MockitoAnnotations.initMocks(this);

        venuesRepository = new VenuesRepository(venuesRemoteDataSource);

        VENUE_PHOTOS = Lists.newArrayList(
                new VenuePhoto("VenuePhotoId1", "VenuePhotoPref", "VenuePhotoSuff", 100, 100)
        );
        VENUE_FEATURED_PHOTOS = new VenueFeaturedPhotos(1, VENUE_PHOTOS);
        VENUES = Lists.newArrayList(
                new Venue("VenueId1", "Venue1", "VenueUrl", 10.0, VENUE_FEATURED_PHOTOS),
                new Venue("VenueId2", "Venue2", "VenueUrl", 10.0, VENUE_FEATURED_PHOTOS)
        );
    }

    @Test
    public void testGetVenues() throws Exception {
        Single<List<Venue>> listSingle = Single.just(VENUES);
        Mockito.when(venuesRemoteDataSource.getVenues()).thenReturn(listSingle);

        Assert.assertEquals(listSingle, venuesRepository.getVenues());

        Mockito.verify(venuesRemoteDataSource).getVenues();
    }
}
