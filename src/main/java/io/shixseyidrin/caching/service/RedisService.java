package io.shixseyidrin.caching.service;

public interface RedisService {
    <K, V> void set(K key, V value);

    <K> Object get(K key);
}
