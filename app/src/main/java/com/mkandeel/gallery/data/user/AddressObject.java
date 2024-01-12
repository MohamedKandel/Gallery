package com.mkandeel.gallery.data.user;

import android.os.Parcel;
import android.os.Parcelable;

public class AddressObject implements Parcelable {
    private String street;
    private String suite;
    private String city;

    public AddressObject(String street, String suite, String city) {
        this.street = street;
        this.suite = suite;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getSuite() {
        return suite;
    }

    public String getCity() {
        return city;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.street);
        dest.writeString(this.suite);
        dest.writeString(this.city);
    }

    public void readFromParcel(Parcel source) {
        this.street = source.readString();
        this.suite = source.readString();
        this.city = source.readString();
    }

    protected AddressObject(Parcel in) {
        this.street = in.readString();
        this.suite = in.readString();
        this.city = in.readString();
    }

    public static final Parcelable.Creator<AddressObject> CREATOR = new Parcelable.Creator<AddressObject>() {
        @Override
        public AddressObject createFromParcel(Parcel source) {
            return new AddressObject(source);
        }

        @Override
        public AddressObject[] newArray(int size) {
            return new AddressObject[size];
        }
    };
}
