package org.example.shop.user;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class UserClient {
    private static final String GET_USER_URL = "/users/{userId}";
    private static final String SYNC_USER_URL = "/users/{userId}/sync";

    private final RestTemplate restTemplate;

    public UserClient(@Qualifier("userClientRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User getUser(UUID id){
        try {
            return restTemplate.getForObject(GET_USER_URL, User.class, id);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            }
            throw new UnknownError("Could not retrieve user");
        } catch (Exception e) {
            throw new UnknownError("Could not retrieve user");
        }
    }

    public void syncUser(User user){
        try {
            restTemplate.put(SYNC_USER_URL, user);
        } catch (HttpClientErrorException e) {
            throw new UnknownError("Could not sync user");
        }
    }
}
