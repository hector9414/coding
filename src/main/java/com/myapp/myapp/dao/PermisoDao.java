package com.myapp.myapp.dao;

import com.myapp.myapp.models.Permiso;

import java.util.List;

public interface PermisoDao {
    List<Permiso> getAll();

    Permiso getPermiso(long id);

    Permiso createPermiso(Permiso permiso);

    void updatePermiso(Permiso permiso);

    void deletePermiso(long id);
}
