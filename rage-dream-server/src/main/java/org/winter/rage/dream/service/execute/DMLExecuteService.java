package org.winter.rage.dream.service.execute;

/**
 * @ClassName: DMLExecuteService
 * @Description:
 * @Author wented
 * @Date 2020/7/21 5:52 PM
 **/
public interface DMLExecuteService<T, R> {

    R execute(T param) throws Exception;

}
