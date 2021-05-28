package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.controller;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthResponse;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthenticatedUser;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
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

/**
 * This test cases requires running 3 memcached process at localhost.
 * You can launch them by running docker-compose at root in this project folder like below.
 *
 *   $ docker-compose up
 *
 */
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

    /** Regex of UUID */
    private static final Pattern uuidPattern =
            Pattern.compile("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})");

    /** Variable to store auth token the obtained previously */
    private static String previousToken;

    @BeforeAll
    static void beforeAll() {
        requestHeaders.add("Content-type", "application/json");
    }

    @Test
    @Order(1)
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
    @Order(1)
    @DisplayName("should return success if request that will auth a user was sent")
    public void test0002() {
        HttpEntity<String> requestBody = new HttpEntity<>("{\"token\": \"" + previousToken +"\"}", requestHeaders);
        AuthResponse response = restTemplate.postForObject(
                "http://localhost:" + port + "/api/v1/authToken", requestBody, AuthResponse.class);

        assertEquals(0, response.getCode());
        assertEquals("Succeeded in authenticate user.", response.getMessage());
        assertEquals("taro", response.getUserName());
    }

    @Test
    @DisplayName("should return error if request contains wrong token")
    public void error0001() {
        HttpEntity<String> requestBody = new HttpEntity<>("{\"token\": \"this-is-wrong-token\"}", requestHeaders);
        String response = restTemplate.postForObject(
                "http://localhost:" + port + "/api/v1/authToken", requestBody, String.class);

        // TODO: Assert error response
        System.out.println(response);
    }
}
