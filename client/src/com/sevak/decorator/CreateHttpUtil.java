package com.sevak.decorator;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class CreateHttpUtil {

    private static final Map<String, String> CONTENT_TYPES = new HashMap<>() {{
        put("jpg", "image/jpeg");
        put("html", "text/html");
        put("json", "application/json");
        put("txt", "text/plain");
        put("", "text/plain");
    }};

    private static String createHeader(String fileName, int statusCode, String statusText, String type, long length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" HTTP/1.1 ").append(statusCode).append(" ").append(statusText);
        stringBuilder.append(" Content-Type: ").append(type);
        stringBuilder.append(" Content-Length: ").append(length);

        return stringBuilder.toString();
    }
    public static String createHttp(String fileName){
        String type = CONTENT_TYPES.get(fileName.substring(fileName.lastIndexOf(".")+1));
        return "GET/"+ fileName+createHeader(fileName,200, "OK", type, 0);

    }
}
