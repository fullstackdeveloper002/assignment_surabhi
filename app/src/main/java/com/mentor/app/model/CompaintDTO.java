package com.mentor.app.model;

import java.io.Serializable;

public class CompaintDTO implements Serializable {

    String id, message,reply,message_status,status,created_at,updated_at;

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getReply() {
        return reply;
    }

    public String getMessage_status() {
        return message_status;
    }

    public String getStatus() {
        return status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
