package com.myapp.myapp.dao;

import com.myapp.myapp.models.User;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getUser(long id);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(long id);

    User login(User user);
}
