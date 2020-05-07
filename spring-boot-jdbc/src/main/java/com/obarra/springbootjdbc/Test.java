package com.obarra.springbootjdbc;

import com.obarra.springbootjdbc.repository.impl.JDBCBillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @Autowired
    private JDBCBillingRepository jdbcBillingRepository;

    @GetMapping("/test")
    public Object test(){
        return jdbcBillingRepository.findAll();
    }
}
