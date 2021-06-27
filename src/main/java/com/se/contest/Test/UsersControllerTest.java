package com.se.contest.Test;

import com.se.contest.ContestApplication;
import com.se.contest.dao.PageMapper;
import com.se.contest.dao.usersDAOMapper;
import com.se.contest.model.usersDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ContestApplication.class} )
@WebAppConfiguration //web应用一定要加！！！
@Rollback(value = true)
public class UsersControllerTest {

    private static Logger logger = LoggerFactory.getLogger(UsersControllerTest.class);

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Autowired
    private usersDAOMapper UsersDAOMapper;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    public static String reencode(String src) throws UnsupportedEncodingException {
        return new String(src.getBytes("iso-8859-1"),"utf-8");
    }

    @Test
    public void list() throws Exception {
        usersDAO user = new usersDAO();

        HashMap<String, Object> attr = new HashMap<>();
        attr.put("count", 3);
        attr.put("offset", 1);
        String content = JSONObject.toJSONString(attr);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/UsersTable/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andExpect(status().isOk()).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void create() throws Exception {
        usersDAO user = new usersDAO();

        user.setUser("小明");
        user.setUserIdNum("1234567");
        user.setPassword("123456");
        String content = JSONObject.toJSONString(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/UsersTable/create")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andExpect(status().isOk()).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void update() throws Exception {
        usersDAO user = new usersDAO();

        user.setUser("小明");
        user.setId(211);
        user.setUserIdNum("1234567");
        user.setPassword("654321");
        String content = JSONObject.toJSONString(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/UsersTable/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andExpect(status().isOk()).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    public void remove() throws Exception {
        usersDAO user = new usersDAO();

        user.setUser("小明");
        user.setId(211);
        user.setUserIdNum("1234567");
        user.setPassword("654321");
        String content = JSONObject.toJSONString(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/UsersTable/remove")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //需要指定 只对post请求有效
                .content(content);
        MvcResult mvcResult = mockMvc.perform((requestBuilder)).andExpect(status().isOk()).andReturn();
        String stringResult = mvcResult.getResponse().getContentAsString();
        System.out.println("=============================");
        System.out.println(reencode(stringResult));
        System.out.println("=============================");
//        JSONAssert.assertEquals(expected,mvcResult.getResponse().getContentAsString(),false);
    }
}
