package com.example.blindwalls21.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Mural implements Parcelable {

    private int id;
    private String title;
    private String artist;
    private String description;
    private String[] imageURL;
    private int year;
    private String photographer;
    private String languageCode;

    public Mural(int id, String title, String artist, String description, String[] imageURL, int year, String photographer, String languageCode) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.description = description;
        this.imageURL = imageURL;
        this.year = year;
        this.photographer = photographer;
        this.languageCode = languageCode;
    }

    private Mural(Parcel in) {
        id = in.readInt();
        title = in.readString();
        artist = in.readString();
        description = in.readString();
        imageURL = in.createStringArray();
        year = in.readInt();
        photographer = in.readString();
        languageCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(artist);
        dest.writeString(description);
        dest.writeStringArray(imageURL);
        dest.writeInt(year);
        dest.writeString(photographer);
        dest.writeString(languageCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Mural> CREATOR = new Creator<Mural>() {
        @Override
        public Mural createFromParcel(Parcel in) {
            return new Mural(in);
        }

        @Override
        public Mural[] newArray(int size) {
            return new Mural[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public String[] getImageURL() {
        return imageURL;
    }

    public int getYear() {
        return year;
    }

    public String getPhotographer() {
        return photographer;
    }

    public String getLanguageCode() {
        return languageCode;
    }
}