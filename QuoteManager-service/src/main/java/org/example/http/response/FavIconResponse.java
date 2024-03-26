package org.example.http.response;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FavIconResponse extends Response {
    private String iconPath;

    public FavIconResponse(String iconPath) {
        this.iconPath = iconPath;
    }

    @Override
    public String createResponseString() {
        String response = "HTTP/1.1 200 OK\r\n";
        response += "Content-Type: image/x-icon\r\n\r\n";
        response += fetchFavIcon(); // Add the favicon binary data
        return response;
    }

    private byte[] fetchFavIcon() {
        try {
            Path path = Paths.get(iconPath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    //moze se ovo dolje isto koristiti ali nema potrebe mogu i fajl da citam

//    private byte[] getDefaultFaviconData() {
//        String defaultIconData = "iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAi0lEQVR42mL4z4AdyKf4B+kj+P9f9/"+
//                "dLRjEx/9X8SbwXKm5w/CXEVYjmPUxnBbID0AgXUG8uCFcRYhoYjIyMDCPQcIBuAKSBN7C0Q0FQgw"+
//                "F/xeBYgAwJiB+gAyGoaVAFsFNg+glgPvMNAzsE2BRMFQKwo0A9cP8r2QTuVA+TBUgjcgAdyDUKbg"+
//                "OB2aLwDKI4B8AWgH5qBC4KMiKIJv4C8QC8CgE4E2gmCjqB6AXUIaB1CM5igLYE+IHkGI8B+vXgEX8"+
//                "QToD7P6OojnqIXgAAAABJRU5ErkJggg==";
//
//        return java.util.Base64.getDecoder().decode(defaultIconData);
//    }
}
