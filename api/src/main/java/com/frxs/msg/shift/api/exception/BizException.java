/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.exception;

import com.frxs.msg.shift.api.exception.enums.ErrorCodeDetailEnum;

import java.io.Serializable;

/**
 * @author ouyangzhaobing
 * @version : BizException.java,v 0.1 2020年07月28日 3:46 下午
 */
public class BizException extends BaseException implements Serializable {


    private static final long serialVersionUID = -1660124642525159041L;

    /**
     * 创建一个<code>BizException</code>
     *
     * @param code 错误码
     */
    public BizException(ErrorCodeDetailEnum code) {
        super(code);
    }

    /**
     *
     * @param code 错误码
     * @param errorMessage 错误描述
     */
    public BizException(ErrorCodeDetailEnum code, String errorMessage) {
        super(code, errorMessage);
    }

    public BizException(ErrorCodeDetailEnum code, Throwable cause) {
        super(code, cause);
    }
}