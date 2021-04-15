package com.example.appproject;

import java.util.ArrayList;

public class Laptop {

    ArrayList<String> laptopList = new ArrayList<>();
    ArrayList<String> laptopID = new ArrayList<>();

    public Laptop(){

    }

    public  Laptop(ArrayList<String> laptopList, ArrayList<String> laptopID){
        this.laptopList = laptopList;
        this.laptopID = laptopID;
    }

    public ArrayList<String> getLaptopList(){
        return laptopList;
    }
    public ArrayList<String> getLaptopID(){
        return laptopID;
    }

}
