package org.example.server;

import org.example.controller.RequestHandler;
import org.example.http.Method;
import org.example.http.Request;
import org.example.http.Response;

import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerThread implements Runnable {
    private final Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true)
        ) {
            String requestLine = in.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(requestLine);
            String method = stringTokenizer.nextToken();
            String path = stringTokenizer.nextToken();

            System.out.println("\nHTTP REQUEST:\n");
            System.out.println(requestLine);
            while (in.ready()) {
                System.out.println(in.readLine());
            }

            Request request = new Request(Method.valueOf(method), path);
            RequestHandler requestHandler = new RequestHandler();
            Response response = requestHandler.handle(request);

            System.out.println("\nHTTP RESPONSE:\n");
            System.out.println(response.respond());

            out.println(response.respond());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                System.out.println("SOCKET HAS BEEN CLOSED");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
