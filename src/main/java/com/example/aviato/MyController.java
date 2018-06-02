package com.example.aviato;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final Request request = new Request();

    @RequestMapping(value="/getTweets/{name}", method = RequestMethod.GET)
    public String searchIO(@PathVariable("name") String name) {
        return request.runSleepOnOtherService(name);
    }
}
