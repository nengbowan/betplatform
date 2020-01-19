/**
 * Copyright 2020 bejson.com
 */
package com.mingben.betplatform.dto.plan;
import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-01-19 10:50:31
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Openhis {

    private String issue;
    private Date openTime;
    private List<Integer> openNums;
    public void setIssue(String issue) {
        this.issue = issue;
    }
    public String getIssue() {
        return issue;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }
    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenNums(List<Integer> openNums) {
        this.openNums = openNums;
    }
    public List<Integer> getOpenNums() {
        return openNums;
    }

}