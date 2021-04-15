package com.example.adminapp;

import java.util.ArrayList;


public class SongData {
    public ArrayList<String> songName;
    public ArrayList<String> songURL;

    public SongData(){

    }

    public SongData(ArrayList<String> songName, ArrayList<String> songURL){
        this.songName = songName;
        this.songURL = songURL;
    }
    public ArrayList<String> getSongName(){
        return songName;
    }
    public ArrayList<String> getSongURL(){
        return songURL;
    }
}
