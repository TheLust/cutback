package com.cutback.backend.validator;

import com.cutback.backend.model.Account;
import com.cutback.backend.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountValidator implements Validator {

    private final AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        GenericValidator.validate(account, errors);
        GenericValidator.unique(errors, "username", accountService.findByUsername(account.getUsername()));
        GenericValidator.unique(errors, "email", accountService.findByEmail(account.getEmail()));
        GenericValidator.unique(errors, "phoneNumber", accountService.findByPhoneNumber(account.getPhoneNumber()));

        GenericValidator.throwValidationException(errors);
    }
}
