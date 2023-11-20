package com.myapp.myapp.controllers;

import com.myapp.myapp.dao.RoleDao;
import com.myapp.myapp.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    RoleDao roleDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Role> getAll(){
        return roleDao.getAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Role getRole(@PathVariable long id){
        return roleDao.getRole(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    Role createRole(@RequestBody Role role){
        roleDao.createRole(role);
        return role;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    void updateRole(@RequestBody Role role){
        roleDao.updateRole(role);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteRole(@PathVariable long id){
        roleDao.deleteRole(id);
    }
}
