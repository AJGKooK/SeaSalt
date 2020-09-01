package com.example.restservice;

import java.util.concurrent.atomic.AtomicLong;
import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		Random rand = new Random();
		return new Greeting(counter.incrementAndGet(), String.format(template, name), rand.nextInt(1000));
	}

	@GetMapping("/stats")
	public SystemStats stats() {
		return new SystemStats();
	}
}
