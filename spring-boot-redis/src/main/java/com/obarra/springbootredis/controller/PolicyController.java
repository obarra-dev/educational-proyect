package com.obarra.springbootredis.controller;

import com.obarra.springbootredis.domain.Policy;
import com.obarra.springbootredis.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    private PolicyRepository policyRepository;

    @Autowired
    public PolicyController(final PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @PostMapping
    public Policy save(@RequestBody Policy policy) {
        policyRepository.saveOrUpdate(policy);
        return policy;
    }

    @GetMapping("/{policyId}")
    public Policy find(@PathVariable Long policyId) {
        return policyRepository.findBy(policyId);
    }

    @GetMapping("/entries")
    public Map<String, String> findAllEntries() {
        Map<Object, Object> policiesMap = policyRepository.findAllEntries();
        return policiesMap
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> (String) e.getKey(),
                        e -> e.getValue().toString()
                ));
    }

    @GetMapping
    public List<Policy> findAll() {
        return policyRepository.findAll();
    }

    @DeleteMapping("/{policyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long policyId) {
        policyRepository.delete(policyId);
    }
}
