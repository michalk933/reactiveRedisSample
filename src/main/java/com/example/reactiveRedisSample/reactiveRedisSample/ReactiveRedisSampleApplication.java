package com.example.reactiveRedisSample.reactiveRedisSample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

import java.time.Duration;

@SpringBootApplication
public class ReactiveRedisSampleApplication {



	public static void main(String[] args) {
		SpringApplication.run(ReactiveRedisSampleApplication.class, args);

	}

}
