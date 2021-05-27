package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateTokenRequest {
    @NotNull
    private String userName;
}
