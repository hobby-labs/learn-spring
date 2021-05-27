package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.controller;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthRequest;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthResponse;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthenticatedUser;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.CreateTokenRequest;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.service.AuthenticationService;
import lombok.AllArgsConstructor;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class SimpleTokenAuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = {"/authToken"})
    public AuthResponse authToken(@RequestBody AuthRequest authRequest) throws InterruptedException, TimeoutException, MemcachedException {
        return authenticationService.authToken(authRequest);
    }

    @PostMapping(value = {"/setToken"})
    public AuthenticatedUser setToken(@RequestBody CreateTokenRequest user) throws Exception {
        return authenticationService.setToken(user);
    }
}
