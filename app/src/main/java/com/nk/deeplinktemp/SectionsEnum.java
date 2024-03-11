package com.nk.deeplinktemp;

/**
 * @Author: naftalikomarovski
 * @Date: 2024/03/11
 */
public enum SectionsEnum {
    POST("post"),
    USER("user");

    private String sectionString;

    SectionsEnum(String sectionString) {
        this.sectionString = sectionString;
    }

    public String getSectionString() {
        return sectionString;
    }
}
