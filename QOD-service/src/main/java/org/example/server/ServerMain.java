package org.example.server;

import org.example.contents.Keeper;
import org.example.contents.Quote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        initQod();
        try {
            ServerSocket serverSocket = new ServerSocket(Keeper.port);
            while(true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerThread(socket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    private static void initQod(){
        Keeper.quotesOfTheDay.add(new Quote("Romano Obilinovic","Da je uletio jos jedan par, zatvorili bi i seike amigo!"));
        Keeper.quotesOfTheDay.add(new Quote("Romano Obilinovic","Pun sam ko brod, a brod je u kanalu"));
        Keeper.quotesOfTheDay.add(new Quote("Romano Obilinovic","Bice paparutke amigo, osjecam jednu najezdu komaraca, tigrastih africkih"));
        Keeper.quotesOfTheDay.add(new Quote("Rade Boganovic","Sta ocekujes kada stavis 3 crnca u odbrani, nista sem da primis toliko golova"));
        Keeper.quotesOfTheDay.add(new Quote("Rade Bogdanovic","Ja sam samo jedan obican momak iz istocnog Sarajeva"));
        Keeper.quotesOfTheDay.add(new Quote("Dragan 'Piksi' Stojkovic","Mi mozda jesmo izgubili u rezultatskom smislu 4:0, ali mi nismo porazeni"));
        Keeper.quotesOfTheDay.add(new Quote("Dragan 'Piksi' Stojkovic","Who is Haaland?"));
        Keeper.quotesOfTheDay.add(new Quote("Dusan Tadic","Ajmo momci, jedan gol, paf drugi gol, ovo je nas teren, necemo bugarima dati da nas pobede na nasem terenu"));
    }
}
