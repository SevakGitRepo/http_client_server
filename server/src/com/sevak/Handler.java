package com.sevak;

import com.sevak.decorator.CreateHttpUtil;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;

public class Handler extends Thread {

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream())
             )) {
            Thread.sleep(5000);

            String request = reader.readLine();
            System.out.println("request " + request);
            String fileName = request.substring(4, request.indexOf("HTTP"));

            String response = "Server answer " + CreateHttpUtil.createHttp(fileName);

            writer.write(response);
            writer.newLine();
            writer.flush();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
