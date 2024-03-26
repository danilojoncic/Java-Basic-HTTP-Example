package org.example.server;

import org.example.content.Keeper;
import org.example.content.Quote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        Keeper.inMemoryQuotes.add(new Quote("Narodna izreka","Put od hiljadu milja pocinje jednim korakom."));
        try {
            ServerSocket ss = new ServerSocket(Keeper.port);
            while(true){
                Socket socket = ss.accept();
                new Thread(new ServerThread(socket)).start();
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }
}
