package com.myapp.myapp.controllers;

import com.myapp.myapp.models.User;
import com.myapp.myapp.services.UserServices;
import com.myapp.myapp.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    JWTUtil jwtUtil;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getAll() {
        return userServices.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable long id) {
        return userServices.getUser(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void createUser(@RequestBody User user) {
        userServices.createUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void updateUser(@RequestBody User user) {
        userServices.updateUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable long id) {
        userServices.deleteUser(id);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    Map<String, Object> login(@RequestBody User dto) {
        //ok
        User user = userServices.login(dto);

        Map<String, Object> result = new HashMap<>();
        if (user != null) {
            String token = jwtUtil.createJWT(String.valueOf(user.getId()), user.getEmail());

            result.put("token", token);
            result.put("user", user);
        }

        return result;
    }

}
