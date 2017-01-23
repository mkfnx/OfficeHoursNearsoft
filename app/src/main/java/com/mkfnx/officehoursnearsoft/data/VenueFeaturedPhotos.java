package com.mkfnx.officehoursnearsoft.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.mkfnx.officehoursnearsoft.home.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenueFeaturedPhotos implements Parcelable {
    int count;
    List<VenuePhoto> items;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<VenuePhoto> getItems() {
        return items;
    }

    public void setItems(List<VenuePhoto> items) {
        this.items = items;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(count);
        parcel.writeList(items);
    }

    private VenueFeaturedPhotos(Parcel in) {
        count = in.readInt();
        items = new ArrayList<VenuePhoto>();
        in.readList(items, VenuePhoto.class.getClassLoader());
    }

    public static final Parcelable.Creator<VenueFeaturedPhotos> CREATOR
            = new Parcelable.Creator<VenueFeaturedPhotos>() {
        public VenueFeaturedPhotos createFromParcel(Parcel in) {
            return new VenueFeaturedPhotos(in);
        }

        public VenueFeaturedPhotos[] newArray(int size) {
            return new VenueFeaturedPhotos[size];
        }
    };
}
