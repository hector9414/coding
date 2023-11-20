package com.myapp.myapp.dao;

import com.myapp.myapp.models.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAll();

    Role getRole(long id);

    Role createRole(Role role);

    void updateRole(Role role);

    void deleteRole(long id);
}
