package com.example.aviato;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController
public class CallableController {
    private final Request request = new Request();

    @RequestMapping(value="/getTweetsCallable/{name}", method = RequestMethod.GET)
    public Callable<String> search(@PathVariable("name") String name) {
        return () -> request.runSleepOnOtherService(name);
    }
}