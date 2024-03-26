package org.example.controller;

import org.example.http.Method;
import org.example.http.Request;
import org.example.http.response.HTMLResponse;
import org.example.http.response.Response;

public class RequestHandler {
    public Response handle(Request request)throws Exception{
        if(request.getContent().equals("/quotes") && request.getMethod().equals(Method.GET)){
            return (new HttpController(request)).get();
        }else if(request.getContent().equals("/save") && request.getMethod().equals(Method.POST)){
            System.out.println("POST REQUEST");
            return (new HttpController(request)).post();
        }else if(request.getContent().equals("/favicon.ico") && request.getMethod().equals(Method.GET)){
            return (new HttpController(request)).getFavIcon();
        }else{
            throw new RuntimeException("Request was not handled!");
        }
    }
}
