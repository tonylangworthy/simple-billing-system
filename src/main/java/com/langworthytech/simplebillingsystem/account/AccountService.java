package com.langworthytech.simplebillingsystem.account;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Optional<Account> findAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
}
