package com.mingben.betplatform.enums;

/**
 * 用户卡类型
 */
public enum  UserTypeEnum {
    TRIAL("试用",0.5),
    DAY("天卡",24d),
    WEEK("周卡",7 * 24d),
    MONTH("月卡",31 * 24d),
    YEAR("年卡",365 * 24d),
    YONGJIU("永久卡",Double.valueOf(Long.MAX_VALUE) );

    private String name ;
    private Double hour;
    UserTypeEnum(String name , Double hour){
        this.name = name;
        this.hour = hour;
    }

    public static UserTypeEnum findByUserType(String name){
        for(UserTypeEnum typeEnum : UserTypeEnum.values()){
            if(typeEnum.name.equals(name)){
                return typeEnum;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHour() {
        return hour;
    }

    public void setHour(Double hour) {
        this.hour = hour;
    }}
