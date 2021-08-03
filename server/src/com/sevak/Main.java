package com.sevak;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Server is running and listen port 8080");

            while (true) {
                Socket socket = serverSocket.accept();
                Handler requestHandler = new Handler(socket);
                requestHandler.start();
            }

        } catch (IOException e) {
            System.err.println("Something get wrong");
            e.printStackTrace();

        }
    }
}
