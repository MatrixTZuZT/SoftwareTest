package com.se.contest.controller;

import com.se.contest.dao.questionsDAOMapper;
import com.se.contest.model.questionsDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/QuestionsTable")
@Api(tags = "QusetionsController")
public class QuestionsController {
    @Autowired
    questionsDAOMapper QuestionsDAOMapper;

    //table questions
    @PostMapping("/list")
    public Object list(@RequestBody HashMap<String, Integer> info) {
        HashMap<Object, Object> res = new HashMap<>();
        HashMap<Object, Object> response = new HashMap<>();
        Integer count = info.get("count");
        Integer offset = info.get("offset");
        List<questionsDAO> sqlRes = QuestionsDAOMapper.select(offset, count);
        res.put("data", sqlRes);
        res.put("total", QuestionsDAOMapper.tableLen());
        response.put("data", res);
        return response;
    }
    @PostMapping("/create")
    public Object create(@RequestBody questionsDAO question) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            QuestionsDAOMapper.insert(question);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/update")
    public Object update(@RequestBody questionsDAO question) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            QuestionsDAOMapper.updateByPrimaryKey(question);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/remove")
    public Object remove(@RequestBody questionsDAO question) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            QuestionsDAOMapper.remove(question);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }



}
