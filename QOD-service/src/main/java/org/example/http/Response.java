package org.example.http;

public class Response {
    private final String json;

    public Response(String json) {
        this.json = json;
    }

    public String respond(){
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/json\r\n\r\n";
        response += json;
        return response;
    }

}
