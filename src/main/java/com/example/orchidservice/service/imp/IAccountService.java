package com.example.orchidservice.service.imp;

import com.example.orchidservice.pojo.Account;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<Account> getAllAccounts();
    Optional<Account> getAccountById(Integer id);
    Account saveAccount(Account account);
    void deleteAccount(Integer id);
    Optional<Account> getAccountByEmail(String email);
    List<Account> getAccountsByRoleId(Integer roleId);
}
