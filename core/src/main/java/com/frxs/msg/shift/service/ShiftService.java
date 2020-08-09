/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;

/**
 * @author ouyangzhaobing
 * @version : ShiftService.java,v 0.1 2020年07月28日 4:05 下午
 */
public interface ShiftService {

    /**
     * 查询第n次该部门第值班人员
     * @param pickRequest
     * @return
     */
    EmployeeDto queryNthTimeOnDuty(PickRequest pickRequest);
}