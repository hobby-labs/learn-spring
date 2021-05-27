package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final MemcachedService memcachedService;

    public void auth(String userId, String password, String token) {
        if (token != null) {

        }
        if (token == null) {

        }
    }

    public void authByToken(String userId, String token) {

    }

    public void authByPassword() {

    }
}
