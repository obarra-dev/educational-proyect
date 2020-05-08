package com.obarra.springbootjdbc;

import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.impl.JDBCBillingRepository;
import com.obarra.springbootjdbc.repository.impl.NamedParameterJDBCBillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class Test {

    @Autowired
    private JDBCBillingRepository jdbcBillingRepository;

    @GetMapping("/save")
    public Object save(){
        Billing b = new Billing();
        b.setAmount(new BigDecimal("404"));
        b.setBillingId(2L);
        b.setCreateDate(LocalDate.now());
        return jdbcBillingRepository.save(b);
    }

    @GetMapping("/update")
    public Object update(){
        Billing b = new Billing();
        b.setAmount(new BigDecimal("405"));
        b.setCreateDate(LocalDate.now());
        return jdbcBillingRepository.update(b, 1L);
    }

    @GetMapping("/list")
    public Object list(){
        return jdbcBillingRepository.findAll();
    }

    @GetMapping("/find")
    public Object find(){
        return jdbcBillingRepository.findById(1L);
    }

    @GetMapping("/count")
    public Object count(){
        return jdbcBillingRepository.count();
    }

    @GetMapping("/delete")
    public Object delete(){
        return jdbcBillingRepository.delete(1L);
    }
}
