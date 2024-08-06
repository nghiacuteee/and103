package com.cam.a3_assignment.model;

public class Response <DATA> {
    private int status;
    private String message;
    private DATA data;

    public Response(int status, String message, DATA data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public DATA getData() {
        return data;
    }
}
