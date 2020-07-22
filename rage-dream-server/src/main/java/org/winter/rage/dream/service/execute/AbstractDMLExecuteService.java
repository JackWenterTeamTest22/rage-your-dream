package org.winter.rage.dream.service.execute;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.winter.rage.dream.constant.DateConstant;
import org.winter.rage.dream.pojo.dto.rds.RdsCommonRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: AbstractExecuteService
 * @Description:
 * @Author wented
 * @Date 2020/7/22 5:00 PM
 **/
@ConfigurationProperties(prefix = "rds.open.api")
@Data
public abstract class AbstractDMLExecuteService<T, R> implements DMLExecuteService<T, R> {

    private String path;

    private String version;

    private String accessKey;

    private String secretKey;

    private String sign;

    private String authorization;


    public abstract String getAction();

    public abstract R processResult(JSONObject result);

    @Override
    public R execute(T param) throws Exception {
        RdsCommonRequest body = new RdsCommonRequest().setAction(getAction())
                .setParam(new JSONObject(param))
                .setVersion(version);

        HttpResponse response = HttpUtil.createPost(path)
                .addHeaders(buildHeader()).body(new JSONObject(body).toString()).execute();
        return processResult(new JSONObject(new String(response.bodyBytes())));
    }

    private Map<String, String> buildHeader() throws Exception {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DateConstant.DATE_FORMAT_PATTERN, Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone(DateConstant.DATE_TIME_ZONE));
        String date = dateFormat.format(new Date());

        String strToSign = String.format(sign, getAction(), date);
        String signature = hmacSha1(strToSign, secretKey);
        String authorization = String.format(accessKey, signature);

        Map<String, String> httpHeaders = new HashMap<>();
        httpHeaders.put("Date", date);
        httpHeaders.put("Authorization", authorization);

        return httpHeaders;
    }

    private String hmacSha1(String dataStr, String keyStr) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(keyStr.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);
        byte[] bytes = mac.doFinal(dataStr.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(bytes);
    }

}
