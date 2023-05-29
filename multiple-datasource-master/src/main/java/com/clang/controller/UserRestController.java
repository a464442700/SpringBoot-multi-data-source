package com.clang.controller;

import com.clang.domain.User;
import com.clang.exception.NoSuchCityException;
import com.clang.exception.NoSuchUserException;
import com.clang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author  Clang
 * @Date 2022-03-17
 */
@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/api/user", method = RequestMethod.GET)
    public String findByName(@RequestParam(value = "userName") String userName) {
        String user = null;
        try {
            user = userService.findByName(userName);
        } catch (NoSuchUserException e) {
            System.err.println(e.toString());
        } catch (NoSuchCityException e) {
            System.err.println(e.toString());
        }

        return user;
    }
}
