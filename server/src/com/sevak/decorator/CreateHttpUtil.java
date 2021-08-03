package com.sevak.decorator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CreateHttpUtil {
    private static final String fileUrl ="C:\\PicsArt\\Homework\\http_client_server\\server\\files\\";
    private static final Map<String, String> CONTENT_TYPES = new HashMap<>() {{
        put("jpg", "image/jpeg");
        put("html", "text/html");
        put("json", "application/json");
        put("txt", "text/plain");
        put("", "text/plain");
    }};

    private static String sendHeader(int statusCode, String statusText, String type, long length) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("HTTP/1.1 ").append(statusCode).append(" ").append(statusText);
        stringBuilder.append(" Content-Type: ").append(type);
        stringBuilder.append(" Content-Length: ").append(length);

        return stringBuilder.toString();
    }
    public static String createHttp(String fileName){
        String substring = fileName.substring(fileName.lastIndexOf(".") + 1);
        String type = CONTENT_TYPES.get(fileName.substring(fileName.lastIndexOf(".")+1).trim());
        var filePath = Path.of(fileUrl+fileName.trim());
        if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
            File file = new File(filePath.toString());
            return sendHeader(200, "OK", type, file.length());

            } else {
                return sendHeader( 404, "Not Found", type, 0);
            }

    }

}
