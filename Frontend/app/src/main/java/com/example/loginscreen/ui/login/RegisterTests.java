package com.example.loginscreen.ui.login;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class RegisterTests {

   Register r = null;
   RegisterService service = mock(RegisterService.class);

    @Before
    public void setUp(){
        r = new Register(service);
    }

    @Test
    public void testingUser(){
        when(service.getUser()).thenReturn("cjurenic");
        assertEquals("cjurenic", r.gettingUsername());
        verify(service).getUser();
    }

    @Test
    public void testPassword(){
        when(service.getPass()).thenReturn("123123");
        assertEquals("123123", r.gettingPassword());
        verify(service).getPass();
    }

    @Test
    public void testFirstName(){
        when(service.getFirst()).thenReturn("chandler");
        assertEquals("chandler", r.gettingFirstName());
        verify(service).getFirst();
    }

    @Test
    public void testLastName(){
        when(service.getLast()).thenReturn("jurenic");
        assertEquals("jurenic", r.gettingLastName());
        verify(service).getLast();
    }



}