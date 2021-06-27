package com.se.contest.model;

public class usersDAO {
    private String user;

    private String password;

    private String userIdNum;

    private Integer id;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user == null ? null : user.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUserIdNum() {
        return userIdNum;
    }

    public void setUserIdNum(String userIdNum) {
        this.userIdNum = userIdNum == null ? null : userIdNum.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id;}
}