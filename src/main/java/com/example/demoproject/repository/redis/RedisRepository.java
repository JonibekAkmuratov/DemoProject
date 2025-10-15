package com.example.demoproject.repository.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import uz.yt.ramsservice.repository.Repository;

import java.time.Duration;
import java.util.Optional;

/**
 * @author : <b>Elmurodov Javohir </b>
 * @since : 9/26/2023 : 4:13 PM
 */
public interface RedisRepository extends Repository {
    String REPORT = "REPORT";
    String MAIN = "MAIN";
    String BY_REGION = "REGION";
    String USER_SESSION_PREFIX = "RAMS_USER_SESSION";
    String AUTHORITY_PREFIX = "AUTHORITY";
    String BASIC_USER_PREFIX = "BASIC_USER";
    String OTP_PREFIX = "OTP";

    void save(Object key, Object o);

    void save(String keyPrefix, Object key, Object o);

    void save(Object key, Object o, Duration ttl);
    void save(String keyPrefix, Object key, Object o, Duration ttl);

    Object get(Object key);

    <T> Optional<T> get(Object key, Class<T> clazz);

    <T> Optional<T> get(Object key, TypeReference<T> typeReference);

    <T> Optional<T> get(String keyPrefix, Object key, Class<T> clazz);

    <T> Optional<T> get(String keyPrefix, Object key, TypeReference<T> typeReference);

    default void evict(Object key) {
        save(key, "", Duration.ofMillis(1));
    }

    default void evict(String keyPrefix, Object key){
        save(keyPrefix, key, "", Duration.ofMillis(1));
    }

    default String getKey(String keyPrefix, Object key) {
        return keyPrefix + "::" + key;
    }

}
