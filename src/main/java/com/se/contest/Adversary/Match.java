package com.se.contest.Adversary;

import java.util.*;

public class Match extends Thread{
    List<User> pendingList =new ArrayList<User>();
    List<Group> matchedList = new ArrayList<Group>();
    int attendingSum = 0;
    int pendingLength = 0;
    int matchingLength = 0;
    boolean timeFinished = false;
    long finishingTime;
    Map<Integer, Integer> idGroupMap = new HashMap<Integer, Integer>();

    public Match(long finishingTime){
        this.finishingTime = finishingTime;
    }

    public Integer getGroupNo(int studentNo){
        Integer groupNo = idGroupMap.get(studentNo);
        if(groupNo==null){
            return 0;
        }
        else{
            return groupNo;
        }
    }

    public boolean append(int studentNo){
        long current = System.currentTimeMillis();
        if(current - this.finishingTime > 0){
            return false;
        }
        User user = new User(studentNo);
        pendingList.add(user);
        pendingLength++;
        attendingSum++;
        return true;
    }

    public void run(){
        while(true){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long current = System.currentTimeMillis();
            if(current > this.finishingTime){
                break;
            }
            if(attendingSum <= (int)(Constant.STUDENTSUM * Constant.MATCHINGTHRESHOLD)){
                while(pendingLength>=5){
                    Group newGroup = new Group();
                    newGroup = groupIntoN(newGroup, 5);
                    matchedList.add(newGroup);
                    matchingLength++;
                }
            }
        }
        groupRemaining();
    }

    private void groupRemaining(){
        for(int group_size=5;group_size>=3;group_size--) {
            int remains = pendingLength % group_size;
            int groups = (int) (pendingLength / group_size);
            if (remains >= 3 || remains == 0) {
                for (int i = 0; i < groups; i++) {
                    Group newGroup = new Group();
                    newGroup = groupIntoN(newGroup, group_size);
                    matchedList.add(newGroup);
                }
                Group newGroup = new Group();
                newGroup = groupIntoN(newGroup, remains);
                matchedList.add(newGroup);
            } else {
                for (int i = 0; i < groups - 1; i++) {
                    Group newGroup = new Group();
                    newGroup = groupIntoN(newGroup, group_size);
                    matchedList.add(newGroup);
                }
            }
        }
    }

    private Group groupIntoN(Group newGroup, int N){
        for(int i=0;i<N;i++){
            User user = pendingList.get(0);
            idGroupMap.put(user.studentNo, matchedList.size()+1);
            user.setGroupNo(matchedList.size());
            newGroup.add(user);
            pendingList.remove(0);
            pendingLength--;
        }
        newGroup.groupSize = N;
        return newGroup;
    }

}
