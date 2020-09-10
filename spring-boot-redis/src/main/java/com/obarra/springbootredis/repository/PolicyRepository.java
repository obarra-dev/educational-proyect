package com.obarra.springbootredis.repository;

import com.obarra.springbootredis.domain.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PolicyRepository {

    private static final String KEY = "POLICY";

    private final HashOperations hashOperations;

    @Autowired
    public PolicyRepository(final RedisTemplate<String, Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }

    public Policy findBy(final Long policyId) {
        return (Policy) hashOperations.get(KEY, policyId);
    }

    public Map<Object, Object> findAllEntries() {
        return hashOperations.entries(KEY);
    }

    public List<Policy> findAll() {
        return hashOperations.values(KEY);
    }

    public void saveOrUpdate(final Policy policy) {
        hashOperations.put(KEY, policy.getPolicyId(), policy);
    }

    public void delete(final Long policyId) {
        hashOperations.delete(KEY, policyId);
    }
}
