package com.tim.newsfeed.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Provider implements Parcelable {
    String title;
    boolean isSelected = false;
    boolean isSubscribed = false;

    public Provider(String title) {
        this.title = title;
    }

    protected Provider(Parcel in) {
        title = in.readString();
        isSelected = in.readByte() != 0;
        isSubscribed = in.readByte() != 0;
    }

    public static final Creator<Provider> CREATOR = new Creator<Provider>() {
        @Override
        public Provider createFromParcel(Parcel in) {
            return new Provider(in);
        }

        @Override
        public Provider[] newArray(int size) {
            return new Provider[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
        parcel.writeByte((byte) (isSubscribed ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(title, provider.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }
}
