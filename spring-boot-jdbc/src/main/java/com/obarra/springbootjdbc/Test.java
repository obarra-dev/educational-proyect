package com.obarra.springbootjdbc;

import com.obarra.springbootjdbc.model.Billing;
import com.obarra.springbootjdbc.repository.impl.JDBCBillingRepository;
import com.obarra.springbootjdbc.repository.impl.NamedParameterJDBCBillingRepository;
import com.obarra.springbootjdbc.repository.impl.SimpleJDBCInsertBillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Test {

    @Autowired
    private JDBCBillingRepository jdbcBillingRepository;

    @Autowired
    private SimpleJDBCInsertBillingRepository simpleJDBCInsertBillingRepository;

    @GetMapping("/savesimple")
    public Object savesimple(){
        Billing b = new Billing();
        b.setAmount(new BigDecimal("404"));
        b.setBillingId(2L);
        b.setCreateDate(LocalDate.now());

        return simpleJDBCInsertBillingRepository.save(b);
    }

    @GetMapping("/filter")
    public Object filter(){
        Billing b = new Billing();
        b.setAmount(new BigDecimal("404"));
        return jdbcBillingRepository.filter(b);
    }

    @GetMapping("/save")
    public Object save(){
        Billing b = new Billing();
        b.setAmount(new BigDecimal("404"));
        b.setCreateDate(LocalDate.now());
        return jdbcBillingRepository.saveAndReturnId(b);
    }


    @GetMapping("/saveall")
    public Object saveall(){
        Billing b = new Billing();
        b.setAmount(new BigDecimal("404"));
        b.setBillingId(2L);
        b.setCreateDate(LocalDate.now());

        List<Billing> billingList = new ArrayList<>();
        billingList.add(b);
        b = new Billing();
        b.setAmount(new BigDecimal("407"));
        b.setBillingId(3L);
        b.setCreateDate(LocalDate.now());
        billingList.add(b);

        b = new Billing();
        b.setAmount(new BigDecimal("408"));
        b.setBillingId(4L);
        b.setCreateDate(LocalDate.now());
        billingList.add(b);
        return jdbcBillingRepository.save(billingList);
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
