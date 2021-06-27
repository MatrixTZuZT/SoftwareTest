package com.se.contest.Adversary;

import java.util.Date;

public class User {
    int studentNo;
    int groupNo;
    Date finishDate;
    int score;

    public User(int studentNo){
        this.studentNo = studentNo;
        this.score = 0;
    }

    public void setGroupNo(int groupNo){
        this.groupNo = groupNo;
    }

    public int getGroupNo(){
        return this.groupNo;
    }

    public void setFinishDate(Date finishDate){
        this.finishDate = finishDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return this.score;
    }


}
