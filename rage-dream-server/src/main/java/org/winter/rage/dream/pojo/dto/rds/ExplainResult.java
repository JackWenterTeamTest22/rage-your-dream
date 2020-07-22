package org.winter.rage.dream.pojo.dto.rds;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: ExplainResult
 * @Description:
 * @Author wented
 * @Date 2020/7/22 4:43 PM
 **/
@Data
@Accessors(chain = true)
public class ExplainResult {

    /**
     * id : 1
     * selectType : SIMPLE
     * table : mta_fi_settle_trace_329
     * partitions : null
     * type : ref
     * possibleKeys : uniq_trace_id_bl,idx_statement_id,settle_trace_buzLine_partnerId,idx_statement_type_turning,idx_statement_type_fee_quantity_income
     * key : uniq_trace_id_bl
     * keyLen : 4
     * ref : const
     * rows : 200348
     * filtered : 6.53
     * extra : Using index condition; Using where; Using filesort
     */

    private String id;
    private String selectType;
    private String table;
    private Object partitions;
    private String type;
    private String possibleKeys;
    private String key;
    private String keyLen;
    private String ref;
    private String rows;
    private String filtered;
    private String extra;

}
