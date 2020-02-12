package com.obarra.webflux.controller;

import com.obarra.webflux.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class CurrencyExchangeController {

    @Autowired
    public CurrencyExchangeService currencyExchangeService;

    @RequestMapping("/")
    public String index(final Model model){

        // loads 1 and display 1, stream data, data driven mode.
        IReactiveDataDriverContextVariable reactiveDataDriverContextVariable = new ReactiveDataDriverContextVariable(
                currencyExchangeService.findAll(), 1
        );

        model.addAttribute("currencyExchanges", reactiveDataDriverContextVariable);

        return "index";
    }


}
