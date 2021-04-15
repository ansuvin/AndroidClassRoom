package com.example.pra04.DTO;

public class ChatModel {
    String name;
    String script;
    String profile_image;
    String date_time;

    public String getName() {
        return name;
    }

    public String getScript() {
        return script;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public ChatModel(String name, String script, String profile_image, String date_time) {
        this.name = name;
        this.script = script;
        this.profile_image = profile_image;
        this.date_time = date_time;
    }
}
