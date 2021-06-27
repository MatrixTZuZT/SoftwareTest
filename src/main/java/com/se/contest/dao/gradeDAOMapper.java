package com.se.contest.dao;

import com.se.contest.model.gradeDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface gradeDAOMapper {
    int deleteByPrimaryKey(gradeDAO key);

    int insert(gradeDAO record);

    int insertSelective(gradeDAO record);

    List<gradeDAO> list(@Param("offset") int offset, @Param("count") int count);

    int update(gradeDAO record);

    int tableLen();

}