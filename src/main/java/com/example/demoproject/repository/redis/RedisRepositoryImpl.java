//package com.example.demoproject.repository.redis;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Repository;
//
//import java.time.Duration;
//import java.util.Optional;
//
//
//@Repository
//@RequiredArgsConstructor
//public class RedisRepositoryImpl implements RedisRepository {
//
//    private final ObjectMapper objectMapper;
//    private final ValueOperations<String, Object> operations;
//
//
//    @Override
//    public void save(Object key, Object o) {
//        operations.set(String.valueOf(key), o);
//    }
//
//    @Override
//    public void save(String keyPrefix, Object key, Object o) {
//        operations.set(getKey(keyPrefix, key), o);
//    }
//
//    @Override
//    public void save(Object key, Object o, Duration ttl) {
//        operations.set(String.valueOf(key), o, ttl);
//    }
//
//    @Override
//    public void save(String keyPrefix, Object key, Object o, Duration ttl) {
//        operations.set(getKey(keyPrefix, key), o, ttl);
//    }
//
//    @Override
//    public Object get(Object key) {
//        return operations.get(key);
//    }
//
//
//    @Override
//    public <T> Optional<T> get(Object key, Class<T> clazz) {
//        Object o = get(key);
//        if (o == null)
//            return Optional.empty();
//        T t = objectMapper.convertValue(o, clazz);
//        return Optional.of(t);
//    }
//
//    @Override
//    public <T> Optional<T> get(Object key, TypeReference<T> typeReference) {
//        Object o = get(key);
//        if (o == null)
//            return Optional.empty();
//        T t = objectMapper.convertValue(o, typeReference);
//        return Optional.of(t);
//    }
//
//    @Override
//    public <T> Optional<T> get(String keyPrefix, Object key, Class<T> clazz) {
//        Object o = get(getKey(keyPrefix, key));
//        if (o == null)
//            return Optional.empty();
//        T t = objectMapper.convertValue(o, clazz);
//        return Optional.of(t);
//    }
//
//    @Override
//    public <T> Optional<T> get(String keyPrefix, Object key, TypeReference<T> typeReference) {
//        Object o = get(getKey(keyPrefix, key));
//        if (o == null)
//            return Optional.empty();
//        T t = objectMapper.convertValue(o, typeReference);
//        return Optional.of(t);
//    }
//
//}
