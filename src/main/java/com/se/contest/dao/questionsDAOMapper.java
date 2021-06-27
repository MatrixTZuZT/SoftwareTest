package com.se.contest.dao;

import com.se.contest.model.questionsDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface questionsDAOMapper {
    int remove(questionsDAO record);

    int insert(questionsDAO record);

    int insertSelective(questionsDAO record);

    List<questionsDAO> select(@Param("offset") int offset, @Param("count") int count);

    int updateByPrimaryKey(questionsDAO record);

    int tableLen();
}