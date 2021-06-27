package com.se.contest.controller;

import com.se.contest.dao.usersDAOMapper;
import com.se.contest.model.questionsDAO;
import com.se.contest.model.usersDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/UsersTable")
@Api(tags = "UsersController")
public class UsersController {
    @Autowired
    usersDAOMapper UsersDAOMapper;

    @PostMapping("/list")
    public Object list(@RequestBody HashMap<String, Integer> info) {
        HashMap<Object, Object> res = new HashMap<>();
        HashMap<Object, Object> response = new HashMap<>();
        Integer count = info.get("count");
        Integer offset = info.get("offset");
        List<usersDAO> sqlRes = UsersDAOMapper.list(offset, count);
        res.put("data", sqlRes);
        res.put("total", UsersDAOMapper.tableLen());
        response.put("data", res);
        return response;
    }

    @PostMapping("/create")
    public Object create(@RequestBody usersDAO user) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            return UsersDAOMapper.insertSelective(user);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/update")
    public Object update(@RequestBody usersDAO user) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            UsersDAOMapper.update(user);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/remove")
    public Object remove(@RequestBody usersDAO user) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            UsersDAOMapper.remove(user);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }
}
