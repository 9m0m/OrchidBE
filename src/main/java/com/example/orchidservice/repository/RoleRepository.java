package com.example.orchidservice.repository;

import com.example.orchidservice.pojo.Role;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository {
    List<Role> findAll();
    Optional<Role> findById(Integer id);
    Role save(Role role);
    void deleteById(Integer id);
    Optional<Role> findByRoleName(String roleName);
}
