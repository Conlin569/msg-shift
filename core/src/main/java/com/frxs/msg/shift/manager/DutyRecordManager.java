/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.manager;

import com.frxs.msg.shift.dal.entity.Department;
import com.frxs.msg.shift.dal.entity.DutyRecord;
import com.frxs.msg.shift.dal.entity.Employee;

import java.time.LocalDate;

/**
 * @author ouyangzhaobing
 * @version : DutyRecordManager.java,v 0.1 2020年08月03日 2:34 下午
 */
public interface DutyRecordManager {

    /**
     * 根据employee和日期保存值班记录
     *
     * @param employee
     * @param department
     * @param dutyDate
     * @param status
     * @return
     */
    DutyRecord buildDutyRecord(Employee employee, Department department, LocalDate dutyDate,String status);
}