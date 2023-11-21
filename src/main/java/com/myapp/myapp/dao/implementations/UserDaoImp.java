package com.myapp.myapp.dao.implementations;

import com.myapp.myapp.dao.UserDao;
import com.myapp.myapp.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
    public void createUser(User user) {
        em.merge(user);
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

    @Override
    public User login(User dto) {
        boolean isAuth = false;

        String hql = "FROM User as u WHERE u.password is not null and u.email = :email";
        List<User> result = em.createQuery(hql)
                .setParameter("email", dto.getEmail())
                .getResultList();


        if(result.isEmpty()){
            return null;
        }

        User user = result.get(0);


        if(StringUtils.hasText(dto.getPassword())){
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            isAuth = argon2.verify(user.getPassword(), dto.getPassword());
        }

        if(isAuth){
            return user;
        }
        return null;
    }
}
