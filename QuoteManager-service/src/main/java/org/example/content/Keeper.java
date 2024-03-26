package org.example.content;

import java.util.ArrayList;
import java.util.List;

public class Keeper {
    public static final List<Quote> inMemoryQuotes = new ArrayList<>();
    public static final int port = 8000;
    public static final int qod_port = 9000;
    public static Quote quoteOfTheDay;


    public static void postAddition(String arg){
        String sve[] = arg.split("=");
        String sve2[] = sve[1].split("&");
        String author = sve2[0].replace("+"," ");
        String text = sve[2].replace("+", " ");
        text = text.replace("%27", "\'");
        text = text.replace("%2C", ",");
        text = text.replace("%3F", "?");
        Quote quote = new Quote(author, text);
        inMemoryQuotes.add(quote);
    }
}
