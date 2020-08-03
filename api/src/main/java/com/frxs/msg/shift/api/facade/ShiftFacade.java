/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.api.facade;

import com.frxs.msg.shift.api.domain.EmployeeDto;
import com.frxs.msg.shift.api.domain.PickRequest;
import com.frxs.msg.shift.api.result.ResultTO;

import java.util.List;

/**
 * @author ouyangzhaobing
 * @version : DutyManFacade.java,v 0.1 2020年07月21日 10:54 上午
 */
public interface ShiftFacade {

    /**
     * 查询未来第n次的某部门值班人
     *
     * @param departmentId 部门id
     * @param times        第n次第值班员工
     * @param cycle        值班周期
     * @return
     */
    ResultTO<EmployeeDto> queryOnDuty(Integer departmentId, Integer times, Integer cycle);

    /**
     * pick该部门的值班员工
     *
     * @param pickRequest
     * @return
     */
    ResultTO<List<EmployeeDto>> pickOnDuty(PickRequest pickRequest);

    ResultTO<EmployeeDto> pickOne(PickRequest pickRequest);
}