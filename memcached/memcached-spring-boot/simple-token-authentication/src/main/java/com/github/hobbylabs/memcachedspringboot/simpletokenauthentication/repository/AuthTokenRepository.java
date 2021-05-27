package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.repository;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.User;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.util.concurrent.TimeoutException;

public interface AuthTokenRepository {
    User findBy(String token) throws InterruptedException, TimeoutException, MemcachedException;
    User set(String token, User user) throws InterruptedException, TimeoutException, MemcachedException;
}
