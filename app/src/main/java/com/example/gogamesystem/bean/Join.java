package com.example.gogamesystem.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

public class Join extends BmobObject {
    public List<String> joiners;

    public List<String> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<String> joiners) {
        this.joiners = joiners;
    }

    public class Joiner{
        private String joiner;

        public Joiner(String joiner){
            this.joiner=joiner;
        }

        public String getJoiner() {
            return joiner;
        }

        public void setJoiner(String joiner) {
            this.joiner = joiner;
        }
    }
}
