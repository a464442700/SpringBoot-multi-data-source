package com.clang.dao.cluster;

import com.clang.domain.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author  Clang
 * @Date 2022-03-17
 */
@Mapper
public interface CityDao {

    City findByName(@Param("cityName") String cityName);
}
