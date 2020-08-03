/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.result;

import com.frxs.framework.common.domain.BaseResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author ouyangzhaobing
 * @version : ResultTO.java,v 0.1 2020年07月22日 10:12 上午
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ResultTO<T> extends BaseResult implements Serializable {
    /**
     * 返回数据
     */
    private T data;
}
