package com.example.reactiveRedisSample.reactiveRedisSample.controller;


import com.example.reactiveRedisSample.reactiveRedisSample.domain.PersonalData;
import com.example.reactiveRedisSample.reactiveRedisSample.service.PersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/v1")
public class PersonalDataController {

    @Autowired
    private PersonalDataService personalDataService;

    @GetMapping("/details/{pesel}")
    Mono<PersonalData> getPersobaldata(@NotNull @PathVariable("pesel") String pesel) {
        return personalDataService.getPersonalData(pesel);
    }

    @PostMapping("/add")
    Mono<PersonalData> addPersonalData(@NotNull @RequestBody PersonalData personalData) {
        return personalDataService.addPersonalData(personalData);
    }

    @PutMapping
    Mono<PersonalData> updatePersonalData(@NotNull @RequestBody PersonalData personalData){
        return personalDataService.updateTestData(personalData);
    }

    @DeleteMapping("/{pesel}")
    Mono<String> deleteById(@NotNull @PathVariable("pesel") String pesel){
        return personalDataService.deletePersonalData(pesel);
    }

}
