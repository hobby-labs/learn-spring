package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Authentication object that the client will send to the server.
 */
@Data
public class AuthRequest {
    @NotNull
    private String token;
}
