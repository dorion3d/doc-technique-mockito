package org.example.shop.user;

import org.example.shop.license.License;
import org.example.shop.license.LicenseState;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Exemple de tests qui utilisent la librairie Mockito
 */
public class UserClientTest {
    private static final String GET_USER_URL = "/users/{userId}";
    private final RestTemplate restTemplateMock = mock(RestTemplate.class);
    private final UserClient underTest = new UserClient(restTemplateMock);
    private final UUID userId = UUID.randomUUID();
    private final User user = new User(userId,"First","Last","user123@email.com");

    @Test
    public void whenGetUser_givenValidId_thenReturnUser(){
        when(restTemplateMock.getForObject(GET_USER_URL,User.class,userId)).thenReturn(user);

        final User response = underTest.getUser(userId);

        assertEquals(user, response);
    }

    @Test
    public void whenGetUser_givenUnknownId_thenReturnNull(){
        when(restTemplateMock.getForObject(GET_USER_URL,User.class,userId)).thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        final User response = underTest.getUser(userId);

        assertNull(response);
    }

    @Test
    public void whenGetUser_givenUserServiceError_thenThrowUnknownException(){
        when(restTemplateMock.getForObject(GET_USER_URL,User.class,userId)).thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(UnknownError.class, () -> underTest.getUser(userId));
    }
}