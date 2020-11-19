package com.zzz.att.mapstruct.entity;

public enum Gender {
    MAN(1, "男"),WOMAN(2, "女"),UNKNOWN(3, "不详");

    private Integer value;
    private String desc;

    Gender(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
