package com.clang.service.impl;

import com.clang.dao.cluster.CityDao;
import com.clang.dao.master.UserDao;
import com.clang.domain.City;
import com.clang.domain.User;
import com.clang.exception.NoSuchCityException;
import com.clang.exception.NoSuchUserException;
import com.clang.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @Author  Clang
 * @Date 2022-03-17
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private CityDao cityDao;

    @Override
    public String findByName(String userName) throws
            NoSuchUserException
            , NoSuchCityException {

        String user = userDao.findByName(userName);
        System.out.println(user);
       // City city = cityDao.findByName("温州");

//        if (Objects.isNull(user)) {
//            throw new NoSuchUserException("没有这个用户!");
//        } else if (Objects.isNull(city)) {
//            throw new NoSuchCityException("没有这个城市");
//        } else {
//            user.setCity(city);
//        }

        return user;
    }
}
