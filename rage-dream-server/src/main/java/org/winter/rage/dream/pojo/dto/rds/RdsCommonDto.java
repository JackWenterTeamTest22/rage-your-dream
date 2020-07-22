package org.winter.rage.dream.pojo.dto.rds;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: RdsCommonDto
 * @Description:
 * @Author wented
 * @Date 2020/7/22 5:52 PM
 **/
@Data
@Accessors(chain = true)
public class RdsCommonDto {

    private String clusterName;

    private String dbName;

}
