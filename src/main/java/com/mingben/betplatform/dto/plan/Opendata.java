/**
  * Copyright 2020 bejson.com 
  */
package com.mingben.betplatform.dto.plan;
import java.util.Date;

/**
 * Auto-generated: 2020-01-19 10:50:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Opendata {

    private int ticking;
    private String expect;
    private String code;
    private Date nexttime;
    private Date time;
    private Date serverttime;
    public void setTicking(int ticking) {
         this.ticking = ticking;
     }
     public int getTicking() {
         return ticking;
     }

    public void setExpect(String expect) {
         this.expect = expect;
     }
     public String getExpect() {
         return expect;
     }

    public void setCode(String code) {
         this.code = code;
     }
     public String getCode() {
         return code;
     }

    public void setNexttime(Date nexttime) {
         this.nexttime = nexttime;
     }
     public Date getNexttime() {
         return nexttime;
     }

    public void setTime(Date time) {
         this.time = time;
     }
     public Date getTime() {
         return time;
     }

    public void setServerttime(Date serverttime) {
         this.serverttime = serverttime;
     }
     public Date getServerttime() {
         return serverttime;
     }

}