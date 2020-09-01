package com.serviceapplication;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SystemStats {

    public SystemStats() {
    }

    public String getHostname() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            return ip.getHostName();
        } catch (UnknownHostException e) {
            return "Not Available";
        }

    }

    public String getProcessorCores() {
        return String.valueOf(Runtime.getRuntime().availableProcessors());
    }

    public String getMemoryAllocation() {
        return String.valueOf(Runtime.getRuntime().maxMemory());
    }
}
