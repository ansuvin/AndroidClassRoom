package com.example.appproject;

import java.util.ArrayList;

public class Stay {
    ArrayList<String> stayList = new ArrayList<>();
    ArrayList<String> stayID = new ArrayList<>();

    public Stay(){

    }
    public Stay(ArrayList<String> stayList, ArrayList<String> stayID){
        this.stayList = stayList;
        this.stayID = stayID;
    }

    public ArrayList<String> getStayList(){
        return stayList;
    }

    public ArrayList<String> getStayID(){
        return stayID;
    }
}
