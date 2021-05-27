package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity;

import javax.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull
    private String userName;
}
