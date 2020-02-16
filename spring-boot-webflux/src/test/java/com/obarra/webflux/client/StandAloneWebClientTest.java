package com.obarra.webflux.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StandAloneWebClientTest {

    @Test
    void invokeToCryptoCurrency() {
        assertEquals("It's alive", StandAloneWebClient.invokeToCryptoCurrency());
    }
}