/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.chain;

import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.dal.entity.Employee;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author ouyangzhaobing
 * @version : PickContext.java,v 0.1 2020年07月31日 10:21 上午
 */
@Data
public class PickContext {

    /**
     * 选人的请求
     */
    private PickRequest pickRequest;

    /**
     * 当前值班的人
     */
    private Employee nowEmployee;

    /**
     * 下次值班的人
     */
    private Employee nextEmployee;

    /**
     * 是否重新开始
     */
    private Boolean again;

    /**
     * 该部门上次值班到日期
     */
    private LocalDate lastDutyDate;
}