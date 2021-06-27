package com.se.contest.controller;

import com.se.contest.dao.titlesDAOMapper;
import com.se.contest.model.titlesDAO;
import com.se.contest.model.usersDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/TitlesTable")
@Api(tags = "TitlesController")
public class TitlesController {
    @Autowired
    titlesDAOMapper TitlesDAOMapper;

    @PostMapping("/list")
    public Object list(@RequestBody HashMap<String, Integer> info) {
        HashMap<Object, Object> res = new HashMap<>();
        HashMap<Object, Object> response = new HashMap<>();
        Integer count = info.get("count");
        Integer offset = info.get("offset");
        List<titlesDAO> sqlRes = TitlesDAOMapper.list(offset, count);
        res.put("data", sqlRes);
        res.put("total", TitlesDAOMapper.tableLen());
        response.put("data", res);
        return response;
    }

    @PostMapping("/create")
    public Object create(@RequestBody titlesDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            TitlesDAOMapper.insert(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/update")
    public Object update(@RequestBody titlesDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            TitlesDAOMapper.update(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/remove")
    public Object remove(@RequestBody titlesDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            TitlesDAOMapper.remove(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }
}
