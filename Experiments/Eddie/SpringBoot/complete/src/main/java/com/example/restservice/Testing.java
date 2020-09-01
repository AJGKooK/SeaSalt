package com.example.restservice;

public class Testing {

    private final String test;

    public Testing(String test) {
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public String getProcessorCores() {
        return String.valueOf(Runtime.getRuntime().availableProcessors());
    }

    public String getMemoryAllocation() {
        return String.valueOf(Runtime.getRuntime().maxMemory());
    }
}
