/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.manager;

/**
 * @author ouyangzhaobing
 * @version : AbstractShift.java,v 0.1 2020年08月01日 11:33 上午
 */
public abstract class AbstractShift<T, R> /*implements ShiftManager<T, R>*/ {

    public T generateDutyMan(R r) {
        T t = pickOne(r);
        saveDutyRecord(t, r);
        return t;
    }

    /**
     * 选人机制,由子类自己实现不同的选人机制
     *
     * @param r
     * @return
     */
    protected abstract T pickOne(R r);

    /**
     * save to db
     *
     * @param t
     * @param r
     */
    protected void saveDutyRecord(T t, R r) {

    }
}