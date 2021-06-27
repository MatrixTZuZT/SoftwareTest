package com.se.contest.Test;

import com.alibaba.fastjson.JSONObject;
import com.se.contest.ContestApplication;
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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ContestApplication.class} )
@WebAppConfiguration //web应用一定要加！！！
public class SecurityControllerTest {

    private static Logger logger = LoggerFactory.getLogger(SecurityControllerTest.class);

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
    public void login() throws Exception {
        HashMap<String, Object> params = new HashMap<>();
        params.put("userId", "1750226");
        params.put("password", "123456");
        String content = JSONObject.toJSONString(params);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/login")
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
