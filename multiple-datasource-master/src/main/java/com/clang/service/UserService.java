package com.clang.service;

import com.clang.domain.User;
import com.clang.exception.NoSuchCityException;
import com.clang.exception.NoSuchUserException;

/**
 * @Author  Clang
 * @Date 2022-03-17
 */
public interface UserService {

    String findByName(String userName) throws NoSuchUserException, NoSuchCityException;
}
