package com.example.computer.doantn.bean;

/**
 * Created by Computer on 5/9/2017.
 */

public class LopHocSettingBean {
    private String idUnlockLevel;
    private String idPlayingLevel;

    public LopHocSettingBean() {
    }

    public LopHocSettingBean(String idUnlockLevel, String idPlayingLevel) {
        this.idUnlockLevel = idUnlockLevel;
        this.idPlayingLevel = idPlayingLevel;
    }

    public String getIdUnlockLevel() {
        return idUnlockLevel;
    }

    public void setIdUnlockLevel(String idUnlockLevel) {
        this.idUnlockLevel = idUnlockLevel;
    }

    public String getIdPlayingLevel() {
        return idPlayingLevel;
    }

    public void setIdPlayingLevel(String idPlayingLevel) {
        this.idPlayingLevel = idPlayingLevel;
    }
}
