package com.se.contest.dao;

import com.se.contest.model.subtitlesDAO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface subtitlesDAOMapper {
    int insert(subtitlesDAO record);

    void remove(subtitlesDAO record);

    void update(subtitlesDAO record);

    List<subtitlesDAO> list(@Param("offset") int offset, @Param("count") int count);

    int tableLen();
}