package com.example.client;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;

public class Entry {

    public static void main(String[] args) throws IOException, InterruptedException {
        var service = new CatFactsService();

        String jsonCatFact1 = service.getRandomCatFact(70);
        String jsonCatFact2 = service.getRandomCatFact(140);

        System.out.println(jsonCatFact1);
        System.out.println(jsonCatFact2);
    }
}

class CatFactsService {
    private final HttpClient httpClient;

    private static final String randomFactURI = "https://catfact.ninja/fact?max_length=%d";

    CatFactsService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String getRandomCatFact(int maxLength) throws IOException, InterruptedException {
        //Creates a new request for sending
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(String.format(randomFactURI, maxLength)))
                .build();

        //Gets response with the body as String
        HttpResponse<String> res = this.httpClient.send(req, HttpResponse.BodyHandlers.ofString());

        return res.body();
    }

}