package com.se.contest.Adversary;

public class Score {
    int studentNo;
    int score;

    public Score(int studentNo){
        this.studentNo = studentNo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }
}
