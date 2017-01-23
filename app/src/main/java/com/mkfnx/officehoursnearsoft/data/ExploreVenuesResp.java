package com.mkfnx.officehoursnearsoft.data;

import java.util.List;

/**
 * Created by mkfnx on 13/01/17.
 */

public class ExploreVenuesResp {
    String headerLocation;
    List<Group> groups;

    public String getHeaderLocation() {
        return headerLocation;
    }

    public void setHeaderLocation(String headerLocation) {
        this.headerLocation = headerLocation;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
