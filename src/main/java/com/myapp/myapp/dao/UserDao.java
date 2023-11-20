package com.myapp.myapp.dao;

import com.myapp.myapp.models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    User getUser(long id);

    User createUser(User user);

    void updateUser(User user);

    void deleteUser(long id);
}
