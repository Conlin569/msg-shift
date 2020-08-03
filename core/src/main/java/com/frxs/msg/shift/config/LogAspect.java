/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.config;

import com.frxs.fd.tracex.core.logger.ErrorCodeHelper;
import com.frxs.framework.common.errorcode.CommonError;
import com.frxs.framework.common.errorcode.ErrorCode;
import com.frxs.framework.common.errorcode.ErrorContext;
import com.frxs.framework.common.errorcode.constant.ErrorTypes;
import com.frxs.framework.common.errorcode.util.ErrorUtil;
import com.frxs.msg.shift.api.exception.BizException;
import com.frxs.msg.shift.api.exception.enums.ErrorCodeScenarioEnum;
import com.frxs.msg.shift.api.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

import static com.frxs.msg.shift.api.exception.enums.ErrorCodeDetailEnum.UNKNOWN_EXCEPTION;

/**
 * @author ouyangzhaobing
 * @version : LogAspect.java,v 0.1 2020年07月28日 4:08 下午
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

    private static final String SYSTEM_CODE = "007";

    @Resource
    private ApolloRefreshConfig config;

    @Pointcut("execution(* com.frxs.msg.shift.api.facade.*.*(..))")
    private void anyMethod() {
    }

    @Around("anyMethod()")
    public Object logException(ProceedingJoinPoint pjp) throws Throwable {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Object[] args = pjp.getArgs();
        if (config.isShowAccessLog()) {
            log.info("invoke {} method, params:{}",
                    targetMethod.getDeclaringClass().getName() + "." + targetMethod.getName() + "()",
                    args);
        }

        try {
            Object object = pjp.proceed();//执行该方法
            return object;
        } catch (Exception e) {
            BizException bizException;
            if (e instanceof BizException) {
                bizException = (BizException) e;
            } else {
                bizException = new BizException(UNKNOWN_EXCEPTION, e);
            }
            ErrorContext errorContext = buildErrorContext(bizException);
            String errorCode = errorContext.fetchCurrentErrorCode();
            log.error(bizException.getMessage() + ErrorCodeHelper.wapperPreFix(errorCode), e);
            return ResultUtil.fail(bizException);
        }
    }

    private static ErrorContext buildErrorContext(BizException e) {
        ErrorCode errorCode = buildErrorCode(e);
        ErrorContext errorContext = e.getErrorContext();
        if (errorContext == null) {
            errorContext = new ErrorContext();
        }
        errorContext.addError(new CommonError(errorCode, e.getMessage(), e.getLocalizedMessage()));
        e.setErrorContext(errorContext);
        return errorContext;
    }

    public static ErrorCode buildErrorCode(BizException e) {
        ErrorCode error = ErrorUtil.makeErrorCode(e.getCode().getErrorLevel(), ErrorTypes.BIZ, ErrorCodeScenarioEnum.DUUBO.getCode(), e.getCode().getCode());
        error.setSystemCode(SYSTEM_CODE);
        return error;
    }
}