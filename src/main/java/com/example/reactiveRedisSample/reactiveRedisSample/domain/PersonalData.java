package com.example.reactiveRedisSample.reactiveRedisSample.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Value
@Builder
public class PersonalData {

    private String pesel;

    private String lastName;

    @JsonCreator
    public PersonalData(@JsonProperty("pesel") String pesel, @JsonProperty("lastName") String lastName){
        this.pesel = pesel;
        this.lastName = lastName;
    }

}
