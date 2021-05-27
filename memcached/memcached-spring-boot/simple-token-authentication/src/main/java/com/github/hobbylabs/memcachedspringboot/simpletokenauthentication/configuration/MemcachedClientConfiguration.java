package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.configuration;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class MemcachedClientConfiguration {

    @Value("${memcached.server.list.string}")
    private String serverListString;

    @Bean
    public MemcachedClient configureMemcachedClient() throws IOException {
        return new XMemcachedClientBuilder(AddrUtil.getAddresses("127.0.0.1:11211 127.0.0.1:11212 127.0.0.1:11213")).build();
    }
}
