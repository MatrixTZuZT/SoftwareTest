package com.se.contest.Adversary;

import java.util.*;

public class Group {
    List<User> member = new ArrayList<User>();
    int groupSize=0;

    public void add(User user){
        member.add(user);
        groupSize++;
    }

    public void setGroupSize(int groupSize){
        this.groupSize = groupSize;
    }

    public int getGroupSize(){
        return this.groupSize;
    }

    public void rank(){
        Comparator<User> comparatorFinishDate = new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                int isBigger = o1.finishDate.compareTo(o2.finishDate);
                return o1.finishDate.compareTo(o2.finishDate);
            }
        };
        Collections.sort(member, comparatorFinishDate);
        for(int i=0;i< member.size();i++){
            member.get(i).score+=1;
            if(i==0){
                member.get(i).score+=1;
            }
        }
    }
}
