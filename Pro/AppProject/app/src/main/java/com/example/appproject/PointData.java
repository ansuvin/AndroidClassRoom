package com.example.appproject;

import java.util.ArrayList;

public class PointData {
    ArrayList<String> reason;
    ArrayList<Integer> point;

    public PointData(){

    }
    public PointData(ArrayList<String> reason, ArrayList<Integer> point){
        this.reason = reason;
        this.point = point;
    }

    public ArrayList<String> getReason(){
        return reason;
    }
    public ArrayList<Integer> getPoint(){
        return point;
    }

}
