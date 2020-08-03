/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.chain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ouyangzhaobing
 * @version : AbstractPickHandler.java,v 0.1 2020年07月31日 9:47 上午
 */
@Setter
@Getter
public abstract class AbstractPickHandler {

    private static final int TODAY = 0;

    protected AbstractPickHandler nextHandler;

    public abstract void handlerRequest(PickContext pickContext);
}