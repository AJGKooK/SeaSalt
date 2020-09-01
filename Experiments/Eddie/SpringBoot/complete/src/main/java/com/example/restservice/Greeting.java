package com.example.restservice;

public class Greeting {

	private final long id;
	private final String content;
	private final int test;

	public Greeting(long id, String content, int test) {
		this.id = id;
		this.content = content;
		this.test = test;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public int getTest() {
		return test;
	}
}
