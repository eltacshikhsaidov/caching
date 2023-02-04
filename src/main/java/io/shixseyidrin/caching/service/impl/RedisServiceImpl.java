package io.shixseyidrin.caching.service.impl;

import io.shixseyidrin.caching.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

    private final RedisTemplate<Object, Object> redisTemplate;

    @Override
    public <K, V> void set(K key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public <K> Object get(K key) {
        return redisTemplate.opsForValue().get(key);
    }
}
