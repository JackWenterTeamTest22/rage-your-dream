package org.winter.rage.dream.service.analysis.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.winter.rage.dream.enums.EnumErrorCode;
import org.winter.rage.dream.enums.EnumSelectStage;
import org.winter.rage.dream.pojo.dto.FieldExtraDto;
import org.winter.rage.dream.pojo.dto.SqlFieldAnalyticResult;
import org.winter.rage.dream.pojo.vo.common.CommonResponse;
import org.winter.rage.dream.service.analysis.SqlAnalysisService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.winter.rage.dream.constant.SqlOprConstant.*;

/**
 * @ClassName: SqlAnalysisServiceImpl
 * @Description:
 * @Author wented
 * @Date 2020/7/21 5:53 PM
 **/
@Service
public class SqlAnalysisServiceImpl implements SqlAnalysisService {
    private final static Logger logger = LoggerFactory.getLogger(SqlAnalysisServiceImpl.class);

    @Override
    public CommonResponse<SqlFieldAnalyticResult> analysisSQL(String sql) {
        //TODO:后期添加对别名的处理

        if (sql == null || sql.length() == 0) {
            return CommonResponse.returnFail(EnumErrorCode.NULL_SQL.getCode(), EnumErrorCode.NULL_SQL.getDesc());
        }
        logger.info("开始进行查询语句sql解析，原始sql为{}", sql);
        //1：先对sql进行预处理，将所有的换行符装换成空格(sql是final的，故必须重新赋一遍值)
        sql = sql.replace("\n", " ");
        logger.debug("1:sql进行处理,将所有的换行符装换成空格后{}", sql);
        //2:将预处理后的sql按照空格进行分隔
        String[] quiltSqls = sql.split("\\s+");
        if (quiltSqls.length == 0) {
            return CommonResponse.returnFail(EnumErrorCode.NULL_SQL.getCode(), EnumErrorCode.NULL_SQL.getDesc());
        }
        //查询语句需要以select开头
        if (!KEYWORD_SELECT.equals(quiltSqls[0].toLowerCase())) {
            return CommonResponse.returnFail(EnumErrorCode.NOT_SQL.getCode(), EnumErrorCode.NOT_SQL.getDesc() + "查询语句没有以select关键字开头");
        }
        //3:开始解析sql
        SqlFieldAnalyticResult result = new SqlFieldAnalyticResult();
        List<String> selectFields = new ArrayList<>();
        List<FieldExtraDto> whereFields = new ArrayList<>();
        EnumSelectStage selectDealType = EnumSelectStage.FIELD;
        for (int part = 0; part < quiltSqls.length; part++) {
            if (quiltSqls[part] == null) {
                continue;
            }

            String local = quiltSqls[part];
            //1判断语句扫描阶段与阶段切换
            EnumSelectStage judgeStage = getStage(selectDealType, local);
            if (judgeStage == null) {
                return CommonResponse.returnFail(EnumErrorCode.NOT_SQL.getCode(), EnumErrorCode.NOT_SQL.getDesc() + "语句关键字顺序错误");
            } else if (judgeStage != EnumSelectStage.UNKNOW) {
                selectDealType = judgeStage;
                continue;
            }

            //2处理select到from中间的部分
            if (selectDealType == EnumSelectStage.FIELD) {
                //去除 distinct
                if (KEYWORD_DISTINCT.equals(local.toLowerCase())) {
                    continue;
                }

                if ("*".equals(local)) {
                    result.setIsSelectAll(true);
                    continue;
                }

                if (judgeStringExist(local, ",") || (part + 1 < quiltSqls.length && KEYWORD_FROM.equals(quiltSqls[part + 1].toLowerCase()))) {
                    //排除 .与as的影响
                    selectFields.add(local.split(".")[1].replace(",", ""));
                    continue;
                }
            }

            //3处理from后面的部分
            if (selectDealType == EnumSelectStage.TABLE && KEYWORD_FROM.equals(quiltSqls[part - 1].toLowerCase())) {
                if (judgeStringExist(local, ".")) {
                    result.setDatabaseName(local.split(".")[0]);
                    result.setTableName(local.split(".")[1]);
                } else {
                    result.setTableName(local);
                }
                continue;
            }

            //4处理where部分
            if (selectDealType == EnumSelectStage.CONDITION) {

            }

        }
        result.setSelectFields(selectFields);


        return CommonResponse.returnSuccess(result);
    }

    /**
     * 获取关键字所处阶段
     *
     * @return 不是阶段关键字则返回未知，阶段关键字合法则返回下一阶段，不合法则返回null
     */
    private EnumSelectStage getStage(EnumSelectStage enumSelectStage, String keyword) {
        if (!KEYWORD_GROUP.equals(keyword.toLowerCase()) && !KEYWORD_FROM.equals(keyword.toLowerCase()) && !KEYWORD_WHERE.equals(keyword.toLowerCase()) && !KEYWORD_ORDER.equals(keyword.toLowerCase()) && !KEYWORD_LIMIT.equals(keyword.toLowerCase())) {
            return EnumSelectStage.UNKNOW;
        } else if (KEYWORD_FROM.equals(keyword.toLowerCase()) && enumSelectStage.getCode() < EnumSelectStage.TABLE.getCode()) {
            return EnumSelectStage.TABLE;
        } else if (KEYWORD_WHERE.equals(keyword.toLowerCase()) && enumSelectStage.getCode() < EnumSelectStage.CONDITION.getCode() && enumSelectStage.getCode() >= EnumSelectStage.TABLE.getCode()) {
            return EnumSelectStage.CONDITION;
        } else if (KEYWORD_GROUP.equals(keyword.toLowerCase()) && enumSelectStage.getCode() < EnumSelectStage.GROUP.getCode() && enumSelectStage.getCode() >= EnumSelectStage.TABLE.getCode()) {
            return EnumSelectStage.GROUP;
        } else if (KEYWORD_ORDER.equals(keyword.toLowerCase()) && enumSelectStage.getCode() < EnumSelectStage.ORDER.getCode() && enumSelectStage.getCode() >= EnumSelectStage.TABLE.getCode()) {
            return EnumSelectStage.ORDER;
        } else if (KEYWORD_LIMIT.equals(keyword.toLowerCase()) && enumSelectStage.getCode() < EnumSelectStage.LIMIT.getCode() && enumSelectStage.getCode() >= EnumSelectStage.TABLE.getCode()) {
            return EnumSelectStage.LIMIT;
        } else {
            return null;
        }
    }


    /**
     * 判断字符串是否符合某个规则
     *
     * @param str   字符串
     * @param regex 匹配规则
     * @return 匹配为true
     * TODO: 方法转移到common的Util中
     */
    public static boolean judgeStringExist(String str, String regex) {
        Pattern pat = Pattern.compile(regex);
        Matcher mat = pat.matcher(str);
        return mat.find();
    }
}