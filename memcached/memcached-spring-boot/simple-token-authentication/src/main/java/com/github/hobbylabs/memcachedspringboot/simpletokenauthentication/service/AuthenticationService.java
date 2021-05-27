package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.service;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.repository.AuthTokenRepositoryImpl;
import lombok.AllArgsConstructor;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthTokenRepositoryImpl authTokenRepositoryImpl;

    public void auth(String userId, String password, String token) throws InterruptedException, TimeoutException, MemcachedException {
        authTokenRepositoryImpl.findBy("foo");
    }

    public void setToken() throws InterruptedException, TimeoutException, MemcachedException {
        authTokenRepositoryImpl.set(null, null);
    }

    public void authByToken(String userId, String token) {

    }

    public void authByPassword() {

    }
}
