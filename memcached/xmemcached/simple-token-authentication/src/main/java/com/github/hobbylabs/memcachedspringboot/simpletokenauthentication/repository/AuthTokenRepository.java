package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.repository;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthenticatedUser;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.util.concurrent.TimeoutException;

public interface AuthTokenRepository {
    String findBy(String token) throws InterruptedException, TimeoutException, MemcachedException;
    boolean setToken(String userName, String token) throws InterruptedException, TimeoutException, MemcachedException;
}
