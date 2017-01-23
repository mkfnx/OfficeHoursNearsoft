package com.mkfnx.officehoursnearsoft.data;

import com.mkfnx.officehoursnearsoft.home.MainActivity;

/**
 * Created by mkfnx on 13/01/17.
 */

public class ExploreVenuesResponse {
    FoursquareResponseMeta meta;
    ExploreVenuesResp response;

    public FoursquareResponseMeta getMeta() {
        return meta;
    }

    public void setMeta(FoursquareResponseMeta meta) {
        this.meta = meta;
    }

    public ExploreVenuesResp getResponse() {
        return response;
    }

    public void setResponse(ExploreVenuesResp response) {
        this.response = response;
    }
}
