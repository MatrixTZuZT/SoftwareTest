package com.se.contest.dao;

import com.se.contest.model.usersDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface usersDAOMapper {
    int insert(usersDAO record);

    int insertSelective(usersDAO record);

    void remove(usersDAO record);

    void update(usersDAO record);

    List<usersDAO> list(@Param("offset") int offset, @Param("count") int count);

    int tableLen();
}