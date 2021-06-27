package com.se.contest.controller;

import com.se.contest.Adversary.Constant;
import com.se.contest.Adversary.Match;
import com.se.contest.dao.PageMapper;
import com.se.contest.dao.gradeDAOMapper;
import com.se.contest.utils.MD5Util;
import com.se.contest.model.gradeDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Api(tags = "ContestController")
public class ContestController {
    @Autowired
    PageMapper pageMapper;
    @Autowired
    gradeDAOMapper GradeDAOMapper;

    long current = System.currentTimeMillis();
    Match match;
    ArrayList<Integer> chapters = new ArrayList<>(Arrays.asList(1));
    HashMap<String, Integer> scores = new HashMap<>();
    Integer questionNums = 10;
    static Integer TOTAL_VAL = 100;

    @PostMapping("/start")
    public Object start(@RequestBody HashMap<String, Object> param) {
        HashMap<String, Integer> res = new HashMap<>();
        if(match == null || match.getState() == Thread.State.TERMINATED) {
            current = System.currentTimeMillis();
            match = new Match(current + Constant.INTERVAL);
            match.start();
            if (param.get("chapter") != null) {
                chapters = (ArrayList<Integer>) param.get("chapter");
            }
            if (param.get("questionNums") != null) {
                questionNums = (Integer) param.get("questionNums");
            }
            res.put("stateCode", 1);
        }
        else
            res.put("stateCode", 0);
        return res;
    }

    @PostMapping("/stop")
    public void stop() {
        match = null;
    }

    @GetMapping("/getQuestions")
    public Object getQuestions() {
        List<Map<String, String>> sql_res = pageMapper.getQuestions(chapters);
        int qn = Math.min(questionNums, sql_res.size());
        int[] randQ = randomArray(0, sql_res.size() - 1, qn);
        int restVal = TOTAL_VAL;

        ArrayList<Object> response = new ArrayList<>();
        int eachVal = Math.floorDiv(100, qn);

        for(int i = 0; i < qn; i ++) {
            int nth = randQ[i];
            Map<String, String> q = sql_res.get(nth);

            HashMap<Object, Object> question1 = new HashMap<>();
            HashMap<Object, Object> choice1 = new HashMap<>();
            HashMap<Object, Object> choice2 = new HashMap<>();
            HashMap<Object, Object> choice3 = new HashMap<>();
            HashMap<Object, Object> choice4 = new HashMap<>();

//            String[] c = all_choices.get(i).split(",");

            choice1.put("marker", "A");
            choice1.put("content", q.get("A"));
            choice2.put("marker", "B");
            choice2.put("content", q.get("B"));
            choice3.put("marker", "C");
            choice3.put("content", q.get("C"));
            choice4.put("marker", "D");
            choice4.put("content", q.get("D"));

            ArrayList<HashMap<Object, Object>> choices = new ArrayList<>();

            choices.add(choice1);
            choices.add(choice2);
            choices.add(choice3);
            choices.add(choice4);

            for(int k = 0; k < choices.size(); k ++) {
                String content = (String)(choices.get(k)).get("content");
                if(content.equals("null")) {
                    choices.remove(k);
                }
            }

            question1.put("question", q.get("question"));
            question1.put("id", new String(String.valueOf(i+1)));
            if(q.get("multiple").equals("yes")) question1.put("single", "false");
            else question1.put("single", "true");
            question1.put("answerList", choices);

            //放入答案
            String answers = q.get("answer");
            answers = MD5Util.getMD5(answers);
            question1.put("answers", answers);

            //放入分值
            question1.put("val", i==qn-1? restVal : eachVal);
            restVal -= eachVal;

            //放入章节信息
            question1.put("chapter", q.get("chapter"));

            //放入一道题
            response.add(question1);
        }
        return response;
    }

    @PostMapping("/getAnswers")
    public Object getAnswers(@RequestBody HashMap<String, Object> answers) {
        HashMap<String, Integer> state = new HashMap<>();
        Integer stateCode = 1;
        try {
            String id = (String) answers.get("id");
            String name = (String) answers.get("name");
            Integer score = (Integer) answers.get("score");
            Date now = new Date();

            gradeDAO grade = new gradeDAO();
            grade.setScore(score);
            grade.setTime(now);
            grade.setUserid(id);

            GradeDAOMapper.insert(grade);
        } catch (Exception e) {
            stateCode = 0;
        }
        state.put("state", stateCode);
        return state;
    }

    @PostMapping("/append")
    public Object append(@RequestBody HashMap<String, Object> student) {
        if(match == null) return 0;
        String id = (String) student.get("userId");
        Boolean state = (Boolean) match.append(Integer.valueOf(id));
        HashMap<String, Integer> res = new HashMap<>();
        res.put("stateCode", 1);
//        System.out.println(match.attendingSum);
        return res;
    }

    @PostMapping("/match")
    public Object match(@RequestBody HashMap<String, Object> student) {
        if(match == null) return null;
        String id = (String) student.get("userId");
        HashMap<String, Integer> group = new HashMap<>();
        Integer groupNum = 0;
        if (id == null) groupNum = 0;
        groupNum = match.getGroupNo(Integer.valueOf(id));
        group.put("group", groupNum);
//        System.out.println("match:"+group);
        return group;
    }

    @PostMapping("/getGrade")
    public Object getGrade(@RequestBody HashMap<String, Object> userId) {
        List<String> idList = (ArrayList<String>)userId.get("userId");

        try {
            List<Map<String,String>> users = pageMapper.selectGrade(idList);
            return users;
        } catch (Exception e) {
            return null;
        }
    }

    public static int[] randomArray(int min, int max, int n) {
        int len = max - min + 1;
        if(max < min || n > len) {
            return null;
        }

        int[] source = new int[len];
        for(int i = min; i < min + len; i ++) {
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for(int i = 0; i < result.length; i ++) {
            index = Math.abs(rd.nextInt() % len--);
            result[i] = source[index];
            source[index] = source[len];
        }
        return result;
    }
}
