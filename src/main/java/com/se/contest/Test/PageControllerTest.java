package com.se.contest.Test;

import com.se.contest.ContestApplication;
import com.se.contest.controller.PageController;
import com.se.contest.dao.PageMapper;
import com.se.contest.dao.titlesDAOMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;

@RunWith(SpringJUnit4ClassRunner.class)
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ContestApplication.class},webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@SpringBootTest(classes = {ContestApplication.class} )
@WebAppConfiguration //web应用一定要加！！！
//@WebMvcTest(value = PageController.class)
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ContextConfiguration("classpath:application.yml")
public class PageControllerTest {

    private static Logger logger = LoggerFactory.getLogger(PageControllerTest.class);

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private PageMapper pageMapper;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    public static String reencode(String src) throws UnsupportedEncodingException {
        return new String(src.getBytes("iso-8859-1"),"utf-8");
    }

    @Test
    public void getContents() throws Exception {
        String title = "教学内容";
        String subtitle = "教学日历";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getContents")
                .param("title", title)
                .param("subtitle", subtitle);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void getSubtitles() throws Exception {
        String title = "教学内容";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/getSubtitles")
                .param("title", title);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

}
