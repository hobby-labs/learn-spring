package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Authentication response that the server respond to the client.
 */
@Data
public class AuthResponse {
    @NotNull
    private int code;
    private String message;
}
