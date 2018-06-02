package com.example.aviato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicInteger;

public class Request {
    static Logger logger = LoggerFactory.getLogger(Request.class);
    private AtomicInteger requestCounter = new AtomicInteger(1);
    public Request() {
    }
    private static final long  MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE ;
    }

    public String runSleepOnOtherService(String name) {
        int requestId = requestCounter.getAndIncrement();
        logger.info("process request: " + requestId);
        String url = "http://localhost:8090/sleep/" + Params.SleepTimeInMs;
        new RestTemplate().getForObject(url, Boolean.TYPE);
        logger.info("Done process request: " + requestId);
        logger.info("Number of threads: " + Thread.activeCount());
        long bytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        logger.info("Memory used: " + bytesToMegabytes(bytes) + "MB");
        return name;
    }
}