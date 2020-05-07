package com.obarra.springbootjdbc;

import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.impl.JDBCBillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class Test {

    @Autowired
    private JDBCBillingRepository jdbcBillingRepository;

    @GetMapping("/test")
    public Object test(){
        Billing b = new Billing();
        b.setAmount(new BigDecimal("404"));
        b.setBillingId(2L);
        b.setCreateDate(LocalDate.now());
        return jdbcBillingRepository.save(b);
    }
}
