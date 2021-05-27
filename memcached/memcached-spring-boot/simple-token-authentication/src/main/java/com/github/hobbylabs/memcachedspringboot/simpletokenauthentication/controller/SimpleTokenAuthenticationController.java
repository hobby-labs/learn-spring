package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.controller;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.service.AuthenticationService;
import lombok.AllArgsConstructor;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class SimpleTokenAuthenticationController {

    private final AuthenticationService authenticationService;

    @GetMapping(value = {"/authToken"})
    public String authToken() throws InterruptedException, TimeoutException, MemcachedException {
        authenticationService.auth(null, null, null);
        return "Hello World";
    }

    @GetMapping(value = {"/setToken"})
    public String setToken() throws InterruptedException, TimeoutException, MemcachedException {
        authenticationService.setToken();
        return "Set token";
    }
}
