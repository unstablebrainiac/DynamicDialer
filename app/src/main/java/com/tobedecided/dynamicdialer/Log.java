package com.tobedecided.dynamicdialer;

import android.location.Location;

/**
 * Created by sajalnarang on 2/12/16.
 */

public class Log {
    private String name;
    private String number;
    private String duration;
    private String location;
    private String type;

    public Log() {
    }

    public Log(String name, String number, String duration, String location, String type) {
        this.name = name;
        this.number = number;
        this.duration = duration;
        this.location = location;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}