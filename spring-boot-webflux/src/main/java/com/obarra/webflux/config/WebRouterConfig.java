package com.obarra.webflux.config;

import com.obarra.webflux.handler.CryptoCurrencyHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class WebRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routeCryptoCurrencyRequests(CryptoCurrencyHandler cryptoCurrencyHandler){
        return RouterFunctions
                .route(RequestPredicates.GET("/cryptocurrency-handler/coins"), cryptoCurrencyHandler::getCoins)
                .andRoute(RequestPredicates.GET("/cryptocurrency-handler/ticker/{id}"), cryptoCurrencyHandler::getTicker)
                .andRoute(RequestPredicates.GET("/cryptocurrency-handler/life"), cryptoCurrencyHandler::isAlive);
    }
}
