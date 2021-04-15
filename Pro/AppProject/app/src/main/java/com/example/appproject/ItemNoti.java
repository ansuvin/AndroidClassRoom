package com.example.appproject;

import java.util.ArrayList;

public class ItemNoti {
    private ArrayList<String> titleStr;
    private ArrayList<String> contentStr;

    public ItemNoti(ArrayList<String> titleStr, ArrayList<String> contentStr){
        this.titleStr = titleStr;
        this.contentStr = contentStr;
    }

    public ItemNoti(){

    }

    public ArrayList<String> getTitleStr() {
        return titleStr;
    }

    public ArrayList<String> getContentStr() {
        return contentStr;
    }
}
