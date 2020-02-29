/**
  * Copyright 2020 bejson.com 
  */
package com.mingben.betplatform.dto.pk6758;
import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2020-02-29 16:33:4
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class OpenHistory {

    private String win;
    private String usableCredit;
    private String credits;
    private List<String> phase;
    private OpenDateList openDateList;
    private OpenNumList openNumList;
    private List<String> clList;
    private List<String> trendLi;
    private List<List<String>> trendContent;
    public void setWin(String win) {
         this.win = win;
     }
     public String getWin() {
         return win;
     }

    public void setUsableCredit(String usableCredit) {
         this.usableCredit = usableCredit;
     }
     public String getUsableCredit() {
         return usableCredit;
     }

    public void setCredits(String credits) {
         this.credits = credits;
     }
     public String getCredits() {
         return credits;
     }

    public void setPhase(List<String> phase) {
         this.phase = phase;
     }
     public List<String> getPhase() {
         return phase;
     }

    public void setOpenDateList(OpenDateList openDateList) {
         this.openDateList = openDateList;
     }
     public OpenDateList getOpenDateList() {
         return openDateList;
     }

    public void setOpenNumList(OpenNumList openNumList) {
         this.openNumList = openNumList;
     }
     public OpenNumList getOpenNumList() {
         return openNumList;
     }

    public void setClList(List<String> clList) {
         this.clList = clList;
     }
     public List<String> getClList() {
         return clList;
     }

    public void setTrendLi(List<String> trendLi) {
         this.trendLi = trendLi;
     }
     public List<String> getTrendLi() {
         return trendLi;
     }

    public void setTrendContent(List<List<String>> trendContent) {
         this.trendContent = trendContent;
     }
     public List<List<String>> getTrendContent() {
         return trendContent;
     }

}