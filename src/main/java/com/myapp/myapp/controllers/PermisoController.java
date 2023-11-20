package com.myapp.myapp.controllers;

import com.myapp.myapp.dao.PermisoDao;
import com.myapp.myapp.models.Permiso;
import com.myapp.myapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("permiso")
public class PermisoController {

    @Autowired
    PermisoDao permisoDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<Permiso> getAll(){
        return permisoDao.getAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    Permiso getPermiso(@PathVariable long id){
        return permisoDao.getPermiso(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    Permiso createPermiso(@RequestBody Permiso permiso){
        permisoDao.createPermiso(permiso);
        return permiso;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    void updatePermiso(@RequestBody Permiso permiso){
        permisoDao.updatePermiso(permiso);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deletePermiso(@PathVariable long id){
        permisoDao.deletePermiso(id);
    }
}
