package com.se.contest.dao;

import com.se.contest.model.pageContentDAO;
import com.se.contest.model.usersDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface pageContentDAOMapper {
    int insert(pageContentDAO record);

    void remove(pageContentDAO record);

    void update(pageContentDAO record);

    List<pageContentDAO> list(@Param("offset") int offset, @Param("count") int count);

    int tableLen();
}