package org.winter.rage.dream.pojo.dto.rds;

import cn.hutool.json.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: RdsCommonRequest
 * @Description:
 * @Author wented
 * @Date 2020/7/22 4:27 PM
 **/
@Data
@Accessors(chain = true)
public class RdsCommonRequest {

    private String action;

    private String version;

    private JSONObject param;
}
