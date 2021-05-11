package com.github.hobbylabs.lettuceandthreadnum.controller;

import com.github.hobbylabs.lettuceandthreadnum.data.ResponseData;
import com.github.hobbylabs.lettuceandthreadnum.service.RedisService;
import com.github.hobbylabs.lettuceandthreadnum.thread.SomeThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private final RedisService redisService;

    public Controller(RedisService redisService) {
        this.redisService = redisService;
    }

    @RequestMapping(value = {"/getSomething"}, method = RequestMethod.GET)
    public ResponseData getSomething() {
        String redisMessage = redisService.getRedisMessage("something");
        ResponseData responseData = new ResponseData(
                Thread.activeCount(), redisMessage, "Response from getSomething()");

        return responseData;
    }

    @RequestMapping(value = {"/waitSomething"}, method = RequestMethod.GET)
    public ResponseData waitSomething() throws InterruptedException {

        LOGGER.info("Entering waiting state: activeCount()=" + Thread.activeCount());
        synchronized (this) {
            wait();
        }

        ResponseData responseData = new ResponseData(
                Thread.activeCount(), "(None)", "Response from waitSomething()");

        return responseData;
    }

    @RequestMapping(value = {"/setSomething"}, method = RequestMethod.POST)
    public ResponseData setSomething() {
        redisService.setRedisMessage("something", "Foo Bar");
        ResponseData responseData = new ResponseData(
                Thread.activeCount(), "(None)", "Response from setSomething()");

        return responseData;
    }

    @RequestMapping(value = {"/incrementThread"}, method = RequestMethod.POST)
    public ResponseData incrementThread() {
        SomeThread someThread = new SomeThread();
        someThread.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ResponseData responseData = new ResponseData(
                Thread.activeCount(), "(None)", "Response from incrementThread()");

        return responseData;
    }

}
