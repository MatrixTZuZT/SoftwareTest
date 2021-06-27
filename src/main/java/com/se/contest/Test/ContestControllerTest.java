package com.se.contest.Test;

import com.alibaba.fastjson.JSONObject;
import com.se.contest.ContestApplication;
import com.se.contest.dao.PageMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ContestApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@SpringBootTest(classes = {ContestApplication.class} )
@WebAppConfiguration //web应用一定要加！！！
//@WebMvcTest(value = PageController.class)
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ContextConfiguration("classpath:application.yml")
public class ContestControllerTest {

    private static Logger logger = LoggerFactory.getLogger(ContestControllerTest.class);

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    public static String reencode(String src) throws UnsupportedEncodingException {
        return new String(src.getBytes("iso-8859-1"),"utf-8");
    }

    @Test
    public void start() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        List<Integer> chapters = Arrays.asList(1);
        params.put("chapter", chapters);
        params.put("questionNums", 6);
        String content = JSONObject.toJSONString(params);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/start")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void getQuestions() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getQuestions")
                .contentType(MediaType.APPLICATION_JSON_UTF8); //需要指定 只对post请求有效
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void getAnswers() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        List<Integer> chapters = Arrays.asList(1);
        params.put("id", "1234560987");
        params.put("name", "测试人");
        params.put("score", 100);
        String content = JSONObject.toJSONString(params);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/getAnswers")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void append() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        List<Integer> chapters = Arrays.asList(1);
        params.put("userId", "1");
        String content = JSONObject.toJSONString(params);

//        start();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/append")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    public void appendUtil(String id) throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        List<Integer> chapters = Arrays.asList(1);
        params.put("userId", id);
        String content = JSONObject.toJSONString(params);

//        start();
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/append")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
    }
    @Test
    public void match() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        List<Integer> chapters = Arrays.asList(1);
        params.put("userId", "1");
        String content = JSONObject.toJSONString(params);

        start();
        appendUtil("1");
        appendUtil("2");
        appendUtil("3");
        appendUtil("4");
        appendUtil("5");
        appendUtil("6");
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/match")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void getGrade() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        List<String> ids = Arrays.asList("1818181");
        params.put("userId", ids);
        String content = JSONObject.toJSONString(params);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/getGrade")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }
}
