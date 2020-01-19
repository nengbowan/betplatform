/**
  * Copyright 2020 bejson.com 
  */
package com.mingben.betplatform.dto.plan;
import java.util.List;

/**
 * Auto-generated: 2020-01-19 10:50:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Plan {

    private String beginIssue;
    private String winIssue;
    private List<String> plans;
    private String endIssue;
    public void setBeginIssue(String beginIssue) {
         this.beginIssue = beginIssue;
     }
     public String getBeginIssue() {
         return beginIssue;
     }

    public void setWinIssue(String winIssue) {
         this.winIssue = winIssue;
     }
     public String getWinIssue() {
         return winIssue;
     }

    public void setPlans(List<String> plans) {
         this.plans = plans;
     }
     public List<String> getPlans() {
         return plans;
     }

    public void setEndIssue(String endIssue) {
         this.endIssue = endIssue;
     }
     public String getEndIssue() {
         return endIssue;
     }

}