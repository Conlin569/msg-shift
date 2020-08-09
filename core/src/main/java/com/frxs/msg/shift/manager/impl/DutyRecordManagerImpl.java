/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2019. All Rights Reserved.
 */
package com.frxs.msg.shift.manager.impl;

import com.frxs.msg.shift.dal.entity.Department;
import com.frxs.msg.shift.dal.entity.DutyRecord;
import com.frxs.msg.shift.dal.entity.Employee;
import com.frxs.msg.shift.dal.mapper.DutyRecordMapper;
import com.frxs.msg.shift.manager.DutyRecordManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author ouyangzhaobing
 * @version : DutyRecordManagerImpl.java,v 0.1 2020年08月03日 2:35 下午
 */
@Component
public class DutyRecordManagerImpl implements DutyRecordManager {

    @Resource
    private DutyRecordMapper dutyRecordMapper;

    @Override
    public DutyRecord buildDutyRecord(Employee employee, Department department, LocalDate dutyDate, String status) {
        DutyRecord dutyRecord = new DutyRecord();
        //  部门信息
        dutyRecord.setDepartmentId(department.getId());
        dutyRecord.setDepartmentName(department.getName());
        //  员工信息
        dutyRecord.setEmployeeId(employee.getId());
        dutyRecord.setEmployeeName(employee.getName());
        //  记录属性
        dutyRecord.setStatus(status);
        dutyRecord.setDutyDate(dutyDate);
        return dutyRecord;
    }
}