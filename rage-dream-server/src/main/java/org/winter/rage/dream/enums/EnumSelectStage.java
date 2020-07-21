package org.winter.rage.dream.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @ClassName: EnumSelectStage
 * @Description:
 * @Author wented
 * @Date 2020/7/21 5:51 PM
 **/
@Getter
public enum EnumSelectStage implements Serializable {
    UNKNOW(0, "未知"),
    FIELD(1, "解析字段"),
    TABLE(2, "确定库表"),
    CONDITION(3, "where条件"),
    GROUP(4, "分组"),
    ORDER(5, "排序"),
    LIMIT(6, "限制与分页");

    private int code;
    private String desc;

    EnumSelectStage(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EnumSelectStage codeOf(int code) {
        for (EnumSelectStage item : values()) {
            if (item == null) {
                continue;
            }
            if (code == item.code) {
                return item;
            }
        }
        return null;
    }

    public String getDescByCode(int code) {
        EnumSelectStage enumInterface = codeOf(code);
        if (enumInterface == null) {
            return "";
        }
        return enumInterface.getDesc();
    }

}
