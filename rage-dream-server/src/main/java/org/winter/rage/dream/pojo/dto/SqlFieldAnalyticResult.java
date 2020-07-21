package org.winter.rage.dream.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SqlFieldAnalyticResult
 * @Description:
 * @Author wented
 * @Date 2020/7/21 5:56 PM
 **/
@Data
@Accessors(chain = true)
public class SqlFieldAnalyticResult {

    /**
     * 数据库名称
     */
    private String databaseName;

    /**
     * 表名称
     */
    private String tableName;

    /**
     * select字段集合
     * 注：isSelectAll=true时该字段可不赋值
     */
    private List<String> selectFields;

    /**
     * select * == true
     */
    private Boolean isSelectAll;

    /**
     * where字段集合
     */
    private List<FieldExtraDto> whereFields;

    /**
     * order by字段集合：key->字段名，val->asc/desc（后续可考虑枚举代替）
     * 注：必须和sql的order by中的顺序保持一致
     */
    private Map<String,String> orderFields;


    /**
     * group by字段集合
     * 注：必须和sql的group by中的顺序保持一致
     */
    private List<String> groupFields;

}
