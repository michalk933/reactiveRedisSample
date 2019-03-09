package com.example.reactiveRedisSample.reactiveRedisSample.controller;

import com.example.reactiveRedisSample.reactiveRedisSample.domain.PersonalData;
import com.example.reactiveRedisSample.reactiveRedisSample.service.PersonalDataService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;


@RunWith(SpringRunner.class)
@WebFluxTest(controllers = PersonalDataController.class)
public class PersonalDataControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PersonalDataService service;

    private final PersonalData data = PersonalData.builder()
            .pesel("12345678901")
            .lastName("Kuchciak")
            .build();

    @Test
    public void addPersonalDataSuccessfullTest() {
        Mockito.when(service.addPersonalData(Mockito.any(PersonalData.class))).thenReturn(Mono.just(data));

        webTestClient.post()
                .uri("http://localhost:8090/api/v1/add")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{\"pesel\":\"12345678901\", \"lastname\":\"Kuchciak\"}")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(PersonalData.class)
                .value(personalData -> Assertions.assertThat(personalData).isInstanceOf(PersonalData.class))
                .value(personalData -> {
                    Assertions.assertThat(personalData.getPesel()).isEqualTo(data.getPesel());
                    Assertions.assertThat(personalData.getLastName()).isEqualTo(data.getLastName());
                });
    }

    @Test
    public void updatePersonalDataSuccessfullTest() {
        Mockito.when(service.updateTestData(Mockito.any(PersonalData.class))).thenReturn(Mono.just(data));

        webTestClient.put()
                .uri("http://localhost:890/api/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody("{\"pesel\":\"12345678901\", \"lastname\":\"Kuchciak\"}")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(PersonalData.class)
                .value(personalData -> {
                    Assertions.assertThat(personalData.getPesel()).isEqualTo(data.getPesel());
                    Assertions.assertThat(personalData.getLastName()).isEqualTo(data.getLastName());
                });
    }

    @Test
    public void deletePersonalDataSuccessfullTest() {
        String pesel = "12345678901";
        Mockito.when(service.deletePersonalData(Mockito.anyString())).thenReturn(Mono.just(pesel));

        webTestClient.delete()
                .uri("http://localhost:8090/api/v1/" + pesel)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(String.class)
                .value(s -> Assertions.assertThat(s).isEqualTo(pesel));
    }

    @Test
    public void getPersonalDataSuccessfullTest() {
        String pesel = "12345678901";
        Mockito.when(service.getPersonalData(Mockito.anyString())).thenReturn(Mono.just(data));

        webTestClient.get()
                .uri("http://localhost:8090/api/v1/details/" + pesel)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody(PersonalData.class)
                .value(personalData -> {
                    Assertions.assertThat(personalData.getPesel()).isEqualTo(data.getPesel());
                    Assertions.assertThat(personalData.getLastName()).isEqualTo(data.getLastName());
                });
    }

    @Test
    public void addPersonalDataDontProvidedPersonalDataTest() {
        webTestClient.post()
                .uri("http://localhost:8090/api/v1/add")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void updatePersonalDataDontProvidedPersonalDataTest() {
        webTestClient.put()
                .uri("http://localhost:890/api/v1/")
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void deletePersonalDataDontProvidedPeselTest() {
        webTestClient.delete()
                .uri("http://localhost:8090/api/v1/")
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

    @Test
    public void getPersonalDataDontProvidedPeselTest() {
        webTestClient.get()
                .uri("http://localhost:8090/api/v1/details/")
                .exchange()
                .expectStatus()
                .is4xxClientError();
    }

}