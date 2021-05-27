package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.repository;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthenticatedUser;
import lombok.AllArgsConstructor;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Repository
@AllArgsConstructor
public class AuthTokenRepositoryImpl implements AuthTokenRepository {

    private final MemcachedClient memcachedClient;

    public String findBy(String token)
            throws InterruptedException, TimeoutException, MemcachedException {
        return memcachedClient.get(token, 3000);
    }
    public boolean setToken(String userName, String token)
            throws InterruptedException, TimeoutException, MemcachedException {
        return memcachedClient.set(token, 3000, userName);
    }
}
