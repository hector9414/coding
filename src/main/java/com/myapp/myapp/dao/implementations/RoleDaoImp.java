package com.myapp.myapp.dao.implementations;

import com.myapp.myapp.dao.RoleDao;
import com.myapp.myapp.models.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class RoleDaoImp implements RoleDao {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public List<Role> getAll() {
        String hql = "FROM Role as u";
        return (List<Role>) em.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public Role getRole(long id) {
        return em.find(Role.class, id);
    }

    @Transactional
    @Override
    public Role createRole(Role role) {
        em.merge(role);
        return role;
    }

    @Transactional
    @Override
    public void updateRole(Role role) {
        em.merge(role);
    }

    @Transactional
    @Override
    public void deleteRole(long id) {
        Role role = getRole(id);
        em.remove(role);
    }
}
