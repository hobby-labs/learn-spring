package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.controller;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthResponse;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthenticatedUser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = { "memcached.server.list.string=127.0.0.1:11211 127.0.0.1:11212 127.0.0.1:11213" }
)
public class SimpleTokenAuthenticationControllerE2eTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final HttpHeaders requestHeaders = new HttpHeaders();

    private static final Pattern uuidPattern =
            Pattern.compile("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");

    private static String previousToken;

    @BeforeAll
    static void beforeAll() {
        requestHeaders.add("Content-type", "application/json");
    }

    @Test
    @DisplayName("should return success if request that will set new user was sent")
    public void test0001() {
        HttpEntity<String> requestBody = new HttpEntity<>("{\"userName\": \"taro\"}", requestHeaders);
        AuthenticatedUser response = restTemplate.postForObject(
                "http://localhost:" + port + "/api/v1/setToken", requestBody, AuthenticatedUser.class);

        assertEquals("taro", response.getUserName());
        assertTrue(uuidPattern.matcher(response.getToken()).find());
        previousToken = response.getToken();
    }

    @Test
    @DisplayName("should return success if request that will auth a user was sent")
    public void test0002() {
        HttpEntity<String> requestBody = new HttpEntity<>("{\"token\": \"" + previousToken +"\"}", requestHeaders);
        AuthResponse response = restTemplate.postForObject(
                "http://localhost:" + port + "/api/v1/authToken", requestBody, AuthResponse.class);

        assertEquals(0, response.getCode());
        assertEquals("Succeeded in authenticate user.", response.getMessage());
        assertEquals("taro", response.getUserName());
    }
}
