package com.example.orchidservice.service.imp;

import com.example.orchidservice.pojo.Role;
import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<Role> getAllRoles();
    Optional<Role> getRoleById(Integer id);
    Role saveRole(Role role);
    void deleteRole(Integer id);
    Optional<Role> getRoleByName(String roleName);
}
