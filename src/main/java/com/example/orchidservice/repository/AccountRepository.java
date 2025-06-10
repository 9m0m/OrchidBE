package com.example.orchidservice.repository;

import com.example.orchidservice.pojo.Account;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository {
    List<Account> findAll();
    Optional<Account> findById(Integer id);
    Account save(Account account);
    void deleteById(Integer id);
    Optional<Account> findByEmail(String email);
    List<Account> findByRoleId(Integer roleId);
}
