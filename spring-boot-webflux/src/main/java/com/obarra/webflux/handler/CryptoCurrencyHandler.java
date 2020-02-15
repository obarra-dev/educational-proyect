package com.obarra.webflux.handler;

import com.obarra.webflux.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.Optional;

public class CryptoCurrencyHandler {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    public Mono<ServerResponse> getCoins(final ServerRequest serverRequest){
        final Mono<Object> coins = cryptoCurrencyService.getCoins();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(coins, Object.class);
    }

    public Mono<ServerResponse> getTicker(final ServerRequest serverRequest){
        String id = serverRequest.pathVariable("id");
        final Mono<Object> ticker = cryptoCurrencyService.getTicker(id);
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(ticker, Object.class);
    }

    public Mono<ServerResponse> isAlive(final ServerRequest serverRequest){
        //Text plan with Body insert is deprecated
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("It's alive"));
    }

}
