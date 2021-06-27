package com.se.contest.controller;

import com.se.contest.dao.PageMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Api(tags = "SecurityController")
public class SecurityController {
    @Autowired
    PageMapper pageMapper;

    @PostMapping("/login")
    public Object login(@RequestBody HashMap<String, Object> user) {
        String id = (String)user.get("userId");
        String name = null;
        String password = (String)user.get("password");
        HashMap<String, Object> res = new HashMap<>();
        Integer stateCode = 0;

        List<Map<String, String>> savedPass = pageMapper.getUser(id);
        if(savedPass.size() == 1) {
            String getPass = savedPass.get(0).get("password");
            if(getPass.equals(password) ) {
                name = savedPass.get(0).get("user");
                stateCode = 1;
            }
        }
        res.put("userId", id);
        res.put("name", name);
        res.put("stateCode", stateCode);
        return res;
    }

}
