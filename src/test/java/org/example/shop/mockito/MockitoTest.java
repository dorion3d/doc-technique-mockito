package org.example.shop.mockito;

import org.example.shop.license.License;
import org.example.shop.license.LicenseService;
import org.example.shop.license.LicenseState;
import org.example.shop.user.User;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.MissingMethodInvocationException;
import org.mockito.exceptions.misusing.NotAMockException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MockitoTest {

    @Test
    public void helloWorld() {
        LinkedList<String> mockedList = mock(LinkedList.class);
        when(mockedList.get(0)).thenReturn("first");
        assertEquals("first", mockedList.get(0));
    }

    @Test
    public void whenVerify_givenNotMock_thenThrowException(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        assertThrows(NotAMockException.class, () -> {
            verify(list).add(10);
        });
    }

    @Test
    public void whenVerify_givenMock_thenVerify(){
        ArrayList<Integer> list = mock(ArrayList.class);
        list.add(10);
        verify(list).add(10);
    }

    @Test
    public void whenWhen_givenNoThenReturn_thenThrowException(){
        ArrayList<Integer> list = mock(ArrayList.class);
        assertThrows(MissingMethodInvocationException.class, ()->{
            when(list);
        });
    }

    @Test
    public void whenThenAnswerExample(){
        LicenseService licenseServiceMock = mock(LicenseService.class);
        when(licenseServiceMock.licenseUser(any(),any()))
                .thenAnswer(call-> new License(UUID.randomUUID(), call.getArgument(0), LicenseState.ENABLED));
        License result = licenseServiceMock.licenseUser(UUID.randomUUID(), UUID.randomUUID());
    }

    @Test
    public void anyExample(){
        ArrayList<Integer> list = mock(ArrayList.class);
        when(list.add(any())).thenReturn(false);
    }

    @Test
    public void eqExample(){
        ArrayList<String> list = mock(ArrayList.class);
        when(list.add(eq("test"))).thenReturn(false);
        assertFalse(list.add("test"));

        final User user = new User(UUID.randomUUID(),"First","Last","user123@email.com");
        ArrayList<User> lists = mock(ArrayList.class);
        when(lists.add(user)).thenReturn(false);
        assertFalse(lists.add(user));
    }

    @Test
    public void mockTest() {
        ArrayList<ArrayList<String>> list1 = mock(ArrayList.class);
        ArrayList<String> list2 = mock(ArrayList.class);
        when(list1.get(0)).thenReturn(list2);
        when(list2.get(0)).thenReturn("get");

        String result = list1.get(0).get(0);

        assertEquals("get", result);
        verify(list1, times(1)).get(0);
        verify(list2, times(1)).get(0);
    }
}
