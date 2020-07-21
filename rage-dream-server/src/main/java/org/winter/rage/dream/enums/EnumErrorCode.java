package org.winter.rage.dream.enums;

import lombok.Getter;

import java.io.Serializable;

/**
 * @ClassName: EnumErrorCode
 * @Description:
 * @Author wented
 * @Date 2020/7/21 5:49 PM
 **/
@Getter
public enum EnumErrorCode implements Serializable {
    //1-100：sql解析错误
    UNKNOW(0, "未知"),
    NOT_SQL(1, "并非sql语句"),
    NULL_SQL(2, "传入的sql语句为空");

    private int code;
    private String desc;

    EnumErrorCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static EnumErrorCode codeOf(int code) {
        for (EnumErrorCode item : values()) {
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
        EnumErrorCode enumInterface = codeOf(code);
        if (enumInterface == null) {
            return "";
        }
        return enumInterface.getDesc();
    }
}
