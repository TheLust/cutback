package com.cutback.backend.controller;

import com.cutback.backend.dto.request.LoginRequest;
import com.cutback.backend.dto.request.RegisterRequest;
import com.cutback.backend.facade.AuthFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.url.base}/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/register")
    public ResponseEntity<String> registerAccount(@RequestBody RegisterRequest registerRequest,
                                                  BindingResult bindingResult) {
        return new ResponseEntity<>(
                authFacade.register(registerRequest, bindingResult),
                HttpStatus.OK
        );
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return new ResponseEntity<>(
                authFacade.login(loginRequest),
                HttpStatus.OK
        );
    }
}
