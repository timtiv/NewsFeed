package com.tim.newsfeed.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class Condition implements Parcelable {
    String title;
    boolean isSelected = false;

    public Condition(String title) {
        this.title = title;
    }

    public Condition() {
    }

    protected Condition(Parcel in) {
        title = in.readString();
        isSelected = in.readByte() != 0;
    }

    public static final Creator<Condition> CREATOR = new Creator<Condition>() {
        @Override
        public Condition createFromParcel(Parcel in) {
            return new Condition(in);
        }

        @Override
        public Condition[] newArray(int size) {
            return new Condition[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition category = (Condition) o;
        return Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeByte((byte) (isSelected ? 1 : 0));
    }
}
