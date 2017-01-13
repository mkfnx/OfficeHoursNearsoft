package com.mkfnx.officehoursnearsoft.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by mkfnx on 13/01/17.
 */

public interface FoursquareService {
    public static final String CLIENT_ID = "KAJ5OHN2NHGNNIPMLEGHRROZXZH2YIPT2TTGONJI4OYVFYH1";
    public static final String CLIENT_SECRET = "PSHYTCSGFUHZUG24PH4F0DHM5FD04VEG5PDLCHT0NP4EZI31";

    @GET("venues/explore?v=20161016&venuePhotos=1&client_id=" + CLIENT_ID +  "&client_secret=" + CLIENT_SECRET)
    Call<ExploreVenuesResponse> exploreVenuesNearLocation(@Query("near") String nearLocation);
}
