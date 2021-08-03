package com.sevak;

import com.sevak.decorator.CreateHttpUtil;
import com.sevak.thread.SendRequest;

import java.io.*;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 5; i++) {
            new SendRequest("picture.jpg").start();
            new SendRequest("text.txt").start();
            new SendRequest("empty").start();
        }
    }
}
