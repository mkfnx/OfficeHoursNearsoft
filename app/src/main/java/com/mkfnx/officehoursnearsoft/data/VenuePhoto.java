package com.mkfnx.officehoursnearsoft.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mkfnx on 13/01/17.
 */

public class VenuePhoto implements Parcelable {
    String id;
    String prefix;
    String suffix;
    int width;
    int height;

    public VenuePhoto(String id, String prefix, String suffix, int width, int height) {
        this.id = id;
        this.prefix = prefix;
        this.suffix = suffix;
        this.width = width;
        this.height = height;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(prefix);
        parcel.writeString(suffix);
        parcel.writeInt(width);
        parcel.writeInt(height);
    }

    private VenuePhoto(Parcel in) {
        id = in.readString();
        prefix = in.readString();
        suffix = in.readString();
        width = in.readInt();
        height = in.readInt();
    }

    public static final Parcelable.Creator<VenuePhoto> CREATOR
            = new Parcelable.Creator<VenuePhoto>() {
        public VenuePhoto createFromParcel(Parcel in) {
            return new VenuePhoto(in);
        }

        public VenuePhoto[] newArray(int size) {
            return new VenuePhoto[size];
        }
    };
}
