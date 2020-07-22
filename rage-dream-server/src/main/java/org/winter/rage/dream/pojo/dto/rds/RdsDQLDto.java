package org.winter.rage.dream.pojo.dto.rds;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: RdsDQLDto
 * @Description:
 * @Author wented
 * @Date 2020/7/22 5:54 PM
 **/
@Data
@Accessors(chain = true)
public class RdsDQLDto extends RdsCommonDto{
    private String selectSqls;

}
