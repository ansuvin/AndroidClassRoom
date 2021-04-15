package com.example.adminapp;

import java.util.ArrayList;

public class BreakData {
    ArrayList<String> breakTitle = new ArrayList<>();
    ArrayList<String> breakContents = new ArrayList<>();
    public BreakData(){

    }
    public BreakData(ArrayList<String> breakTitle, ArrayList<String> breakContents){
        this.breakTitle = breakTitle;
        this.breakContents = breakContents;
    }
    public ArrayList<String> getBreakTitle(){
        return breakTitle;
    }
    public ArrayList<String> getBreakContents(){
        return breakContents;
    }
}
