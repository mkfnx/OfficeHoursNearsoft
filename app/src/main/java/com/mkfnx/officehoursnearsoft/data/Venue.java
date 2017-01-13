package com.mkfnx.officehoursnearsoft.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.mkfnx.officehoursnearsoft.home.MainActivity;

/**
 * Created by mkfnx on 13/01/17.
 */

public class Venue implements Parcelable {
    String id;
    String name;
    String url;
    double rating;
    VenueFeaturedPhotos featuredPhotos;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public VenueFeaturedPhotos getFeaturedPhotos() {
        return featuredPhotos;
    }

    public void setFeaturedPhotos(VenueFeaturedPhotos featuredPhotos) {
        this.featuredPhotos = featuredPhotos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeDouble(rating);
        parcel.writeParcelable(featuredPhotos, i);
    }

    private Venue(Parcel in) {
        id = in.readString();
        name = in.readString();
        url = in.readString();
        rating = in.readDouble();
        featuredPhotos = in.readParcelable(VenueFeaturedPhotos.class.getClassLoader());
    }

    public static final Parcelable.Creator<Venue> CREATOR
            = new Parcelable.Creator<Venue>() {
        public Venue createFromParcel(Parcel in) {
            return new Venue(in);
        }

        public Venue[] newArray(int size) {
            return new Venue[size];
        }
    };
}
