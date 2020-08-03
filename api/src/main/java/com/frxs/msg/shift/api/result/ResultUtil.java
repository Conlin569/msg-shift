/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.result;

import com.frxs.msg.shift.api.exception.BizException;
import com.frxs.framework.common.errorcode.CommonError;
import com.frxs.framework.common.errorcode.ErrorContext;
import com.frxs.msg.shift.api.exception.enums.ErrorCodeDetailEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;

/**
 * @author ouyangzhaobing
 * @version : ResultUtil.java,v 0.1 2020年07月28日 4:14 下午
 */
@Slf4j
public class ResultUtil {

    /**
     * 发生异常时的通用处理方法
     *
     * @param e {@link BizException}的子类
     * @return
     */
    public static <T> ResultTO<T> fail(BizException e) {
        ResultTO<T> result = new ResultTO<T>();
        result.setSuccess(false);
        result.setErrorContext(e.getErrorContext());
        return result;
    }


    public static <T> ResultTO<T> success(T data) {
        ResultTO<T> result = new ResultTO<T>();
        result.setData(data);
        result.setSuccess(true);
        return result;
    }

    public static <T> T checkAndGet(ResultTO<T> resultTO) {
        if(resultTO == null) {
            return null;
        }
        if(resultTO.isSuccess()) {
            return resultTO.getData();
        }
        log.error("checkData error! cause:{}", resultTO.getErrorContext());
        return null;
    }

    public static <T> T getOrThrow(ResultTO<T> resultTO) {
        if(resultTO == null) {
            return null;
        }
        if(resultTO.isSuccess()) {
            return resultTO.getData();
        } else {
            ErrorContext errorContext = resultTO.getErrorContext();
            CommonError error = errorContext.fetchCurrentError();
            ErrorCodeDetailEnum code = ErrorCodeDetailEnum.getByCode(error.getErrorCode().getErrorSpecific());
            throw new BizException(code, error.getErrorMsg());
        }
    }

    public static <T> T checkAndGet(ResultTO<T> resultTO, Consumer<ResultTO<T>> consumer) {
        if(resultTO == null) {
            return null;
        }
        if(resultTO.isSuccess()) {
            return resultTO.getData();
        } else {
            consumer.accept(resultTO);
            return null;
        }
    }
}