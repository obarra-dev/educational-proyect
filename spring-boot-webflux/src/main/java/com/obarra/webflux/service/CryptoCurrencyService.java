package com.obarra.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CryptoCurrencyService {

    @Autowired
    private WebClient webClient;

    public Mono<Object> getCoins(){
        return webClient.get().uri("https://api.bitso.com/v3/available_books/")
                .retrieve().bodyToMono(Object.class);
    }

    public Mono<Object> getTicker(final String id){
        return webClient.get().uri("https://api.bitso.com/v3/ticker/?book={id}", id)
                .retrieve().bodyToMono(Object.class);
    }

    public Mono<String> getTickerString(final String id){
        Mono<Object> ticker = getTicker(id);
        return ticker.map(Object::toString);
    }

}
