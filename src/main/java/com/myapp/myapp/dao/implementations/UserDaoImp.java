package com.myapp.myapp.dao.implementations;

import com.myapp.myapp.dao.UserDao;
import com.myapp.myapp.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager em;

    @Transactional
    @Override
    public List<User> getAll() {
        String hql = "FROM User as u";
        return (List<User>) em.createQuery(hql).getResultList();
    }

    @Transactional
    @Override
    public User getUser(long id) {
        return em.find(User.class, id);
    }

    @Transactional
    @Override
    public User createUser(User user) {
        em.merge(user);
        return user;
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        em.merge(user);
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        User user = getUser(id);
        em.remove(user);
    }
}
