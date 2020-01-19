/**
  * Copyright 2020 bejson.com 
  */
package com.mingben.betplatform.dto.plan;

import java.util.List;
import java.util.Date;

/**
 * Auto-generated: 2020-01-19 10:50:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Data {

    private Opendata opendata;
    private String planName;
    private List<List<String>> cl;
    private Cplan cplan;
    private List<Plan> plan;
    private List<Openhis> openhis;
    public void setOpendata(Opendata opendata) {
         this.opendata = opendata;
     }
     public Opendata getOpendata() {
         return opendata;
     }

    public void setPlanName(String planName) {
         this.planName = planName;
     }
     public String getPlanName() {
         return planName;
     }

    public void setCl(List<List<String>> cl) {
         this.cl = cl;
     }
     public List<List<String>> getCl() {
         return cl;
     }

    public void setCplan(Cplan cplan) {
         this.cplan = cplan;
     }
     public Cplan getCplan() {
         return cplan;
     }

    public void setPlan(List<Plan> plan) {
         this.plan = plan;
     }
     public List<Plan> getPlan() {
         return plan;
     }

    public void setOpenhis(List<Openhis> openhis) {
         this.openhis = openhis;
     }
     public List<Openhis> getOpenhis() {
         return openhis;
     }

}