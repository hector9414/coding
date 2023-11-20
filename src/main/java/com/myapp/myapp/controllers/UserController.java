package com.myapp.myapp.controllers;

import com.myapp.myapp.dao.UserDao;
import com.myapp.myapp.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    List<User> getAll(){
        return userDao.getAll();
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User getUser(@PathVariable long id){
        return userDao.getUser(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    User createUser(@RequestBody User user){
        userDao.createUser(user);
        return user;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    void updateUser(@RequestBody User user){
        userDao.updateUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void deleteUser(@PathVariable long id){
        userDao.deleteUser(id);
    }

}
