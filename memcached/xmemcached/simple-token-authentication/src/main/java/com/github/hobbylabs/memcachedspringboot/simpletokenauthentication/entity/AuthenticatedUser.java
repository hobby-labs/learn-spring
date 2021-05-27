package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity;

import lombok.Data;

@Data
public class AuthenticatedUser {
    private String userName;
    private String token;
}
