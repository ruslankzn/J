package com.javarush.task.task30.task3008;

import java.io.Serializable;

//1
public class Message implements Serializable {
    //2
    private final MessageType type;

    //3
    private final String data;

    //4
    public MessageType getType() {
        return type;
    }
    public String getData() {
        return data;
    }

    //5
    public Message(MessageType type) {
        this.type = type;
        data = null;
    }

    //6
    public Message(MessageType type, String data) {
        this.type = type;
        this.data = data;
    }
    
}
