package org.winter.rage.dream.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: FieldExtraDto
 * @Description:
 * @Author wented
 * @Date 2020/7/21 5:58 PM
 **/
@Data
@Accessors(chain = true)
public class FieldExtraDto {
    /**
     * 字段名称
     */
    private String name;

    /**
     * 操作类型：<,=,between and,in等，后续可考虑使用枚举代替
     */
    private String oprType;

    /**
     * 等号右侧的值类型（非表字段类型）
     */
    private String valType;

}
