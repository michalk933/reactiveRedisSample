package com.example.reactiveRedisSample.reactiveRedisSample.repository;

import com.example.reactiveRedisSample.reactiveRedisSample.domain.PersonalData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class PersonalDataRadisRepositoryImpl implements PersonalDataRepository {

    @Autowired
    private ReactiveRedisOperations<String, String> operation;

    @Override
    public Mono<PersonalData> save(PersonalData personalData) {
        return operation.opsForValue()
                .set(personalData.getPesel(), personalData.getLastName())
                .map(__ -> personalData);
    }

    @Override
    public Mono<PersonalData> findById(String id) {
        return operation.opsForValue()
                .get(id)
                .map(result -> new PersonalData(id, result));
    }

    @Override
    public Mono<PersonalData> update(PersonalData personalData) {
        return operation.opsForValue()
                .setIfAbsent(personalData.getPesel(), personalData.getLastName())
                .map(__ -> personalData);
    }

    @Override
    public Mono<String> delete(String id) {
        return operation.opsForValue()
                .delete(id)
                .map(__ -> id);
    }

}
