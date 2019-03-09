package com.example.reactiveRedisSample.reactiveRedisSample.repository;

import com.example.reactiveRedisSample.reactiveRedisSample.domain.PersonalData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonalDataRadisRepositoryImplTest {

    @Autowired
    private PersonalDataRadisRepositoryImpl personalDataRadisRepository;

    @Test
    public void getPersonalData() {
        PersonalData personalData = PersonalData.builder()
                .pesel("12345678901")
                .lastName("Kuchciak")
                .build();
        StepVerifier.create(personalDataRadisRepository.save(personalData)
                .flatMap(data -> personalDataRadisRepository.findById(data.getPesel())))
                .expectNext(personalData)
                .verifyComplete();
    }

    @Test
    public void removePersonalDataTest() {
        PersonalData personalData = PersonalData.builder()
                .pesel("12345678901")
                .lastName("Kuchciak")
                .build();
        StepVerifier.create(personalDataRadisRepository.delete(personalData.getPesel())
                .flatMap(s -> personalDataRadisRepository.findById(s)))
                .expectComplete()
                .verify();
    }

    @Test
    public void updatePersonalData() {
        PersonalData personalData = PersonalData.builder()
                .pesel("12345678901")
                .lastName("Kuchciak")
                .build();
        personalDataRadisRepository.save(PersonalData.builder()
                .pesel("12345678901")
                .lastName("Test")
                .build());
        StepVerifier.create(personalDataRadisRepository.save(PersonalData.builder()
                .pesel("12345678901")
                .lastName("Test")
                .build())
                .map(data -> personalDataRadisRepository.update(personalData))
                .flatMap(personalDataMono -> Mono.just(personalData.getLastName())))
                .expectNextMatches(s -> s.equals(personalData.getLastName()))
                .verifyComplete();
    }

    @Test
    public void addPersonalData(){
        PersonalData personalData = PersonalData.builder()
                .pesel("12345678901")
                .lastName("Kuchciak")
                .build();
        StepVerifier.create(personalDataRadisRepository.save(personalData))
                .expectNext(personalData)
                .verifyComplete();
    }

}