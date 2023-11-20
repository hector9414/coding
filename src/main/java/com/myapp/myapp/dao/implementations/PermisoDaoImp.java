package com.myapp.myapp.dao.implementations;

import com.myapp.myapp.dao.PermisoDao;
import com.myapp.myapp.models.Permiso;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class PermisoDaoImp implements PermisoDao {
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public List<Permiso> getAll() {
        String hql = "FROM Permiso as u";
        return (List<Permiso>) em.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public Permiso getPermiso(long id) {
        return em.find(Permiso.class, id);
    }

    @Transactional
    @Override
    public Permiso createPermiso(Permiso permiso) {
        em.merge(permiso);
        return permiso;
    }

    @Transactional
    @Override
    public void updatePermiso(Permiso permiso) {
        em.merge(permiso);
    }

    @Transactional
    @Override
    public void deletePermiso(long id) {
        Permiso permiso = getPermiso(id);
        em.remove(permiso);
    }
}
