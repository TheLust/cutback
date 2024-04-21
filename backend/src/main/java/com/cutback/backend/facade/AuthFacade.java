package com.cutback.backend.facade;

import com.cutback.backend.dto.request.LoginRequest;
import com.cutback.backend.dto.request.RegisterRequest;
import com.cutback.backend.exception.AuthenticationException;
import com.cutback.backend.mapper.GenericMapper;
import com.cutback.backend.model.Account;
import com.cutback.backend.model.Role;
import com.cutback.backend.security.AccountDetails;
import com.cutback.backend.security.JwtUtils;
import com.cutback.backend.service.AccountService;
import com.cutback.backend.validator.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
public class AuthFacade {

    private final AccountService accountService;
    private final GenericMapper genericMapper;
    private final AccountValidator accountValidator;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;

    public String register(@RequestBody RegisterRequest registerRequest,
                                                  BindingResult bindingResult) {
        Account account = genericMapper.toResource(registerRequest);
        account.setRole(Role.USER);
        account.setEnabled(true);

        accountValidator.validate(account, bindingResult);

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        return jwtUtils.generateToken(accountService.insert(account));
    }

    public String login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        try {
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            AccountDetails accountDetails = (AccountDetails) auth.getPrincipal();
            return jwtUtils.generateToken(accountDetails.getAccount());

        } catch (BadCredentialsException e) {
            throw new AuthenticationException(AuthenticationException.BAD_CREDENTIALS);
        }
    }
}
