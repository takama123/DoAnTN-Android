package com.example.computer.doantn.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Computer on 5/1/2017.
 */

public class GameLevelBean implements Parcelable{
    private String lop;
    private int level;
    private String level_name;

    public GameLevelBean() {
    }

    public GameLevelBean(String lop, int level, String level_name) {
        this.lop = lop;
        this.level = level;
        this.level_name = level_name;
    }

    protected GameLevelBean(Parcel in) {
        lop = in.readString();
        level = in.readInt();
        level_name = in.readString();
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

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLevel_name() {
        return level_name;
    }

    public void setLevel_name(String level_name) {
        this.level_name = level_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(lop);
        dest.writeInt(level);
        dest.writeString(level_name);
    }
}
