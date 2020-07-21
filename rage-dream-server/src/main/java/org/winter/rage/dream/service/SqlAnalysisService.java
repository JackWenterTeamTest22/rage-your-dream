package org.winter.rage.dream.service;

/**
 * @ClassName: SqlAnalysisService
 * @Description:
 * @Author wented
 * @Date 2020/7/21 5:52 PM
 **/
public interface SqlAnalysisService {

    /**
     * 解析sql获取各阶段需要的字段
     *
     * @param sql
     * @return
     */
    CommonResponse<SqlFieldAnalyticResult> analysisSQL(String sql);
}
