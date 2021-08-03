package com.sevak.thread;

import com.sevak.decorator.CreateHttpUtil;

import java.io.*;
import java.net.Socket;

public class SendRequest extends Thread {
    private String fileFormat;

    public SendRequest(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("127.0.0.1", 8080);
             //writer@ grelu hamara uxarkuma info
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
             //reader kardalu hamara stanum e info
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream())
             )) {
            System.out.println("connecting to server"+Thread.currentThread().getName());
            writer.write(CreateHttpUtil.createHttp(fileFormat));
            writer.newLine();
            writer.flush();

            String response = reader.readLine();
            System.out.println("Response -> " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
