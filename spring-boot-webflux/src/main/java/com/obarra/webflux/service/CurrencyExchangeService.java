package com.obarra.webflux.service;

import com.obarra.webflux.model.CurrencyExchange;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyExchangeService {

    private static List<CurrencyExchange> currencyExchanges;

    static {
        currencyExchanges = Arrays.asList(new CurrencyExchange[] {
                new CurrencyExchange("Dolar", 85.2),
                new CurrencyExchange("Euro", 66.66),
                new CurrencyExchange("Boliviano", 8.84),
                new CurrencyExchange("Quetzal", 7.99),
                new CurrencyExchange("Yuan", 8.76),
                new CurrencyExchange("Bitcoin", 631298.62)
        });
    }

    public Flux<CurrencyExchange> findAll(){
        return Flux.fromIterable(currencyExchanges).delayElements(Duration.ofSeconds(2));
    }
}
