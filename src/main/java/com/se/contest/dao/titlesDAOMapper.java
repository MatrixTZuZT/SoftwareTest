package com.se.contest.dao;

import com.se.contest.model.titlesDAO;
import com.se.contest.model.usersDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface titlesDAOMapper {
    int insert(titlesDAO record);

    void remove(titlesDAO record);

    void update(titlesDAO record);

    List<titlesDAO> list(@Param("offset") int offset, @Param("count") int count);

    int tableLen();
}