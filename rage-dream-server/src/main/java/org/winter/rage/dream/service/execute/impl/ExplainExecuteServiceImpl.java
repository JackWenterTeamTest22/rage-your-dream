package org.winter.rage.dream.service.execute.impl;

import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Service;
import org.winter.rage.dream.constant.RdsConstant;
import org.winter.rage.dream.pojo.dto.rds.ExplainResult;
import org.winter.rage.dream.pojo.dto.rds.RdsDQLDto;
import org.winter.rage.dream.service.execute.AbstractDMLExecuteService;

/**
 * @ClassName: ExplainExecuteServiceImpl
 * @Description:
 * @Author wented
 * @Date 2020/7/22 5:27 PM
 **/
@Service
public class ExplainExecuteServiceImpl extends AbstractDMLExecuteService<RdsDQLDto, ExplainResult> {

    @Override
    public String getAction() {
        return RdsConstant.RDS_OPEN_API_DQL_ACTION;
    }

    @Override
    public ExplainResult processResult(JSONObject result) {
        return null;
    }
}
