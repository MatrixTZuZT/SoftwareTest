package com.se.contest.controller;

import com.se.contest.dao.subtitlesDAOMapper;
import com.se.contest.model.subtitlesDAO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/SubtitlesTable")
@Api(tags = "SubtitlesController")
public class SubtitlesController {
    @Autowired
    subtitlesDAOMapper SubtitlesDAOMapper;

    @PostMapping("/list")
    public Object list(@RequestBody HashMap<String, Integer> info) {
        HashMap<Object, Object> res = new HashMap<>();
        HashMap<Object, Object> response = new HashMap<>();
        Integer count = info.get("count");
        Integer offset = info.get("offset");
        List<subtitlesDAO> sqlRes = SubtitlesDAOMapper.list(offset, count);
        res.put("data", sqlRes);
        res.put("total", SubtitlesDAOMapper.tableLen());
        response.put("data", res);
        return response;
    }

    @PostMapping("/create")
    public Object create(@RequestBody subtitlesDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            SubtitlesDAOMapper.insert(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/update")
    public Object update(@RequestBody subtitlesDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            SubtitlesDAOMapper.update(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }

    @PostMapping("/remove")
    public Object remove(@RequestBody subtitlesDAO info) {
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;
        try {
            SubtitlesDAOMapper.remove(info);
        } catch (Exception e) {
            stateCode = 1;
        } finally {
            res.put("code", stateCode);
        }
        return res;
    }
}
