package com.obarra.webflux.controller;

import com.obarra.webflux.service.CryptoCurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("cryptocurrency")
public class CryptoCurrencyController {

    @Autowired
    private CryptoCurrencyService cryptoCurrencyService;

    @GetMapping("/coins")
    public Mono<Object> getCoins(){
        return cryptoCurrencyService.getCoins();
    }

    @GetMapping("/ticker/{id}")
    public Mono<Object> getTicker(@PathVariable("id") String id){
        return cryptoCurrencyService.getTicker(id);
    }

    @GetMapping("/ticker-string/{id}")
    public Mono<String> getTickerString(@PathVariable("id") String id){
        return cryptoCurrencyService.getTickerString(id);
    }
}
