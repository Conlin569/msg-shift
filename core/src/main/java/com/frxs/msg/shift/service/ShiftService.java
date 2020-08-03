/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.service;

import com.frxs.msg.shift.api.domain.EmployeeDto;

/**
 * @author ouyangzhaobing
 * @version : ShiftService.java,v 0.1 2020年07月28日 4:05 下午
 */
public interface ShiftService {

    /**
     * 查询未来n天的某部门值班人
     *
     * @param departmentId
     * @param times
     * @param cycle
     * @return
     */
    EmployeeDto queryOnDuty(Integer departmentId, Integer times, Integer cycle);
}