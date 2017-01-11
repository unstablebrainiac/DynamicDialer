package com.tobedecided.dynamicdialer;


import java.util.Date;

/**
 * Created by sajalnarang on 2/12/16.
 */

public class Log {
    private String name;
    private String number;
    private String duration;
    private Date dayTime;
    private String type;

    public Log() {
    }

    public Log(String name, String number, String duration, Date dayTime, String type) {
        this.name = name;
        this.number = number;
        this.duration = duration;
        this.dayTime = dayTime;
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

    public Date getDayTime() {
        return dayTime;
    }

    public void setDayTime(Date dayTime) {
        this.dayTime = dayTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}