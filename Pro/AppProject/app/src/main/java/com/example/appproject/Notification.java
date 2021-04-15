package com.example.appproject;

public class Notification {
    private String titleStr;
    private String contentStr;

    public Notification(){

    }

    public Notification(String titleStr, String contentStr){
        this.titleStr = titleStr;
        this.contentStr = contentStr;
    }

    public String getTitleStr() {
        return titleStr;
    }

    public String getContentStr() {
        return contentStr;
    }
}
