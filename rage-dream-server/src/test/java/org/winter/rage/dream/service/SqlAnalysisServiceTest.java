package org.winter.rage.dream.service;

import org.junit.Assert;
import org.junit.Test;
import org.winter.rage.dream.CommonTest;
import org.winter.rage.dream.pojo.dto.SqlFieldAnalyticResult;
import org.winter.rage.dream.pojo.vo.common.CommonResponse;

import javax.annotation.Resource;

/**
 * @ClassName: SqlAnalysisServiceTest
 * @Description:
 * @Author wented
 * @Date 2020/7/22 10:36 AM
 **/
public class SqlAnalysisServiceTest extends CommonTest {

    @Resource
    private SqlAnalysisService sqlAnalysisService;

    @Test
    public void testAnalysisSQL(){
        CommonResponse<SqlFieldAnalyticResult> response = sqlAnalysisService.analysisSQL("select * from test limit 1");
        Assert.assertNotNull(response);
    }
}
