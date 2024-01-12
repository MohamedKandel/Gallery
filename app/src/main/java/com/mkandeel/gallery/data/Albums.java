package com.mkandeel.gallery.data;

import android.os.Parcel;
import android.os.Parcelable;

public class Albums implements Parcelable {
    private int userId;
    private int id;
    private String title;

    public Albums(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeInt(this.id);
        dest.writeString(this.title);
    }

    public void readFromParcel(Parcel source) {
        this.userId = source.readInt();
        this.id = source.readInt();
        this.title = source.readString();
    }

    protected Albums(Parcel in) {
        this.userId = in.readInt();
        this.id = in.readInt();
        this.title = in.readString();
    }

    public static final Parcelable.Creator<Albums> CREATOR = new Parcelable.Creator<Albums>() {
        @Override
        public Albums createFromParcel(Parcel source) {
            return new Albums(source);
        }

        @Override
        public Albums[] newArray(int size) {
            return new Albums[size];
        }
    };
}
