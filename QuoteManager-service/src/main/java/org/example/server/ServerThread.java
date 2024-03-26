package org.example.server;
import org.example.content.Keeper;
import org.example.controller.RequestHandler;
import org.example.http.Method;
import org.example.http.Request;
import org.example.http.response.Response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.management.MemoryType;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private int i;
    public ServerThread(Socket sock) {
        this.client = sock;

        try {
            //inicijalizacija ulaznog toka
            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));

            //inicijalizacija izlaznog sistema
            out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    client.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String requestLine = in.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);

            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            System.out.println("\nHTTP REQUEST:\n");
            do {
                System.out.println(requestLine);
                requestLine = in.readLine();
                if(requestLine.contains("Content-Length:")){
                    String temp = requestLine;
                    String niz[] = temp.split(" ");
                    i = Integer.parseInt(niz[1]);
                }
            } while (!requestLine.trim().equals(""));

            if (method.equals(Method.POST.toString())) {
                char[] buffer = new char[i];
                in.read(buffer);
                String ans = new String(buffer);
                System.out.println(ans);
                Keeper.postAddition(ans);
            }

            Request request = new Request(Method.valueOf(method), path);

            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            System.out.println("\nHTTP RESPONSE:\n");
            System.out.println(response.createResponseString());

            out.println(response.createResponseString());

            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
