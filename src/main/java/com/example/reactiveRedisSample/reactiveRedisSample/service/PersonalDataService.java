package com.example.reactiveRedisSample.reactiveRedisSample.service;

import com.example.reactiveRedisSample.reactiveRedisSample.domain.PersonalData;
import com.example.reactiveRedisSample.reactiveRedisSample.repository.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonalDataService {

    Logger log = LoggerFactory.getLogger(PersonalDataService.class);

    private final PersonalDataRepository personalDataRepository;

    public Mono<PersonalData> getPersonalData(String id){
        return personalDataRepository.findById(id);
    }

    public Mono<PersonalData> addPersonalData(PersonalData personalData){
        log.info("Add: " + personalData.toString());
        return personalDataRepository.save(personalData);
    }

    public Mono<PersonalData> updateTestData(PersonalData personalData){
        log.info("Uptade: " + personalData.toString());
        return personalDataRepository.update(personalData);
    }

    public Mono<String> deletePersonalData(String id){
        log.info("Delete: " + this.getPersonalData(id));
        return personalDataRepository.delete(id);
    }

}
