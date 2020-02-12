package com.obarra.webflux.controller;

import org.reactivestreams.Publisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class CurrencyController {

    @GetMapping("/national-currency")
    public Publisher<String> getNationalCurrency(){
        return Mono.just("Peso");
    }
}
