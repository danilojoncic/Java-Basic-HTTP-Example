package org.example.http.response;

public class HTMLResponse extends Response {
    private String htmlPage;

    public HTMLResponse(String htmlPage) {
        this.htmlPage = htmlPage;
    }

    @Override
    public String createResponseString() {
        String response = "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n";
        response += htmlPage;

        return response;
    }
}
