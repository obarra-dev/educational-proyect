package com.obarra.springbootredis.repository;

import com.obarra.springbootredis.domain.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

@Repository
public class PolicyRepository {

    private static final String KEY = "POLICY";

    private RedisTemplate<String, Object> redisTemplate;

    private HashOperations hashOperations;

    @Autowired
    public PolicyRepository(final RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public Policy findBy(final Long policyId) {
        return (Policy) hashOperations.get(KEY, policyId);
    }

    public Map<Object, Object> findAll() {
        return hashOperations.entries(KEY);
    }

    public void add(final Policy policy) {
        hashOperations.put(KEY, policy.getPolicyId(), policy.getNote());
    }

    public void delete(final Long policyId) {
        hashOperations.delete(KEY, policyId);
    }
}
