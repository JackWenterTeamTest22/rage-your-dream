package org.winter.rage.dream.pojo.vo.common;

/**
 * @ClassName: CommonResponse
 * @Description:
 * @Author wented
 * @Date 2020/7/21 6:05 PM
 **/
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author haowang
 */
@Data
@Accessors(chain = true)
public class CommonResponse<T> implements Serializable {

    /**
     * 是否成功
     */
    private boolean ret;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * 错误码
     */
    private int errcode;
    private T data;

    public CommonResponse() {
    }

    private CommonResponse(T t) {
        this.ret = true;
        this.data = t;
        this.errcode = 0;
    }

    private CommonResponse(String errmsg, T t) {
        this.ret = false;
        this.errmsg = errmsg;
        this.data = t;
        this.errcode = -1;
    }

    private CommonResponse(int errcode, String errmsg, T t) {
        this.ret = false;
        this.errmsg = errmsg;
        this.errcode = errcode;
        this.data = t;
    }

    public static <T> CommonResponse<T> returnSuccess() {
        return new CommonResponse(null);
    }

    public static <T> CommonResponse<T> returnSuccess(T t) {
        return new CommonResponse(t);
    }

    public static <T> CommonResponse<T> returnFail(String errmsg) {
        return new CommonResponse(errmsg, null);
    }

    public static <T> CommonResponse<T> returnFail(String errmsg, T t) {
        return new CommonResponse(errmsg, t);
    }

    public static <T> CommonResponse<T> returnFail(int errcode, String errmsg) {
        return new CommonResponse(errcode, errmsg, null);
    }

    public static <T> CommonResponse<T> returnFail(int errcode, String errmsg, T t) {
        return new CommonResponse(errcode, errmsg, t);
    }


    @Override
    public int hashCode() {
        boolean prime = true;
        int result = 1;
        result = 31 * result + (this.data == null ? 0 : this.data.hashCode());
        result = 31 * result + (this.errmsg == null ? 0 : this.errmsg.hashCode());
        result = 31 * result + (this.ret ? 1231 : 1237);
        result = 31 * result + this.errcode;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CommonResponse that = (CommonResponse)o;
            if (this.errcode != that.errcode) {
                return false;
            } else if (this.ret != that.ret) {
                return false;
            } else {
                label50: {
                    if (this.data != null) {
                        if (this.data.equals(that.data)) {
                            break label50;
                        }
                    } else if (that.data == null) {
                        break label50;
                    }

                    return false;
                }

                if (this.errmsg != null) {
                    if (!this.errmsg.equals(that.errmsg)) {
                        return false;
                    }
                } else if (that.errmsg != null) {
                    return false;
                }

                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "CommonResonse [ver='1.0', ret=" + this.ret + ", errmsg='" + this.errmsg + '\'' + ", errcode=" + this.errcode + ", data=" + this.data + ']';
    }
}