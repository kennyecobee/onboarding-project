package com.example.kennyr.onboardingproject.tasks;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Task implements Parcelable {

    public String title;
    public String description;
    public Date date;

    public Task(String title, String description, Date date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeSerializable(date);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel parcel) {
            return new Task(parcel);
        }

        @Override
        public Task[] newArray(int i) {
            return new Task[i];
        }
    };

    private Task(Parcel in) {
        title = in.readString();
        description = in.readString();
        date = (Date) in.readSerializable();
    }
}
