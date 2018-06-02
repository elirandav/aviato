package com.example.aviato;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
public class DeferredResultController {
    static ExecutorService threadPool =
            new ThreadPoolExecutor(2, 200, 60,
                    TimeUnit.SECONDS, new SynchronousQueue<>());
    private Request request = new Request();

    @RequestMapping(value="/getTweetsDeferredResult/{name}", method = RequestMethod.GET)
    public DeferredResult<String> search(@PathVariable("name") String name) {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        threadPool.submit(() -> deferredResult.setResult(request.runSleepOnOtherService(name)));
        return deferredResult;
    }

}