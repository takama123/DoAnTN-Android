package com.example.computer.doantn.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Computer on 5/1/2017.
 */

public class GameLevelBean implements Parcelable {
    private int idLevel;
    private String titleLevel;
    private String maxlength;
    private String isRemember;
    private String typeMath;

    public GameLevelBean() {
    }

    public GameLevelBean(int level, String titleLevel, String maxlength, String isRemember, String typeMath) {
        this.idLevel = level;
        this.titleLevel = titleLevel;
        this.maxlength = maxlength;
        this.isRemember = isRemember;
        this.typeMath = typeMath;
    }

    protected GameLevelBean(Parcel in) {
        idLevel = in.readInt();
        titleLevel = in.readString();
        maxlength = in.readString();
        isRemember = in.readString();
        typeMath = in.readString();
    }

    public static final Creator<GameLevelBean> CREATOR = new Creator<GameLevelBean>() {
        @Override
        public GameLevelBean createFromParcel(Parcel in) {
            return new GameLevelBean(in);
        }

        @Override
        public GameLevelBean[] newArray(int size) {
            return new GameLevelBean[size];
        }
    };

    public int getLevel() {
        return idLevel;
    }

    public void setLevel(int level) {
        this.idLevel = level;
    }

    public String getTitleLevel() {
        return titleLevel;
    }

    public void setTitleLevel(String titleLevel) {
        this.titleLevel = titleLevel;
    }

    public String getMaxlength() {
        return maxlength;
    }

    public void setMaxlength(String maxlength) {
        this.maxlength = maxlength;
    }

    public String getIsRemember() {
        return isRemember;
    }

    public void setIsRemember(String isRemember) {
        this.isRemember = isRemember;
    }

    public String getTypeMath() {
        return typeMath;
    }

    public void setTypeMath(String typeMath) {
        this.typeMath = typeMath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idLevel);
        dest.writeString(titleLevel);
        dest.writeString(maxlength);
        dest.writeString(isRemember);
        dest.writeString(typeMath);
    }
}