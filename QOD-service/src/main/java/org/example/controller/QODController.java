package org.example.controller;

import org.example.contents.Keeper;
import org.example.contents.Quote;
import org.example.http.Request;
import org.example.http.Response;

import java.util.Date;
import java.util.Random;

public class QODController {
    private Request request;

    public QODController(Request request) {
        this.request = request;
    }

    //moze bolje da se namjesti ali zasada neka ostane ovako
    public Response get(){
        Date now = new Date();
        int index = (int)(now.getTime() % Keeper.quotesOfTheDay.size()-1);
        Quote qod = Keeper.quotesOfTheDay.get(index);
        String json = qod.toJson();
        return new Response(json);
    }
}
