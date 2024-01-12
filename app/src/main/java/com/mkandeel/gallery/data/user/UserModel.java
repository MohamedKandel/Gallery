package com.mkandeel.gallery.data.user;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    private int id;
    private String name;
    private String username;
    private AddressObject address;

    public UserModel(int id, String name, String username, AddressObject address) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public AddressObject getAddress() {
        return address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.username);
        dest.writeParcelable(this.address, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.username = source.readString();
        this.address = source.readParcelable(AddressObject.class.getClassLoader());
    }

    protected UserModel(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.username = in.readString();
        this.address = in.readParcelable(AddressObject.class.getClassLoader());
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
