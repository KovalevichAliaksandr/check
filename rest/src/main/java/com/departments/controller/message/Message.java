package com.departments.controller.message;

/**
 * Created by alex on 18.2.17.
 */
public class Message {

    private String type;
    private String message;

    public Message(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
