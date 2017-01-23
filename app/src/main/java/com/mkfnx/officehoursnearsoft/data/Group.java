package com.mkfnx.officehoursnearsoft.data;

import com.mkfnx.officehoursnearsoft.home.MainActivity;

import java.util.List;

/**
 * Created by mkfnx on 13/01/17.
 */

public class Group {
    String type;
    List<ExploreVenuesResponseItem> items;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ExploreVenuesResponseItem> getItems() {
        return items;
    }

    public void setItems(List<ExploreVenuesResponseItem> items) {
        this.items = items;
    }
}
