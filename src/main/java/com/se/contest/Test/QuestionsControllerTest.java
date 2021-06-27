package com.se.contest.Test;

import com.alibaba.fastjson.JSONObject;
import com.se.contest.ContestApplication;
import com.se.contest.dao.usersDAOMapper;
import com.se.contest.model.questionsDAO;
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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ContestApplication.class} )
@WebAppConfiguration //web应用一定要加！！！
@Rollback(value = true)
public class QuestionsControllerTest {

    private static Logger logger = LoggerFactory.getLogger(QuestionsControllerTest.class);

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
        HashMap<String, Object> attr = new HashMap<>();
        attr.put("count", 3);
        attr.put("offset", 1);
        String content = JSONObject.toJSONString(attr);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/QuestionsTable/list")
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
        questionsDAO question = new questionsDAO();

        question.setQuestion("这是一个问题");
        question.setA("问题1");
        question.setB("问题2");
        question.setC("问题3");
        question.setD("问题4");
        question.setAnswer("C");
        question.setChapter("3");
        question.setMultiple("no");
        String content = JSONObject.toJSONString(question);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/QuestionsTable/create")
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
        questionsDAO question = new questionsDAO();

        question.setQuestion("这是一个改过的问题");
        question.setId(14);
        question.setA("问题1");
        question.setB("问题2");
        question.setC("问题3");
        question.setD("问题4");
        question.setAnswer("B");
        question.setChapter("3");
        question.setMultiple("no");
        String content = JSONObject.toJSONString(question);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/QuestionsTable/update")
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
        questionsDAO question = new questionsDAO();

        question.setQuestion("这是一个改过的问题");
        question.setId(14);
        question.setA("问题1");
        question.setB("问题2");
        question.setC("问题3");
        question.setD("问题4");
        question.setAnswer("B");
        question.setChapter("3");
        question.setMultiple("no");
        String content = JSONObject.toJSONString(question);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/QuestionsTable/remove")
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
