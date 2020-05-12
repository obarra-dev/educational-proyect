package com.obarra.springbootredis.controller;

import com.obarra.springbootredis.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private PolicyRepository policyRepository;

    @Autowired
    public PolicyController(final PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @GetMapping
    public Map<String,String> findAll() {
        Map<Object, Object> policiesMap = policyRepository.findAll();
        return policiesMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> (String)e.getKey(),
                        e -> e.getValue().toString()
                ));
    }

    @DeleteMapping("/{policyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long policyId) {
        policyRepository.delete(policyId);
    }
}
