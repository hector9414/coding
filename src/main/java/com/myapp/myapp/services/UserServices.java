package com.myapp.myapp.services;

import com.myapp.myapp.dao.UserDao;
import com.myapp.myapp.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    UserDao userDao;


    public List<User> getAll() {
        return userDao.getAll();
    }

    public User getUser(long id) {
        return userDao.getUser(id);
    }

    public void createUser(User user) {
        String hash = hashGenerator(user.getPassword());
        user.setPassword(hash);
        userDao.createUser(user);
    }

    public void updateUser(User user) {
        userDao.createUser(user);
    }

    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    private String hashGenerator(String password){
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        return argon2.hash(2, 1024,1,password);
    }

    public User login(User dto) {
        return userDao.login(dto);
    }
}
