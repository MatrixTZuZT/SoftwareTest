package com.se.contest.controller;

import com.se.contest.dao.pageContentDAOMapper;
import com.se.contest.model.pageContentDAO;
import com.se.contest.model.questionsDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/PageContentTable")
@Api(tags = "PageContentController")
public class PageContentController {
    @Autowired
    pageContentDAOMapper PageContentDAOMapper;

    //table questions
    @PostMapping("/list")
    public Object list(@RequestBody HashMap<String, Integer> info) {
        HashMap<Object, Object> res = new HashMap<>();
        HashMap<Object, Object> response = new HashMap<>();
        Integer count = info.get("count");
        Integer offset = info.get("offset");
        List<pageContentDAO> sqlRes = PageContentDAOMapper.list(offset, count);
        res.put("data", sqlRes);
        res.put("total", PageContentDAOMapper.tableLen());
        response.put("data", res);
        return response;
    }
    @PostMapping("/create")
    public Object create(@RequestBody pageContentDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            PageContentDAOMapper.insert(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/update")
    public Object update(@RequestBody pageContentDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            PageContentDAOMapper.update(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/remove")
    public Object remove(@RequestBody pageContentDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            PageContentDAOMapper.remove(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }



}
