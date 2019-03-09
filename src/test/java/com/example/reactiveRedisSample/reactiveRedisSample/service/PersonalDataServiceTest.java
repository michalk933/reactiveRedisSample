package com.example.reactiveRedisSample.reactiveRedisSample.service;

import com.example.reactiveRedisSample.reactiveRedisSample.domain.PersonalData;
import com.example.reactiveRedisSample.reactiveRedisSample.repository.PersonalDataRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


public class PersonalDataServiceTest {

    private PersonalDataRepository personalDataRepository = Mockito.mock(PersonalDataRepository.class);
    private PersonalDataService service = new PersonalDataService(personalDataRepository);

    @Before
    public void setup() {
        Mockito.when(personalDataRepository.findById(Mockito.anyString())).thenReturn(Mono.just(PersonalData.builder()
                .pesel("12345678901")
                .lastName("Kuchciak")
                .build()
        ));
    }

    @Test
    public void getPersonalDataTest() {
        String pesel = "12345678901";
        StepVerifier.create(service.getPersonalData(pesel))
                .expectNextMatches(personalData -> personalData.getLastName().equals("Kuchciak") && personalData.getPesel().equals(pesel))
                .expectComplete()
                .verify();
    }

    @Test
    public void addPersonalDataTest() {
        StepVerifier.create(service.addPersonalData(PersonalData.builder()
                .pesel("12345678902")
                .lastName("Kuchciak2")
                .build()))
                .expectNextMatches(data -> data.getPesel().equals("12345678902") && data.getLastName().equals("Kuchciak2"))
                .expectComplete()
                .verify();
    }

    @Test
    public void updataPersonalData() {
        StepVerifier.create(service.updateTestData(PersonalData.builder()
                .pesel("12345678902")
                .lastName("Kuchciak2")
                .build()))
                .expectNextMatches(data -> data.getPesel().equals("12345678902") && data.getLastName().equals("Kuchciak2"))
                .expectComplete()
                .verify();
    }

}