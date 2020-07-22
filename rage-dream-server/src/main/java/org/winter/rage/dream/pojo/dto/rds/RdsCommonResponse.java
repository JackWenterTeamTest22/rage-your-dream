package org.winter.rage.dream.pojo.dto.rds;

import cn.hutool.json.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @ClassName: RdsCommonResponse
 * @Description:
 * @Author wented
 * @Date 2020/7/22 3:52 PM
 **/
@Data
@Accessors(chain = true)
public class RdsCommonResponse {

    /**
     * code : 0
     * userMessage : 操作成功
     * message : 操作成功
     * data :
     * ctx : {"requestId":"xxx","sentryEventId":"None"}
     */

    private int code;
    private String userMessage;
    private String message;
    private JSONObject data;
    private CtxBean ctx;

    @Data
    @Accessors(chain = true)
    public static class CtxBean {
        /**
         * requestId : xxx
         * sentryEventId : None
         */

        private String requestId;
        private String sentryEventId;
    }
}
