package com.cutback.backend.service;

import com.cutback.backend.exception.NotFoundException;
import com.cutback.backend.model.Account;
import com.cutback.backend.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Account", "id", id.toString()));
    }

    public Optional<Account> findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Optional<Account> findByPhoneNumber(String phoneNumber) {
        return accountRepository.findByPhoneNumber(phoneNumber);
    }

    public Account insert(Account account) {
        return accountRepository.save(account);
    }

    public Account update(Account current, Account updated) {
        BeanUtils.copyProperties(updated, current,
                "id");
        return accountRepository.save(current);
    }

    public Account delete(Account account) {
        accountRepository.delete(account);
        return account;
    }
}
