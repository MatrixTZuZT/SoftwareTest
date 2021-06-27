package com.se.contest.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Mapper
public interface PageMapper {
    List<Map<String, String>> selectGrade(@Param("c") List<String> c);
    List<Map<String, String>> getContents(@Param("title") String title, @Param("subtitle") String subtitle);
    List<Map<String, String>> getSubtitle(@Param("title") String title);
    List<Map<String, String>> getUser(@Param("id") String id);
    List<Map<String, String>> getQuestions(@Param("c") List<Integer> c);
}
