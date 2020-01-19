/**
 * Copyright 2020 bejson.com
 */
package com.mingben.betplatform.dto.bet;

/**
 * Auto-generated: 2020-01-19 13:19:26
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BetRequestDto {

    private String lotteryCode;
    private String playDetailCode;
    private String bettingNumber;
    private int bettingCount;
    private int bettingAmount;
    private String bettingPoint;
    private int bettingUnit;
    private String bettingIssue;
    private int graduationCount;

    public static BetRequestDto create(String lotteryCode ,String playDetailCode ,String number,  String issue ,  int amount){
        return BetRequestDto.builder()
                .lotteryCode(lotteryCode)
                .playDetailCode(playDetailCode)
                .bettingNumber(number)
                .bettingCount(1)
                .bettingAmount(amount)
                .bettingPoint("7.0")
                .bettingIssue(issue)
                .graduationCount(1)
                .build();
    }

}
