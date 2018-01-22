package com.spider.bean;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by michael on 2017/10/24.
 */
public class Match implements Serializable{
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getGameEndTime() {
        return gameEndTime;
    }

    public void setGameEndTime(String gameEndTime) {
        this.gameEndTime = gameEndTime;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getGuestTeam() {
        return guestTeam;
    }

    public void setGuestTeam(String guestTeam) {
        this.guestTeam = guestTeam;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getWin() {
        return win;
    }

    public void setWin(double win) {
        this.win = win;
    }

    public double getDefeats() {
        return defeats;
    }

    public void setDefeats(double defeats) {
        this.defeats = defeats;
    }

    public double getDrew() {
        return drew;
    }

    public void setDrew(double drew) {
        this.drew = drew;
    }

    public double getRqWin() {
        return rqWin;
    }

    public void setRqWin(double rqWin) {
        this.rqWin = rqWin;
    }

    public double getRqDefeats() {
        return rqDefeats;
    }

    public void setRqDefeats(double rqDefeats) {
        this.rqDefeats = rqDefeats;
    }

    public double getRqDrew() {
        return rqDrew;
    }

    public void setRqDrew(double rqDrew) {
        this.rqDrew = rqDrew;
    }

    public int getRqNum() {
        return rqNum;
    }

    public void setRqNum(int rqNum) {
        this.rqNum = rqNum;
    }

    @Override
    public String toString() {
        return "Match{" +
                "cid='" + cid + '\'' +
                ", gameName='" + gameName + '\'' +
                ", gameEndTime='" + gameEndTime + '\'' +
                ", homeTeam='" + homeTeam + '\'' +
                ", guestTeam='" + guestTeam + '\'' +
                ", win=" + win +
                ", defeats=" + defeats +
                ", drew=" + drew +
                ", rqNum=" + rqNum +
                ", rqWin=" + rqWin +
                ", rqDefeats=" + rqDefeats +
                ", rqDrew=" + rqDrew +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
       // if (o == null || getClass() != o.getClass()) return false;

        Match match = (Match) o;

        if (Double.compare(match.win, win) != 0) return false;
        if (Double.compare(match.defeats, defeats) != 0) return false;
        if (Double.compare(match.drew, drew) != 0) return false;
        if (rqNum != match.rqNum) return false;
        if (Double.compare(match.rqWin, rqWin) != 0) return false;
        if (Double.compare(match.rqDefeats, rqDefeats) != 0) return false;
        if (Double.compare(match.rqDrew, rqDrew) != 0) return false;
        if (!cid.equals(match.cid)) return false;
        if (!gameName.equals(match.gameName)) return false;
        if (!gameEndTime.equals(match.gameEndTime)) return false;
        if (!homeTeam.equals(match.homeTeam)) return false;
        return guestTeam.equals(match.guestTeam);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = cid.hashCode();
        result = 31 * result + gameName.hashCode();
        result = 31 * result + gameEndTime.hashCode();
        result = 31 * result + homeTeam.hashCode();
        result = 31 * result + guestTeam.hashCode();
        temp = Double.doubleToLongBits(win);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(defeats);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(drew);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + rqNum;
        temp = Double.doubleToLongBits(rqWin);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rqDefeats);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(rqDrew);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public Match(){}

    public Match(String cid, String gameName, String gameEndTime, String homeTeam, String guestTeam, double win, double defeats, double drew,
                 int rqNum, double rqWin, double rqDefeats, double rqDrew) {
        this.cid = cid;
        this.gameName = gameName;
        this.gameEndTime = gameEndTime;
        this.homeTeam = homeTeam;
        this.guestTeam = guestTeam;
        this.win = win;
        this.defeats = defeats;
        this.drew = drew;
        this.rqNum = rqNum;
        this.rqWin = rqWin;
        this.rqDefeats = rqDefeats;
        this.rqDrew = rqDrew;
        this.createTime = new Date();
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    private String cid;//编号
    private String gameName;
    private String gameEndTime;//停售时间
    private String homeTeam;
    private String guestTeam;
    private double win;
    private double defeats;
    private double drew;
    private int rqNum;
    private double rqWin;
    private double rqDefeats;
    private double rqDrew;
    private Date createTime;
}
