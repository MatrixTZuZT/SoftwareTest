package com.se.contest.controller;

import com.se.contest.dao.gradeDAOMapper;
import com.se.contest.model.gradeDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/GradeTable")
@Api(tags = "GradeController")
public class GradeController {
    @Autowired
    gradeDAOMapper GradeDAOMapper;

    @PostMapping("/list")
    public Object list(@RequestBody HashMap<String, Integer> info) {
        HashMap<Object, Object> res = new HashMap<>();
        HashMap<Object, Object> response = new HashMap<>();
        Integer count = info.get("count");
        Integer offset = info.get("offset");

        List<gradeDAO> sqlRes = GradeDAOMapper.list(offset, count);
        res.put("data", sqlRes);
        res.put("total", GradeDAOMapper.tableLen());
        response.put("data", res);
        return response;
    }

    @PostMapping("/create")
    public Object create(@RequestBody gradeDAO grade) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            GradeDAOMapper.insertSelective(grade);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/update")
    public Object update(@RequestBody gradeDAO grade) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            GradeDAOMapper.update(grade);
        } catch (Exception e) {
            e.printStackTrace();
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/remove")
    public Object remove(@RequestBody gradeDAO grade) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            GradeDAOMapper.deleteByPrimaryKey(grade);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }
}
