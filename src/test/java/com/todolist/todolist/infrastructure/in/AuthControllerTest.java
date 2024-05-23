package com.todolist.todolist.infrastructure.in;

import com.todolist.todolist.infrastructure.configuration.JwtUtils;
import com.todolist.todolist.infrastructure.configuration.UserDetailsImpl;
import com.todolist.todolist.infrastructure.dto.LoginRequest;
import com.todolist.todolist.infrastructure.in.controllers.AuthController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarOkComLoginComSucesso() {
        LoginRequest request = new LoginRequest("test@test.com", "password");
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        ResponseCookie cookie = ResponseCookie.from("jwt", "token").build();
        when(jwtUtils.gerarJWTCookie(any(UserDetailsImpl.class))).thenReturn(cookie);
        var response = controller.login(request);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deveRetornarOkComLogoutComSucesso() {
        ResponseCookie cookie = ResponseCookie.from("jwt", "").build();
        when(jwtUtils.limparJWTCookie()).thenReturn(cookie);
        var response = controller.logout(null, null);
        assertEquals(200, response.getStatusCodeValue());
    }
}
