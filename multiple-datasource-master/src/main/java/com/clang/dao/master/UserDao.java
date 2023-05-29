package com.clang.dao.master;

import com.clang.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author  Clang
 * @Date 2022-03-17
 */
@Mapper
public interface UserDao {


    String findByName(@Param("userName") String userName);
}
