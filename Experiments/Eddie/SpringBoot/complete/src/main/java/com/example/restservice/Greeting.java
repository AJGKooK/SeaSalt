package com.example.restservice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Greeting {

	private final long id;
	private final String content;
	private final int luckyNumber;

	public Greeting(long id, String content, int lucky) {
		this.id = id;
		this.content = content;
		this.luckyNumber = lucky;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public int getTest() {
		return luckyNumber;
	}

	public String getTime() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		return formatter.format(date);
	}
}
