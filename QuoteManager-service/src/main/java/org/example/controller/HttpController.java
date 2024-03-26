package org.example.controller;

import org.example.content.Keeper;
import org.example.content.Quote;
import org.example.http.Fetcher;
import org.example.http.Request;
import org.example.http.response.FavIconResponse;
import org.example.http.response.HTMLResponse;
import org.example.http.response.RedirectResponse;
import org.example.http.response.Response;

public class HttpController {
    private Request request;

    public HttpController(Request request) {
        this.request = request;
    }


    public Response get(){
        StringBuilder listOfQuotes = new StringBuilder("");
        for (Quote quote : Keeper.inMemoryQuotes) {
            listOfQuotes.append("<li class=\"list-group-item\">");
            listOfQuotes.append("<strong>" + quote.getAuthor() + "</strong>: " + quote.getText());
            listOfQuotes.append("</li>");
        }
        Fetcher.fetchQuoteOfTheDay();
        String quoteOfDay = Keeper.quoteOfTheDay.getAuthor() + ": " + Keeper.quoteOfTheDay.getText(); // Placeholder, replace with actual quote of the day

        String htmlBody = "" +
                "<div class=\"container text-center\">" +
                "<h2>Enter Quote Details</h2>" +
                "<form method=\"POST\" action=\"/save\" class=\"form-inline justify-content-center\">" +
                "<div class=\"form-group\">" +
                "<label for=\"author\">Author:</label>" +
                "<input name=\"author\" type=\"text\" class=\"form-control mx-2\">" +
                "</div>" +
                "<div class=\"form-group\">" +
                "<label for=\"quote\">Quote:</label>" +
                "<input name=\"quote\" type=\"text\" class=\"form-control mx-2\">" +
                "</div>" +
                "<button type=\"submit\" class=\"btn btn-primary mx-2\">Save</button>" +
                "</form>" +
                "<hr>" +
                "<h4>" + quoteOfDay + "</h4>" +
                "<h4>All Quotes</h4>" +
                "<ul class=\"list-group text-center\">" +
                listOfQuotes.toString() +
                "</ul>" +
                "</div>";

        String content = "<html><head><title>Quote manager</title>" +
                "<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                "<style>" +
                "body { display: flex; justify-content: center; align-items: center; min-height: 100vh; }" +
                "</style>" +
                "</head>\n";
        content += "<body>" + htmlBody + "</body></html>";
        return new HTMLResponse(content);
    }




    public Response post(){
        return new RedirectResponse("/quotes");
    }

    public Response getFavIcon() {
        //obavezno promijeniti kasnije ovu putanju
        return new FavIconResponse("src/main/java/org/example/content/images.ico");
    }
}
