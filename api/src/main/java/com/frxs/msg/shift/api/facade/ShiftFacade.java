/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.facade;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.api.result.ResultTO;

/**
 * @author ouyangzhaobing
 * @version : DutyManFacade.java,v 0.1 2020年07月21日 10:54 上午
 */
public interface ShiftFacade {

    /**
     * 查询第n次第值班员工
     *
     * @param pickRequest
     * @return
     */
    ResultTO<EmployeeDto> queryNthTimeOnDuty(PickRequest pickRequest);
}