package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ParsedLog {
    private String ip;
    private String user;
    private Date date;
    private Event event;
    private Status status;

    public ParsedLog(String log) {
        String[] data=log.split("\t");
        ip=data[0];
        user=data[1];
        try {
            date=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").parse(data[2]);
        } catch(ParseException e) {
            e.printStackTrace();
        }
        event=Event.valueOf(data[3].split( " ")[0]);
        status=Status.valueOf(data[4]);
    }

    public String getIp() {
        return ip;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public Event getEvent() {
        return event;
    }

    public Status getStatus() {
        return status;
    }
}