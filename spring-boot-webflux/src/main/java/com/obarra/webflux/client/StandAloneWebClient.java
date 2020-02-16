package com.obarra.webflux.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StandAloneWebClient {

    public static String invokeToCryptoCurrency(){
        WebClient webClient = WebClient.create("http://localhost:8080");
        Mono<ClientResponse> result = webClient
                .get().uri("/cryptocurrency-handler/life").accept(MediaType.TEXT_PLAIN).exchange();
        String message = result.flatMap(res -> res.bodyToMono(String.class)).block();
        return message;
    }
}
