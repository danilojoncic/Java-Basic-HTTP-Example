package org.example.http;

import com.google.gson.Gson;
import org.example.content.Keeper;
import org.example.content.Quote;

import java.io.*;
import java.net.Socket;

public class Fetcher {
    public static void fetchQuoteOfTheDay(){

        try {
            Socket socket = new  Socket("localhost",9000);
            BufferedReader in = new BufferedReader(new InputStreamReader((socket).getInputStream()));
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            String httpZahtev = "GET /day HTTP/1.1\n" +
                    "Host: localhost:9000\n";

            String requestLine;
            String qod;
            out.println(httpZahtev);
            requestLine = in.readLine();
            do {
                System.out.println(requestLine);
                requestLine = in.readLine();
            } while (!requestLine.trim().equals(""));

            qod = in.readLine();
            System.out.println(qod);
            Gson gson = new Gson();
            Keeper.quoteOfTheDay  = gson.fromJson(qod, Quote.class);
        }catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
