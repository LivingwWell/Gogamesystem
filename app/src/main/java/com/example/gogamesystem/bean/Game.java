package com.example.gogamesystem.bean;

import cn.bmob.v3.BmobObject;

public class Game extends BmobObject {
    public String name;//比赛名字
    public String black;//黑方
    public String white;//白方
    public String victory;//胜利方
    public String defeat;//失败方
    public String method;//胜法
    public String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBlack() {
        return black;
    }

    public void setBlack(String black) {
        this.black = black;
    }

    public String getWhite() {
        return white;
    }

    public void setWhite(String white) {
        this.white = white;
    }

    public String getVictory() {
        return victory;
    }

    public void setVictory(String victory) {
        this.victory = victory;
    }

    public String getDefeat() {
        return defeat;
    }

    public void setDefeat(String defeat) {
        this.defeat = defeat;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Game{" +
                "name='" + name + '\'' +
                ", black='" + black + '\'' +
                ", white='" + white + '\'' +
                ", victory='" + victory + '\'' +
                ", defeat='" + defeat + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
