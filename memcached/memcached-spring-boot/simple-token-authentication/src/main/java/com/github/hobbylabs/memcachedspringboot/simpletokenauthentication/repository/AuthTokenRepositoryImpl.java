package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.repository;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.User;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Repository
public class AuthTokenRepositoryImpl implements AuthTokenRepository {

    private final MemcachedClient memcachedClient;

    @Autowired
    public AuthTokenRepositoryImpl(MemcachedClient memcachedClient) throws IOException {
        this.memcachedClient = memcachedClient;
    }

    public User findBy(String token) throws InterruptedException, TimeoutException, MemcachedException {
        String result = (String)memcachedClient.get(token, 2000);
        List<String> serverList = memcachedClient.getServersDescription();
        return null;
    }
    public User set(String token, User user) throws InterruptedException, TimeoutException, MemcachedException {
        memcachedClient.set("foo", 100, "Value");
        return null;
    }
}
