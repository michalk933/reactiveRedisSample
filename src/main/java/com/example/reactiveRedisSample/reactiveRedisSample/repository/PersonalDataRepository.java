package com.example.reactiveRedisSample.reactiveRedisSample.repository;

import com.example.reactiveRedisSample.reactiveRedisSample.domain.PersonalData;
import reactor.core.publisher.Mono;

public interface PersonalDataRepository {

    Mono<PersonalData> save(PersonalData personalData);

    Mono<PersonalData> findById(String id);

    Mono<PersonalData> update(PersonalData personalData);

    Mono<String> delete(String id);

}
