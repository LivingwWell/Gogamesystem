package com.example.gogamesystem.bean;

import java.util.List;

import cn.bmob.v3.BmobObject;

public class Join extends BmobObject {
    public List<Play> joiners;
    public List<String> joins;

    @Override
    public String toString() {
        return
                "joins=" + joins;
    }

    public List<String> getJoins() {
        return joins;
    }

    public void setJoins(List<String> joins) {
        this.joins = joins;
    }

    public List<Play> getJoiners() {
        return joiners;
    }

    public void setJoiners(List<Play> joiners) {
        this.joiners = joiners;
    }


    public static class Play{
        private String joiner;
        private String joingame;

        @Override
        public String toString() {
            return "Play{" +
                    "joiner='" + joiner + '\'' +
                    ", joingame='" + joingame + '\'' +
                    '}';
        }

        public Play(String joiner, String joingame){
            this.joiner=joiner;
            this.joingame=joingame;
        }

        public String getJoingame() {
            return joingame;
        }

        public void setJoingame(String joingame) {
            this.joingame = joingame;
        }

        public String getJoiner() {
            return joiner;
        }

        public void setJoiner(String joiner) {
            this.joiner = joiner;
        }
    }


}
