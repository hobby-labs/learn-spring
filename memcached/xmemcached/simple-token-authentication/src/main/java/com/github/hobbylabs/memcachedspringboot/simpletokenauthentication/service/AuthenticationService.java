package com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.service;

import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthRequest;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthResponse;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.AuthenticatedUser;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.entity.CreateTokenRequest;
import com.github.hobbylabs.memcachedspringboot.simpletokenauthentication.repository.AuthTokenRepositoryImpl;
import lombok.AllArgsConstructor;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthTokenRepositoryImpl authTokenRepositoryImpl;

    /**
     * Authenticate by token.
     * @param authRequest
     * @throws InterruptedException
     * @throws TimeoutException
     * @throws MemcachedException
     */
    public AuthResponse authToken(AuthRequest authRequest) throws Exception {
        String userName = authTokenRepositoryImpl.findBy(authRequest.getToken());

        if (userName == null) {
            // TODO: Should be changed the exception more accurate one.
            throw new Exception("Not authorized");
        }

        AuthResponse response = new AuthResponse();

        response.setCode(0);
        response.setUserName(userName);
        response.setMessage("Succeeded in authenticate user.");

        return response;
    }

    /**
     * Create new token.
     * @param user User that requested to create new token.
     * @return
     * @throws Exception
     */
    public AuthenticatedUser setToken(CreateTokenRequest user) throws Exception {
        String token = UUID.randomUUID().toString();

        boolean result = authTokenRepositoryImpl.setToken(user.getUserName(), token);
        if (!result) {
            throw new Exception("Failed to set token of a user \"" + user.getUserName() + "\"");
        }

        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setUserName(user.getUserName());
        authenticatedUser.setToken(token);

        return authenticatedUser;
    }
}
