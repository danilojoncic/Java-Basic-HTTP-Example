package org.example.controller;

import org.example.http.Method;
import org.example.http.Request;
import org.example.http.Response;

public class RequestHandler {
    public Response handle(Request request){
        if(request.getContent().equals("/day") && request.getMethod().equals(Method.GET)){
            return (new QODController(request).get());
        }else{
            throw new RuntimeException("Request has not been handled correctly!");
        }
    }
}
