package com.se.contest.controller;

import com.se.contest.dao.PageMapper;
import io.swagger.annotations.Api;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Api(tags = "PageController")
public class PageController {
    @Autowired
    PageMapper pageMapper;

    private static String changeCharSet(
            String str, String newCharset) throws UnsupportedEncodingException {
        if (str != null) {
            // 用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            // 用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return str;
    }

    @GetMapping("/getContents")
    public Object getContents(String title, String subtitle) throws UnsupportedEncodingException {
        List<Map<String, String>> result = pageMapper.getContents(title, subtitle);
//        List<Map<String, String>> result = pageMapper.getQuestions(Arrays.asList(1));
        if(result.size() > 0) {
            return result.get(0);
        } else {
            return -1;
        }
    }
    @GetMapping("/getSubtitles")
    public Object getSubtitles(String title) {
        List<Map<String, String>> result = pageMapper.getSubtitle(title);
        return result;
    }



}
